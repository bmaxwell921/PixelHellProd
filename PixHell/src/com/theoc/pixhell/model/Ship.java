package com.theoc.pixhell.model;

import android.graphics.Point;

import com.theoc.pixhell.infoboxes.StatInfo;

public abstract class Ship extends GameObject {
	protected static final float defaultFireRate = 1000;
	protected static final int defaultHealth = 100;
	protected static final int defaultDamage = 25;
	
	private StatInfo stats;
	
	public Ship() {
		this(defaultFireRate);
	}
	
	public Ship(float fireRate) {
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	}
	
	public Ship(Point location, Point velocity) {
		this(location, velocity, defaultFireRate);
	}
	
	public Ship(Point location, Point velcity, float fireRate) {
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	}
}
