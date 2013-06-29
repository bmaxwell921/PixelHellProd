package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.utilities.Vector2;

public class Pickup extends GameObject implements Consumable{
	
	public Pickup(Bitmap image, Vector2 position, Vector2 velocity, int width, int height) {
		super(image, position, velocity, width, height);
	}
}
