package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class Weapon extends GameObject {
	public int damage;
	private static final Vector2 DEFAULT_SPEED = new Vector2(0, 20);
	
	public Weapon(Bitmap image, Vector2 position, Vector2 maxVel, int width, int height, int damage) {
		super(image, position, maxVel, width, height);
		this.damage = damage;
	}
}
