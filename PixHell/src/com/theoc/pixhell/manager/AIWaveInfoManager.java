package com.theoc.pixhell.manager;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.utilities.Difficulty;

/**
 * Is used by the LevelObject to figure out information about the enemies this wave .
 * Mostly a rename of the AI Admin class
 * @author brandon
 *
 */
public class AIWaveInfoManager {	
	private final int baseWaveEnemyCount = 5;
	//Millis between spawn times
	private final float baseWaveSpawnDelay = 2000;
	private final float minSpawnDelay = 50;
	
	private final int baseWaveEnemyHealth = 100;
	
	private Difficulty curDif;
	
	public AIWaveInfoManager(Difficulty dif) {
		curDif = dif;
	}
	
	public WaveInfo generateNextWaveInfo(int waveNum) {
		if (waveNum <= 0) {
			throw new IllegalArgumentException("Wave number must be at least 1");
		}
		float spawnRate = baseWaveSpawnDelay / waveNum;
		spawnRate = spawnRate < minSpawnDelay ? minSpawnDelay : spawnRate;
		return new WaveInfo(baseWaveEnemyCount * waveNum, baseWaveSpawnDelay / waveNum, 
				baseWaveEnemyHealth * waveNum);
	}
}
