package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class DumbWeapon extends Weapon {

	public DumbWeapon(Bitmap image, Vector2 position, Vector2 maxVel, int width, int height, int damage) {
		super(image, position, maxVel, width, height, damage);
	}	
}
