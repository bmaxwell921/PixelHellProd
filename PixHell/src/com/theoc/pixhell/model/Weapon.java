package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class Weapon extends GameObject {
	public int damage;
	
	public Weapon(Bitmap image, Vector2 position, Vector2 maxVel, int height, int width) {
		super(image, position, maxVel, height, width);
	}
}
