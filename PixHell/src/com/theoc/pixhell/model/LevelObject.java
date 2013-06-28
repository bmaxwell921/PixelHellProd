package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;

public class LevelObject extends Observable
{
	private enum GameState {IN_WAVE, BETWEEN_WAVE};
	private GameState curGameState;
	private GameObject background;
	private List<Ship> enemies;
	private Ship player;
	
	private List<GameObject> shots;
	
	public LevelObject(InputManager im) {
		background = new Background(AssetMap.getImage(AssetMap.backgroundKey));
		player = new Player();
		curGameState = GameState.BETWEEN_WAVE;
		setUpNextWave(0);
	}
	
	public void update(long timeElapsed) {
		//
		
		
		
		this.setChanged();
		this.notifyObservers();
	}
	
	private void setUpNextWave(int waveNumber) {
		//Ask the AIWaveInfoManager to give us how many enemies to make
		//Send that info to the AIFactory
		
		curGameState = GameState.IN_WAVE;
	}
	
	public List<GameObject> getOnscreenObjects() {
		List<GameObject> onScreen = new ArrayList<GameObject>();
		onScreen.add(background);
		onScreen.addAll(enemies);
		onScreen.add(player);
		onScreen.addAll(shots);		
		return onScreen;
	}
}
