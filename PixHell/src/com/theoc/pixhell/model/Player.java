package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.utilities.DirectionalVector;
import com.theoc.pixhell.utilities.Vector2;

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

	public Player(Bitmap image, Vector2 location, Vector2 maxVel,
			InputManager inputManager, int screenwidth, int screenheight) {
		this(image, location, maxVel, defaultFireRate, inputManager, screenwidth,
				screenheight);
		

	}

	public Player(Bitmap image, Vector2 location, Vector2 maxVel, float fireRate,
			InputManager inputManager, int screenWidth, int screenHeight) {
		super(image, location, maxVel, fireRate);
		this.inputManager = inputManager;
		this.screenheight = screenHeight;
		this.screenwidth = screenWidth;
	}

	@Override
	public void update(float time) {
		DirectionalVector<Integer> tilt = inputManager.getTiltVector();
		float dx = maxVel.x * ((tilt.x < 0) ? LEFT : ((tilt.x == 0) ? ZERO : RIGHT));
		float dy = maxVel.y * ((tilt.y < 0) ? UP : ((tilt.y == 0) ? ZERO : DOWN));
		
		Vector2 tempPos = new Vector2(position);
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
	
	private boolean isOutOfBounds(Vector2 tempPos) {
		return (tempPos.x < 0 || tempPos.x + width > screenwidth
				|| tempPos.y < 0 || tempPos.y + height > screenheight);
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
