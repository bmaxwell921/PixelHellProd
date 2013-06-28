package com.theoc.pixhell.model;

import android.graphics.Bitmap;

public class Boss extends Enemy {

	public Boss(Bitmap image) {
		super(image);
	}
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
