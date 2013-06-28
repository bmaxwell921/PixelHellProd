package com.theoc.pixhell.model;

import com.theoc.pixhell.manager.InputManager;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Player extends Ship {

	InputManager inputmanager;
	public Player(Bitmap image,InputManager inputmanager) {
		this(image, defaultFireRate);
		this.inputmanager = inputmanager;
	}
	
	public Player(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Player(Bitmap image, Point location, Point velocity,InputManager inputmanager) {
		this(image, location, velocity, defaultFireRate);
		this.inputmanager = inputmanager;
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
