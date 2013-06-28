package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Player extends Ship {

	
	public Player(Bitmap image) {
		super(image, defaultFireRate);
	}
	
	public Player(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Player(Bitmap image, Point location, Point velocity) {
		super(image, location, velocity, defaultFireRate);
	}
	
	public Player(Bitmap image, Point location, Point velocity, float fireRate) {
		super(image, location, velocity, fireRate);
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
