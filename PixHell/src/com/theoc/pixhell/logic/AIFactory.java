package com.theoc.pixhell.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import android.graphics.Point;
import android.util.Log;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.model.Enemy;
import com.theoc.pixhell.model.Grunt;
import com.theoc.pixhell.model.Ship;
import com.theoc.pixhell.utilities.Difficulty;
import com.theoc.pixhell.utilities.Vector2;

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
	
	private int screenWidth, screenHeight;
	
	public AIFactory(Difficulty dif, WaveInfo baseWaveInfo, int screenWidth, int screenHeight) {
		this.baseWaveInfo = baseWaveInfo;
		this.curWaveInfo = baseWaveInfo;
		timeUntilSpawn = this.curWaveInfo.spawnDelay;
		pf = new PathFactory();
		gen = new Random();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
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
		Vector2 position = getStartPosition();
		Enemy ret = null;
		if (val == 0) {
			ret = new Grunt(AssetMap.getImage(AssetMap.enemyOne), position);
		} else if (val == 1) {
			ret = new Grunt(AssetMap.getImage(AssetMap.enemyTwo), position);
		} else {
			ret = new Grunt(AssetMap.getImage(AssetMap.enemyThree), position);
		}
		
		ret.setPathQueue(pf.getPathFor(ret));
		return ret;
	}
	
	private Vector2 getStartPosition() {
		int x = gen.nextInt(screenWidth - 100);
		Log.i("Movement", "x pos is: " + x);
		return new Vector2(gen.nextInt(screenWidth - 100), 0);
	}
	
	public boolean isLevelComplete() {
		return curWaveInfo.numEnemies <= 0;
	}
	
	private class PathFactory {
		
		List<Queue<Vector2>> paths;
		
		public PathFactory() {
			paths = new ArrayList<Queue<Vector2>>();
		}
		
		public Queue<Vector2> getPathFor(Enemy en) {
			setUpPaths(en);
			
			int qPath = gen.nextInt(paths.size());
			
			return paths.get(qPath);
		}
		
		
		private void setUpPaths(Enemy en) {
			paths.add(straightLineQueue(en));
		}
		
		//Chooses a random point at the bottom of the screen to move to
		private Queue<Vector2> straightLineQueue(Enemy en) {
			Queue<Vector2> q = new LinkedList<Vector2>();
			//100 = image width
			int x = gen.nextInt(screenWidth - en.width);
			int buffer = 2 * en.height;
			int y = screenHeight + buffer;
			q.add(new Vector2(x, y));
			return q;
		}
	}	
}
