package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class Enemy extends Ship {
	
	public Enemy(Bitmap image) {
		this(image, defaultFireRate);
	}
	
	public Enemy(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Enemy(Bitmap image, Point location, Point velocity) {
		super (image, location, velocity, defaultFireRate);
	}
	
	public Enemy(Bitmap image, Point location, Point velocity, float fireRate) {
		super(image, location, velocity, fireRate);
	}
	
	protected void moveToLocation(Point dest) {
		//Moves the current Enemy toward the given point
	}
}
