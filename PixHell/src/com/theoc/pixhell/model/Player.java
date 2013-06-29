package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.DirectionalVector;
import com.theoc.pixhell.utilities.Vector2;

public class Player extends Ship {
	private int screenHeight;
	private int screenWidth;

	InputManager inputManager;

	public Player(Bitmap image, InputManager inputManager, int screenwidth,
			int screenheight) {
		this(image, defaultFireRate, inputManager, screenwidth, screenheight);

	}

	public Player(Bitmap image, float fireRate, InputManager inputManager, int screenWidth, 
			int screenHeight) {
		super(image, fireRate);
		this.inputManager = inputManager;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.maxVel = DEFAULT_MAX_VEL;
		this.position = new Vector2(screenWidth / 2, screenHeight / 2);
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
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
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
				|| tempPos.y < 0 || tempPos.y + height > screenHeight);
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}
	public BulletWeapon Fire(float time) {
		if (this.FiringTime < 0) {
			this.stats.resetFireRate();
			
			if (!this.inputManager.screenIsTouched())
			{
				this.stats.resetFireRate();
			}
			else
			{
				this.stats.setScreenPressFireRate();
			}
			this.FiringTime = this.stats.getCurFireRate();
			BulletWeapon bullet = new BulletWeapon(new Vector2(

					this.position.x +(Constants.SHIP_WIDTH)/2-(Constants.BULLET_WIDTH)/2, this.position.y), new Vector2(-1, -1),
					1, 1, AssetMap.getImage(AssetMap.shot));
			return bullet;
		} else {

			this.FiringTime = this.FiringTime - time;

			return null;
		}

	}

}
