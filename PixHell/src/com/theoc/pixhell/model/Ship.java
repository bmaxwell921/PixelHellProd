package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.theoc.pixhell.infoboxes.StatInfo;

public abstract class Ship extends GameObject {
	protected static final float defaultFireRate = 1000;
	protected static final int defaultHealth = 100;
	protected static final int defaultDamage = 25;
	private static final int shipWidth = 100;
	private static final int shipHeight = 100;
	
	private StatInfo stats;
	
	public Ship(Bitmap image) {
		this(image, defaultFireRate);
	}
	
	public Ship(Bitmap image, float fireRate) {
		super(image);
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	}
	
	public Ship(Bitmap image, Point location, Point velocity) {
		this(image, location, velocity, defaultFireRate);
	}
	
	public Ship(Bitmap image, Point location, Point velocity, float fireRate) {
		super(location, velocity, shipHeight, shipWidth, image);
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	}
}
