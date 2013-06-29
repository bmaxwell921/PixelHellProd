package com.theoc.pixhell.logic;

import java.util.Queue;
import java.util.Random;

import android.graphics.Point;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.model.Grunt;
import com.theoc.pixhell.model.Ship;
import com.theoc.pixhell.utilities.Difficulty;

/**
 * Class that 'spawns' Ai for the Level Object
 * @author brandon
 *
 */
public class AIFactory {
	private Random gen;
	
	private WaveInfo baseWaveInfo;
	private WaveInfo curWaveInfo;
	
	private float timeUntilSpawn;
	
	private PathFactory pf;
	
	public AIFactory(Difficulty dif, WaveInfo baseWaveInfo) {
		this.baseWaveInfo = baseWaveInfo;
		this.curWaveInfo = baseWaveInfo;
		timeUntilSpawn = this.curWaveInfo.spawnDelay;
		pf = new PathFactory();
		gen = new Random();
	}
	
	//TODO balance this
	public void moveToNextWave() {
		int nextWave = curWaveInfo.waveNum + 1;
		float minSpawnDelay = 50;
		float spawnRate = baseWaveInfo.spawnDelay / nextWave;
		spawnRate = spawnRate < minSpawnDelay ? minSpawnDelay : spawnRate;
		curWaveInfo.setVals(baseWaveInfo.numEnemies * nextWave, 
				spawnRate, baseWaveInfo.enemyHealth + 10 * nextWave, nextWave);
	}
	
	public boolean timeForSpawn(float dt) {
		timeUntilSpawn -= dt;
		if (timeUntilSpawn <= 0) {
			return true;
		}
		return false;
	}
	
	public Ship spawnEnemy() {
		timeUntilSpawn = curWaveInfo.spawnDelay;
		--curWaveInfo.numEnemies;
		return chooseNextEnemy();
	}
	
	private Ship chooseNextEnemy() {
		int numEnemies = 3;
		int val = gen.nextInt(numEnemies);
		Ship ret = null;
		if (val == 0) {
			ret = new Grunt(AssetMap.getImage(AssetMap.enemyOne));
		} else if (val == 1) {
			
		} else {
			
		}
		
		return ret;
	}
	
	public boolean isLevelComplete() {
		return curWaveInfo.numEnemies <= 0;
	}
	
	private class PathFactory {
		
		
		public Queue<Point> getPath() {
			return null;
		}
	}
	
}
