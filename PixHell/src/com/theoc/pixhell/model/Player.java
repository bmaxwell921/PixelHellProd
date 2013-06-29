package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Rect;

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
	
	@Override
	public Rect RectBoxforCollision()
	{ 
		Vector2 center = this.getCenter();
		int halfWidth = this.width / 2;
		int halfHeight = this.height / 2;
		Rect rectangle = new Rect((int)(center.x - halfWidth / 2f), (int)(center.y - halfHeight / 2f), 
				(int)(center.x + halfWidth / 2), (int)(center.y + halfHeight / 2));
		return rectangle;	
	}
	
	private boolean isOutOfBounds(Vector2 tempPos) {
		return (tempPos.x < 0 || tempPos.x + width > screenWidth
				|| tempPos.y < 0 || tempPos.y + height + buffer > screenHeight);
	}
}
