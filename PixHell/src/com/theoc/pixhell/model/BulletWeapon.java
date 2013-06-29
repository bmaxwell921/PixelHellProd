package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class BulletWeapon extends DumbWeapon {

	
	public BulletWeapon(Vector2 position, Vector2 maxVel, int height, int width,
			Bitmap image) {
		super(position, maxVel, height, width, image);
		
	}
	
	public void update(float time)
	{
		
		this.position.y=this.position.y+this.maxVel.y*time;
		
	}

}
