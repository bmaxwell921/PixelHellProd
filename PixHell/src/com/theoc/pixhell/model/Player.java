package com.theoc.pixhell.model;

import android.graphics.Point;

public class Player extends Ship {

	
	public Player() {
		super(defaultFireRate);
	}
	
	public Player(float fireRate) {
		super(fireRate);
	}
	
	public Player(Point location, Point velocity) {
		super(location, velocity, defaultFireRate);
	}
	
	public Player(Point location, Point velocity, float fireRate) {
		super(location, velocity, fireRate);
	}
	
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
