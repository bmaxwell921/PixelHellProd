package com.theoc.pixhell.model;

import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.utilities.DirectionalVector;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;

public class Player extends Ship {
	private final int LEFT = -1;
	private final int RIGHT = 1;
	private final int UP = -1;
	private final int DOWN = 1;
	
	private int screenheight;
	private int screenwidth;

	InputManager inputManager;

	public Player(Bitmap image, InputManager inputManager, int screenwidth,
			int screenheight) {
		this(image, defaultFireRate, inputManager, screenwidth, screenheight);

	}

	public Player(Bitmap image, float fireRate, InputManager inputManager, int screenWidth, 
			int screenHeight) {
		super(image, fireRate);
		this.inputManager = inputManager;
		this.screenheight = screenHeight;
		this.screenwidth = screenWidth;
	}

	public Player(Bitmap image, Point location, Point velocity,
			InputManager inputManager, int screenwidth, int screenheight) {
		this(image, location, velocity, defaultFireRate, inputManager, screenwidth,
				screenheight);
		

	}

	public Player(Bitmap image, Point location, Point velocity, float fireRate,
			InputManager inputManager, int screenHeight, int screenWidth) {
		super(image, location, velocity, fireRate);
		this.inputManager = inputManager;
		this.screenheight = screenHeight;
		this.screenwidth = screenWidth;
	}

	@Override
	public void update(float time) {
		DirectionalVector<Float> tilt = inputManager.getTiltVector();
		int dx = velocity.x * ((tilt.x < 0) ? LEFT : RIGHT);
		int dy = velocity.y * ((tilt.y < 0) ? UP : DOWN);
		
		this.position.x += dx;
		this.position.y += dy;
		
		if (isOutOfBounds()) {
			
		}
		
		
		if (this.position.x < 0 || this.position.x > screenwidth
				|| this.position.y < 0 || this.position.y > screenheight) {
			inputManager.getTiltVector().x = (float) 0;
		}
		this.position.x = this.position.x + this.velocity.x
				* (inputManager.getTiltVector().x < 0 ? -1 : 1);
		this.position.y = this.position.y + this.velocity.y
				* (inputManager.getTiltVector().y < 0 ? -1 : 1);
	}
	
	private boolean isOutOfBounds() {
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
