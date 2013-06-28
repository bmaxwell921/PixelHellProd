package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Pickup extends GameObject{
	
	public Pickup(Point position, Point velocity, int height, int width,
			Bitmap image) {
		super(position, velocity, height, width, image);
		// TODO Auto-generated constructor stub
	}

	public void update(float time)
	{
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
