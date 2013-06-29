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
	public static final String enemyTwo = "img/Enemy2.png";
	public static final String enemyThree = "img/Enemy3.png";
	public static final String playerOne = "img/Player.png";
	public static final String playerTwo = "img/Player2.png";
	public static final String shot = "";
	
	private static Map<String, Bitmap> map;
	
	public static void init(AssetManager am) throws IOException {
		map = new HashMap<String, Bitmap>();
		addImage(am, backgroundKey);
		addImage(am, playerOne);
		
		addImage(am, enemyOne);
		addImage(am, enemyTwo);
		addImage(am, enemyThree);
	}
	
	//Adds an image to the map, the key needs to be the same as the relative filepath to the image
	private static void addImage(AssetManager am, String keyFilePath) throws IOException {
		map.put(keyFilePath, BitmapFactory.decodeStream(am.open(keyFilePath)));
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
