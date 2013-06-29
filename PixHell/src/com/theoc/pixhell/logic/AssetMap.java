package com.theoc.pixhell.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
	public static final int PLAYER_WEAPON  = R.raw.pickup02;
	public static final int PLAYER_MISSILE = R.raw.pickup03;
	public static final int PLAYER_BOMB    = R.raw.pickup04; 
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
	//private static Map<Integer, Float> soundMap;
	
	public static void init(AssetManager am) throws IOException {
		imageMap = new HashMap<String, Bitmap>();
		addImage(am, backgroundKey);
		addImage(am, playerOne);
		
		addImage(am, enemyOne);
		addImage(am, enemyTwo);
		addImage(am, enemyThree);
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
