package com.theoc.pixhell.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import android.util.SparseIntArray;

import com.theoc.pixhell.R;

public class AssetMap {
	//Public keys
	public static final String backgroundKey = "img/Background.png";
	public static final String enemyOne = "img/Enemy.png";
	public static final String enemyTwo = "img/Enemy2.png";
	public static final String enemyThree = "img/Enemy3.png";
	public static final String playerOne = "img/Player.png";
	public static final String playerTwo = "img/Player2.png";
	public static final String shot = "img/Shot.png";
	public static final String explosion = "img/Explosion.png";
	public static final String missile = "img/Missile.png";
	public static final String missileDown = "img/MissileUpsideDown.png";
	public static final String coin = "img/CoinPickupFilled.png";

	
	public static final int BOSS_KILL_FIZZ   = R.raw.boss_kill01;
	public static final int BOSS_KILL_BOOM   = R.raw.boss_kill02;
	public static final int BOSS_KILL_RATTLE = R.raw.boss_kill03;
	public static final int BOSS_KILL_WHOOSH = R.raw.boss_kill01;
	public static final int ENEMY_HIT_BULLET  = R.raw.enemy_hit01;
	public static final int ENEMY_HIT_LASER   = R.raw.enemy_hit02;
	public static final int ENEMY_HIT_MISSILE = R.raw.enemy_hit03;
	public static final int ENEMY_HIT_BOMB    = R.raw.enemy_hit04;
	public static final int ENEMY_KILL_FIZZ   = R.raw.enemy_kill01;
	public static final int ENEMY_KILL_WHOOSH = R.raw.enemy_kill02;
	public static final int ENEMY_KILL_RATTLE = R.raw.enemy_kill03;
	public static final int ENEMY_KILL_DEREZ  = R.raw.enemy_kill04;
	public static final int MENU_SELECT_START   = R.raw.player_hit01; 
	public static final int MENU_SELECT_BACK    = R.raw.player_hit02;
	public static final int MENU_SELECT_SUBMENU = R.raw.player_hit03;
	public static final int MENU_SELECT_TOGGLE  = R.raw.player_hit04; 
	public static final int PICKUP_HEALTH  = R.raw.pickup01; 
	public static final int PICKUP_WEAPON  = R.raw.pickup02;
	public static final int PICKUP_MISSILE = R.raw.pickup03;
	public static final int PICKUP_BOMB    = R.raw.pickup04; 
	public static final int PLAYER_HIT_BULLET  = R.raw.player_hit01; 
	public static final int PLAYER_HIT_LASER   = R.raw.player_hit02;
	public static final int PLAYER_HIT_MISSILE = R.raw.player_hit03;
	public static final int PLAYER_HIT_BOMB    = R.raw.player_hit04; 
	public static final int PLAYER_KILL_EXPLODE  = R.raw.player_kill01; 
	public static final int PLAYER_KILL_GAMEOVER = R.raw.player_kill02;
	public static final int PLAYER_KILL_FIZZ     = R.raw.player_kill03;
	public static final int PLAYER_KILL_BLOOP    = R.raw.player_kill04; 
	public static final int SHOT_BULLET  = R.raw.lazer01; 
	public static final int SHOT_LASER   = R.raw.lazer02;
	public static final int SHOT_MISSILE = R.raw.lazer03;
	public static final int SHOT_BOMB    = R.raw.lazer04; 
	
	private static Map<String, Bitmap> imageMap;
	private static SparseIntArray soundMap;
	
	public static void initImage(AssetManager am) throws IOException {
		imageMap = new HashMap<String, Bitmap>();
		addImage(am, backgroundKey);
		addImage(am, playerOne);
		addImage(am, enemyOne);
		addImage(am, enemyTwo);
		addImage(am, enemyThree);
		addImage(am, shot);
		addImage(am, explosion);
		addImage(am, missile);
		addImage(am, missileDown);
		addImage(am, coin);
	}
	
