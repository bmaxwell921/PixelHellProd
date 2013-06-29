package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class BulletWeapon extends DumbWeapon {
	private static final int DEFAULT_DAMAGE = 10;
	
	public BulletWeapon(Bitmap image, Vector2 position, Vector2 maxVel) {
		super(image, position, maxVel, Constants.BULLET_WIDTH, 
				Constants.BULLET_HEIGHT, DEFAULT_DAMAGE);
		
	}
}
