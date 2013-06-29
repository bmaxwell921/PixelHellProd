package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class BulletWeapon extends DumbWeapon {

	
	public BulletWeapon(Bitmap image, Vector2 position, Vector2 maxVel, int height, int width) {
		super(image, position, maxVel, height, width);
		
	}
	
	public void update(float time)
	{
		
		this.position.y=this.position.y+this.maxVel.y*time;
		
	}

}