	public static void initSound(Context c, SoundPool sp) {
		soundMap = new SparseIntArray();
		soundMap.put(BOSS_KILL_FIZZ, sp.load(c, BOSS_KILL_FIZZ, 1));
		soundMap.put(BOSS_KILL_BOOM, sp.load(c, BOSS_KILL_BOOM, 1));
		soundMap.put(BOSS_KILL_RATTLE, sp.load(c, BOSS_KILL_RATTLE, 1));
		soundMap.put(BOSS_KILL_WHOOSH, sp.load(c, BOSS_KILL_WHOOSH, 1));
		soundMap.put(ENEMY_HIT_BULLET, sp.load(c, ENEMY_HIT_BULLET, 1));
		soundMap.put(ENEMY_HIT_LASER, sp.load(c, ENEMY_HIT_LASER, 1));
		soundMap.put(ENEMY_HIT_MISSILE, sp.load(c, ENEMY_HIT_MISSILE, 1));
		soundMap.put(ENEMY_HIT_BOMB, sp.load(c, ENEMY_HIT_BOMB, 1));
		soundMap.put(ENEMY_KILL_FIZZ, sp.load(c, ENEMY_KILL_FIZZ, 1));
		soundMap.put(ENEMY_KILL_WHOOSH, sp.load(c, ENEMY_KILL_WHOOSH, 1));
		soundMap.put(ENEMY_KILL_DEREZ, sp.load(c, ENEMY_KILL_DEREZ, 1));
		soundMap.put(ENEMY_KILL_RATTLE, sp.load(c, ENEMY_KILL_RATTLE, 1));
		soundMap.put(MENU_SELECT_START, sp.load(c, MENU_SELECT_START, 1));
		soundMap.put(MENU_SELECT_TOGGLE, sp.load(c, MENU_SELECT_TOGGLE, 1));
		soundMap.put(MENU_SELECT_SUBMENU, sp.load(c, MENU_SELECT_SUBMENU, 1));
		soundMap.put(MENU_SELECT_BACK, sp.load(c, MENU_SELECT_BACK, 1));
		soundMap.put(PICKUP_HEALTH, sp.load(c, PICKUP_HEALTH, 1));
		soundMap.put(PICKUP_WEAPON, sp.load(c, PICKUP_WEAPON, 1));
		soundMap.put(PICKUP_MISSILE, sp.load(c, PICKUP_MISSILE, 1));
		soundMap.put(PICKUP_BOMB, sp.load(c, PICKUP_BOMB, 1));
		soundMap.put(PLAYER_HIT_BULLET, sp.load(c, PLAYER_HIT_BULLET, 1));
		soundMap.put(PLAYER_HIT_LASER, sp.load(c, PLAYER_HIT_LASER, 1));
		soundMap.put(PLAYER_HIT_MISSILE, sp.load(c, PLAYER_HIT_MISSILE, 1));
		soundMap.put(PLAYER_HIT_BOMB, sp.load(c, PLAYER_HIT_BOMB, 1));
		soundMap.put(PLAYER_KILL_EXPLODE, sp.load(c, PLAYER_KILL_EXPLODE, 1));
		soundMap.put(PLAYER_KILL_GAMEOVER, sp.load(c, PLAYER_KILL_GAMEOVER, 1));
		soundMap.put(PLAYER_KILL_FIZZ, sp.load(c, PLAYER_KILL_FIZZ, 1));
		soundMap.put(PLAYER_KILL_BLOOP, sp.load(c, PLAYER_KILL_BLOOP, 1));
		soundMap.put(SHOT_BULLET, sp.load(c, SHOT_BULLET, 1));
		soundMap.put(SHOT_MISSILE, sp.load(c, SHOT_MISSILE, 1));
		soundMap.put(SHOT_LASER, sp.load(c, SHOT_LASER, 1));
		soundMap.put(SHOT_BOMB, sp.load(c, SHOT_BOMB, 1));
	}
	
	public static int getSoundID(int key) {
		return soundMap.get(key);
	}
	
	//Adds an image to the map, the key needs to be the same as the relative filepath to the image
	private static void addImage(AssetManager am, String keyFilePath) throws IOException {
		imageMap.put(keyFilePath, BitmapFactory.decodeStream(am.open(keyFilePath)));
	}
	
	/**
	 * Returns the Bitmap images associated with the given key
	 * @param key
	 * 			One of the public instance fields in this class
	 * @return
	 */
	public static Bitmap getImage(String key) {
		if (!imageMap.containsKey(key)) {
			return null;
		}
		return imageMap.get(key);
	}
}
