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
	private final int ZERO = 0;
	
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
		DirectionalVector<Integer> tilt = inputManager.getTiltVector();
		int dx = velocity.x * ((tilt.x < 0) ? LEFT : ((tilt.x == 0) ? ZERO : RIGHT));
		int dy = velocity.y * ((tilt.y < 0) ? UP : ((tilt.y == 0) ? ZERO : DOWN));
		
		Point tempPos = new Point(position);
		tempPos.x += dx;
		if (!isOutOfBounds(tempPos)) {
			this.position.x += dx;
		}
		tempPos.x -= dx;
		tempPos.y += dy;
		if (!isOutOfBounds(tempPos)) {
			this.position.y += dy;
		}
	}
	
	private boolean isOutOfBounds(Point tempPos) {
		return (tempPos.x < 0 || tempPos.x + width > screenwidth
				|| tempPos.y < 0 || tempPos.y + height > screenheight);
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
