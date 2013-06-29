package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import android.graphics.Rect;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.logic.AIFactory;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.manager.SoundManager;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Difficulty;
import com.theoc.pixhell.utilities.GameState;

public class LevelObject extends Observable
{
	public int screenWidth, screenHeight;
	public GameState curGameState;
	private GameState onPauseState;
	
	private GameObject background;
	private List<Ship> enemies;
	public Ship player;
	
	
	private List<Weapon> playerShots;
	private List<Weapon> enemyShots;
	
	//private List<GameObject> consumables;
	//private List<Explosion>
	
	private AIFactory factory;
	private SoundManager sm;
	
	//TODO make this better
	private final int TEAR_DOWN_TIMER = 3000;
	private int timeLeftForTearDown = 0;
	
	
	public LevelObject(int screenWidth, int screenHeight, InputManager im, SoundManager sm ) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sm = sm;
		background = new Background(AssetMap.getImage(AssetMap.backgroundKey), screenWidth, 
				screenHeight);
		enemies = new LinkedList<Ship>();
		playerShots = new LinkedList<Weapon>();
		enemyShots = new LinkedList<Weapon>();
		
		//TODO don't have this hard coded here
		player = new Player(AssetMap.getImage(AssetMap.playerOne), im, screenWidth, screenHeight);

		transitionToState(GameState.BETWEEN_WAVE); 
		onPauseState = GameState.IN_WAVE;
		
		//TODO get Difficulty this from elsewhere
		factory = new AIFactory(Difficulty.EASY, new WaveInfo(10, 2000, 100), 
				screenWidth, screenHeight);
		setUpNextWave();
	}
	
	public void update(long timeElapsed) {
		if (curGameState == GameState.PAUSE) {
			//Do nothing
		} else if (curGameState == GameState.IN_WAVE) {
			inWaveUpdate(timeElapsed);
		} else if (curGameState == GameState.BETWEEN_WAVE) {
			setUpNextWave();
		} else if (curGameState == GameState.TEAR_DOWN) {
			tearDownUpdate(timeElapsed);
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void pause() {
		if (!isPaused()) {
			onPauseState = curGameState;
			transitionToState(GameState.PAUSE);
		}
	}
	
	public boolean isPaused() {
		return curGameState == GameState.PAUSE;
	}
	
	public void resume() {
		transitionToState(onPauseState);
		onPauseState = GameState.IN_WAVE;
	}
	
	public void setPlayerWeapon(Class<? extends Weapon> weapon) {
		//TODO Overhaul how the weapons work to be component-entity
	}
	
	private void inWaveUpdate(long timeElapsed) {
		doUpdates(timeElapsed);
		doCollisionChecks();
		spawnNewEnemies(timeElapsed);
		checkOffScreenGameObjects();
		checkEndGame();
	}
	
	private void doUpdates(float timeElapsed) {
		//Move everyone, then check collisions
		player.update(timeElapsed);
		Weapon Playerweapon =player.Fire(timeElapsed);
		if( Playerweapon !=null)
		{
			sm.playSoundEffect(AssetMap.SHOT_BULLET);
			playerShots.add(Playerweapon);
		}
		for (Ship ship : enemies) {
			ship.update(timeElapsed);
			Weapon Enemyweapon =ship.Fire(timeElapsed);
			if( Enemyweapon !=null)
			{
				sm.playSoundEffect(AssetMap.SHOT_BULLET);
				enemyShots.add(Enemyweapon);
			}
		}
		
		for (GameObject shot : playerShots) {
			shot.update(timeElapsed);
		}
		
		for (GameObject shot : enemyShots) {
			shot.update(timeElapsed);
			
		}
		background.update(timeElapsed);
	}
	
	private void doCollisionChecks() {
		playerEnemyCollisions();
		playerEnemyShotCollisions();
		enemyPlayerShotCollisions();
	}
	
	private void spawnNewEnemies(float dt) {
		if (factory.timeForSpawn(dt)) {
			enemies.add(factory.spawnEnemy());
		}
	}
	
	private void playerEnemyCollisions() {
		//for (Ship enemy : enemies) {
		for (Iterator<Ship> iter = enemies.iterator(); iter.hasNext();) {
			Ship enemy = iter.next();
			if (player.CollidesWith(enemy)) {
				player.applyDamage(Constants.ENEMY_CRASH_DAMAGE);
				iter.remove();
			}
		}
	}
	
	private void playerEnemyShotCollisions() {
		handleShipShotCollision(player, enemyShots);
	}
	
	private void enemyPlayerShotCollisions() {
		for (Ship enemy : enemies) {
			handleShipShotCollision(enemy, playerShots);
		}
	}
	
	private void handleShipShotCollision(Ship single, 
			List<Weapon> others) {
		//for (Weapon other : others) {
		for (Iterator<Weapon> iter = others.iterator(); iter.hasNext();) {
			Weapon other = iter.next();
			if (single.CollidesWith(other)) {
				single.applyDamage(other.damage);
				iter.remove();
			}
		}
	}
	
	private void checkOffScreenGameObjects() {
		checkListOffScreen(enemies);
		checkListOffScreen(playerShots);
		checkListOffScreen(enemyShots);
	}
	
	private void checkListOffScreen(List<? extends GameObject> objs) {
		for (Iterator<? extends GameObject> iter = objs.iterator(); iter.hasNext(); ) {
			if (isOffScreen(iter.next())) {
				iter.remove();
			}
		}
	}
	
	private void checkEndGame() {
		if (player.stats.getHealth() <= 0) {
			transitionToState(GameState.TEAR_DOWN);
		}
	}
	
	private boolean isOffScreen(GameObject obj) {
		return !background.RectBoxforCollision().contains(obj.RectBoxforCollision());
	}
	
	private void setUpNextWave() {
		factory.moveToNextWave();
		transitionToState(GameState.IN_WAVE);
	}
	
	private void tearDownUpdate(float dt) {
		timeLeftForTearDown -= dt;
		if (timeLeftForTearDown <= 0) {
			transitionToState(GameState.GAME_OVER);
		}
	}
	
	private void transitionToState(GameState state) {
		curGameState = state;
		if (state == GameState.TEAR_DOWN) {
			timeLeftForTearDown = TEAR_DOWN_TIMER;
		}
	}
	
	public List<GameObject> getOnscreenObjects() {
		List<GameObject> onScreen = new ArrayList<GameObject>();
		onScreen.add(background);
		onScreen.addAll(enemies);
		onScreen.add(player);
		onScreen.addAll(enemyShots);
		onScreen.addAll(playerShots);
		return onScreen;
	}
}
