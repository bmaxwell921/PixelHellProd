package com.theoc.pixhell.infoboxes;

/**
 * Box to hold information about a given wave
 * @author brandon
 *
 */
public class WaveInfo {

	public int numEnemies;
	public float spawnRate;
	public int enemyHealth;
	
	public WaveInfo(int numEnemies, float spawnRate, int enemyHealth) {
		this.numEnemies = numEnemies;
		this.spawnRate = spawnRate;
		this.enemyHealth = enemyHealth;
	}
}
