package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.infoboxes.StatInfo;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class Missile extends SmartWeapons {
	private static final int DEFAULT_DAMAGE = 100;
	

	public Missile(Bitmap image, Vector2 position, Vector2 maxVel) {
		super(image, position, maxVel, Constants.MISSILE_WIDTH, Constants.MISSILE_HEIGHT, DEFAULT_DAMAGE);
		// TODO Auto-generated constructor stub
		
	}





}
