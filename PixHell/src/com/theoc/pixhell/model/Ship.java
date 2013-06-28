package com.theoc.pixhell.model;

import android.graphics.Point;

public abstract class Ship extends GameObject {
	protected static final float defaultFireRate = 1000;
	//Box up the stats???
	public float health;
	private final float fireRate;
	private float lastFired;
	
	
	public Ship() {
		this(defaultFireRate);
	}
	
	public Ship(float baseFireRate) {
		//TODO what are these?
		this(new Point(0, 0), new Point(0, 0), 1000);
	}
	
	public Ship(Point location, Point veloctiy, float baseFireRate) {
		fireRate = baseFireRate;
	}
}
