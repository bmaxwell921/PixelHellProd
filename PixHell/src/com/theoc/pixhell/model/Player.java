package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.utilities.Vector2;

public class Player extends Ship {
	private int screenHeight;
	private int screenWidth;
	private final int buffer = 300;
	
	private static final int missileDamage = 100;
	private static final int bulletDamage = 25;

	InputManager inputManager;

	public Player(Bitmap image, InputManager inputManager, int screenWidth, int screenHeight) {
		this(image, inputManager, screenWidth, screenHeight, UP);
		
	}
	
	public Player(Bitmap image, InputManager im, int screenWidth, int screenHeight, int damage) {
		super(image, new Vector2(screenWidth / 2, screenHeight / 2), UP, missileDamage, bulletDamage);
		this.inputManager = im;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	public void update(float time) {
		Vector2 tilt = inputManager.getTiltVector();
		float dx = maxVel.x * tilt.x * time * millisToSeconds;
		float dy = maxVel.y * tilt.y * time * millisToSeconds;
		
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
		return (tempPos.x < 0 || tempPos.x + width > screenWidth
				|| tempPos.y < 0 || tempPos.y + height + buffer > screenHeight);
	}
}
