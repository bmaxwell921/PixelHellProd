package com.theoc.pixhell.model;

import com.theoc.pixhell.manager.InputManager;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Player extends Ship {
	int screenheight = 0;
	int screenwidth = 0;

	InputManager inputmanager;
	public Player(Bitmap image,InputManager inputmanager,int screenheight,int screenwidth) {
		this(image, defaultFireRate);
		this.inputmanager = inputmanager;
		this.screenheight = screenheight;
		this.screenwidth = screenwidth;
	}
	
	public Player(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Player(Bitmap image, Point location, Point velocity,InputManager inputmanager,int screenheight,int screenwidth) {
		this(image, location, velocity, defaultFireRate);
		this.inputmanager = inputmanager;
		this.screenheight = screenheight;
		this.screenwidth = screenwidth;
	}
	
	public Player(Bitmap image, Point location, Point velocity, float fireRate) {
		super(image, location, velocity, fireRate);
	}
	
	@Override
	public void update(float time) {
		
		// TODO Auto-generated method stub
		if(this.position.x <0 || this.position.x >screenwidth ||this.position.y <0 || this.position.y >screenheight)
		{
			inputmanager.getTiltVector().x =  (float) 0;
		}
		this.position.x =this.position.x +  this.velocity.x *(inputmanager.getTiltVector().x <0 ?-1:1);
		this.position.y =this.position.y +  this.velocity.y *(inputmanager.getTiltVector().y <0 ?-1:1);
		
		
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
