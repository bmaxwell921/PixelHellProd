package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class Weapon extends GameObject {
	public int damage;
	
	public Weapon(Vector2 position, Vector2 maxVel, int height, int width,
			Bitmap image) {
		super(position, maxVel, height, width, image);
	}
}
