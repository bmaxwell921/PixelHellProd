package com.theoc.pixhell.logic;

import java.io.IOException;
import java.util.Map;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AssetMap {
	//Public keys
	public static final String backgroundKey = "img/Background.png";
	public static final String enemyOne = "";
	public static final String enemyTwo = "";
	public static final String enemyThree = "";
	public static final String playerOne = "";
	public static final String playerTwo = "";
	public static final String shot = "";
	
	private static Map<String, Bitmap> map;
	
	public static void init(AssetManager am) throws IOException {
		map.put(backgroundKey, BitmapFactory.decodeStream(am.open(backgroundKey)));
	}
}
