package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.logic.AIFactory;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.manager.SoundManager;
import com.theoc.pixhell.utilities.Difficulty;

public class LevelObject extends Observable
{
	public int screenWidth, screenHeight;
	private enum GameState {PAUSE, IN_WAVE, BETWEEN_WAVE};
	private GameState curGameState;
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

		curGameState = GameState.BETWEEN_WAVE; 
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
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void pause() {
		if (!isPaused()) {
			onPauseState = curGameState;
			curGameState = GameState.PAUSE;
		}
	}
	
	public boolean isPaused() {
		return curGameState == GameState.PAUSE;
	}
	
	public void resume() {
		curGameState = onPauseState;
	}
	
	private void inWaveUpdate(long timeElapsed) {
		doUpdates(timeElapsed);
		doCollisionChecks();
		spawnNewEnemies(timeElapsed);
		checkOffScreenGameObjects();
	}
	
	private void doUpdates(float timeElapsed) {
		//Move everyone, then check collisions
		player.update(timeElapsed);
		for (Ship ship : enemies) {
			ship.update(timeElapsed);
			Weapon weapon =ship.Fire(timeElapsed);
			if( weapon !=null)
			{
				sm.playSoundEffect(AssetMap.SHOT_BULLET);
				enemyShots.add(weapon);
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
		for (Weapon other : others) {
			if (single.CollidesWith(other)) {
				single.applyDamage(other.damage);
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
	
	private boolean isOffScreen(GameObject obj) {
		return !background.RectBoxforCollision().contains(obj.RectBoxforCollision());
	}
	
	private void setUpNextWave() {
		factory.moveToNextWave();
		curGameState = GameState.IN_WAVE;
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
