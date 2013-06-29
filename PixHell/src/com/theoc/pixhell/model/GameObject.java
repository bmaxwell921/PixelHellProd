package com.theoc.pixhell.model;


import android.graphics.Bitmap;
import android.graphics.Rect;

import com.theoc.pixhell.utilities.Vector2;


public abstract class GameObject {
	protected final float millisToSeconds = 1 / 1000f;
	public Vector2 position;
	public Vector2 maxVel;
	public int height;
	public int width;
	public Bitmap image;
	
	public boolean isAlive;
	
	public GameObject(Bitmap image) {
		this.image = image;
		this.position = Vector2.ZERO;
		this.maxVel = Vector2.ZERO;
		isAlive = true;
	}
	
	public GameObject(Vector2 position, Vector2 maxVel, int height, int width,
			Bitmap image) {
		super();
		this.position = position;
		this.maxVel = maxVel;
		this.height = height;
		this.width = width;
		this.image = image;
	}
	
	public void update(float time)
	{
		position.add(Vector2.multiply(maxVel, time));
	}
	public boolean CollidesWith(GameObject gameObject) {
		return this.RectBoxforCollision().intersect(gameObject.RectBoxforCollision());
	}
	
	public  Rect RectBoxforCollision()
	{ 
		Rect rectangle = new Rect((int)position.x, (int)position.y,
				(int)position.x+width,(int)position.y+height); 
		return rectangle;		
	}


	

}
