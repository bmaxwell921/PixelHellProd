package com.theoc.pixhell.model;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Grunt extends Enemy {

	public Grunt(Bitmap image, Point position, Point velocity) {
		super(image, position, velocity);
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
