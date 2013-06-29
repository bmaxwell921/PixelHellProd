package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import android.graphics.Point;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;

public class LevelObject extends Observable
{
	public int screenWidth, screenHeight;
	private enum GameState {IN_WAVE, BETWEEN_WAVE};
	private GameState curGameState;
	
	private GameObject background;
	private List<Ship> enemies;
	private Ship player;
	
	
	private List<Weapon> playerShots;
	private List<Weapon> enemyShots;
	
	//private List<GameObject> consumables;
	//private List<Explosion>
	
	private int curWave;
	
	public LevelObject(int screenWidth, int screenHeight, InputManager im) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		background = new Background(AssetMap.getImage(AssetMap.backgroundKey));
		enemies = new LinkedList<Ship>();
		playerShots = new LinkedList<Weapon>();
		enemyShots = new LinkedList<Weapon>();
		
		player = new Player(AssetMap.getImage(AssetMap.playerOne), 
				new Point(screenWidth / 2, screenHeight / 2), new Point(0 ,0), 
				im, screenWidth, screenHeight);

		curGameState = GameState.BETWEEN_WAVE;
		curWave = 1;
		setUpNextWave();
	}
	
	public void update(long timeElapsed) {
		if (curGameState == GameState.IN_WAVE) {
			inWaveUpdate(timeElapsed);
		} else if (curGameState == GameState.BETWEEN_WAVE) {
			//setUpNextWave();
		}
		
		
		
		this.setChanged();
		this.notifyObservers();
	}
	
	private void inWaveUpdate(long timeElapsed) {
		//Move everyone, then check collisions
		player.update(timeElapsed);
		for (Ship ship : enemies) {
			ship.update(timeElapsed);
		}
		
		for (GameObject shot : playerShots) {
			shot.update(timeElapsed);
		}
		
		for (GameObject shot : enemyShots) {
			shot.update(timeElapsed);
		}
		
		background.update(timeElapsed);
		
		/*
		 * Collisions:
		 * 	Player on enemy - Take away health
		 * 	Player on enemyProj - take away health
		 * 	
		 * 	Enemy of playerProj - take away health
		 * 	
		 * 	Player with edge of screen
		 */
		playerEnemyCollisions();
		playerEnemyShotCollisions();
		
		enemyPlayerShotCollisions();
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
	
	private void setUpNextWave() {
		//Ask the AIWaveInfoManager to give us how many enemies to make
		//Send that info to the AIFactory
		
		curGameState = GameState.IN_WAVE;
		++curWave;
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
