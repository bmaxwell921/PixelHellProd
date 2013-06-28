package com.theoc.pixhell.model;

import android.graphics.Point;

public abstract class Enemy extends Ship {
	
	public Enemy() {
		this(defaultFireRate);
	}
	
	public Enemy(float fireRate) {
		super(fireRate);
	}
	
	public Enemy(Point location, Point velocity) {
		super (location, velocity, defaultFireRate);
	}
	
	public Enemy(Point location, Point velocity, float fireRate) {
		super(location, velocity, fireRate);
	}
}
