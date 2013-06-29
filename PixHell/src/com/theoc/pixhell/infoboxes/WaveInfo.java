package com.theoc.pixhell.infoboxes;

/**
 * Box to hold information about a given wave
 * @author brandon
 *
 */
public class WaveInfo {

	public int numEnemies;
	public float spawnDelay;
	public int enemyHealth;
	public int waveNum;
	
	public WaveInfo(int numEnemies, float spawnDelay, int enemyHealth) {
		this.numEnemies = numEnemies;
		this.spawnDelay = spawnDelay;
		this.enemyHealth = enemyHealth;
		this.waveNum = 1;
	}
	
	public void setVals(int numEnemies, float spawnDelay, int enemyHealth, int waveNum) {
		this.numEnemies = numEnemies;
		this.spawnDelay = spawnDelay;
		this.enemyHealth = enemyHealth;
		this.waveNum = waveNum;
	}
}
