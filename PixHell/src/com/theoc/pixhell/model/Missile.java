package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.infoboxes.StatInfo;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class Missile extends SmartWeapons {	

	public Missile(Bitmap image, Vector2 position, Vector2 maxVel, int damage) {
		super(image, position, maxVel, Constants.MISSILE_WIDTH, Constants.MISSILE_HEIGHT, damage);		
	}





}
