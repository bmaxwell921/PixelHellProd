package com.theoc.pixhell.model;

import com.theoc.pixhell.manager.InputManager;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Player extends Ship {
	int screenheight = 0;
	int screenwidth = 0;

	InputManager inputManager;

	public Player(Bitmap image, InputManager inputManager, int screenwidth,
			int screenheight) {
		this(image, defaultFireRate, inputManager);
		this.screenheight = screenheight;
		this.screenwidth = screenwidth;

	}

	public Player(Bitmap image, float fireRate, InputManager inputManager) {
		super(image, fireRate);
		this.inputManager = inputManager;
	}

	public Player(Bitmap image, Point location, Point velocity,
			InputManager inputManager, int screenwidth, int screenheight) {
		this(image, location, velocity, defaultFireRate, inputManager);
		this.screenheight = screenheight;
		this.screenwidth = screenwidth;

	}

	public Player(Bitmap image, Point location, Point velocity, float fireRate,
			InputManager inputManager) {
		super(image, location, velocity, fireRate);
		this.inputManager = inputManager;
	}

	@Override
	public void update(float time) {

		// TODO Auto-generated method stub
		if (this.position.x < 0 || this.position.x > screenwidth
				|| this.position.y < 0 || this.position.y > screenheight) {
			inputManager.getTiltVector().x = (float) 0;
		}
		this.position.x = this.position.x + this.velocity.x
				* (inputManager.getTiltVector().x < 0 ? -1 : 1);
		this.position.y = this.position.y + this.velocity.y
				* (inputManager.getTiltVector().y < 0 ? -1 : 1);

	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
