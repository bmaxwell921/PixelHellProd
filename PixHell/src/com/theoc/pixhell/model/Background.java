package com.theoc.pixhell.model;

import android.graphics.Bitmap;

public class Background extends GameObject {

	public Background(Bitmap image) {
		
	}
	
	@Override
	public void update(float time) {
		//Do nothing
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		return false;
	}

}
