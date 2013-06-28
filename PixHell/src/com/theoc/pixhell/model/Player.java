package com.theoc.pixhell.model;

import com.theoc.pixhell.manager.InputManager;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Player extends Ship {

	InputManager inputManager;
	public Player(Bitmap image, InputManager inputManager) {
		this(image, defaultFireRate, inputManager);
		
	}
	
	public Player(Bitmap image, float fireRate, InputManager inputManager) {
		super(image, fireRate);
		this.inputManager = inputManager;
	}
	
	public Player(Bitmap image, Point location, Point velocity, InputManager inputManager) {
		this(image, location, velocity, defaultFireRate, inputManager);
		
	}
	
	public Player(Bitmap image, Point location, Point velocity, float fireRate, 
			InputManager inputManager) {
		super(image, location, velocity, fireRate);
		this.inputManager = inputManager;
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
