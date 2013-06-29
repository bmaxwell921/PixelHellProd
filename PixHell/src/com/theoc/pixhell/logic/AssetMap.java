package com.theoc.pixhell.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AssetMap {
	//Public keys
	public static final String backgroundKey = "img/Background.png";
	public static final String enemyOne = "img/Enemy.png";
	public static final String enemyTwo = "";
	public static final String enemyThree = "";
	public static final String playerOne = "img/Player.png";
	public static final String playerTwo = "";
	public static final String shot = "";
	
	private static Map<String, Bitmap> map;
	
	public static void init(AssetManager am) throws IOException {
		map = new HashMap<String, Bitmap>();
		map.put(backgroundKey, BitmapFactory.decodeStream(am.open(backgroundKey)));
		map.put(playerOne, BitmapFactory.decodeStream(am.open(playerOne)));
		
		map.put(enemyOne, BitmapFactory.decodeStream(am.open(enemyOne)));
	}
	
	/**
	 * Returns the Bitmap images associated with the given key
	 * @param key
	 * 			One of the public instance fields in this class
	 * @return
	 */
	public static Bitmap getImage(String key) {
		if (!map.containsKey(key)) {
			return null;
		}
		return map.get(key);
	}
}
