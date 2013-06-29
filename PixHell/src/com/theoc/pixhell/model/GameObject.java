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
	
	public GameObject(Bitmap image, int width, int height) {
		this(image, Vector2.ZERO, Vector2.ZERO, width, height);
	}
	
	public GameObject(Bitmap image, Vector2 position, Vector2 maxVel, int width, int height) {
		super();
		this.position = position;
		this.maxVel = maxVel;
		this.height = height;
		this.width = width;
		this.image = image;
		isAlive = true;
	}
	
	public void update(float time)
	{
		position.add(Vector2.multiply(maxVel, time * millisToSeconds));
	}
	
	public boolean CollidesWith(GameObject gameObject) {
		return Rect.intersects(this.RectBoxforCollision(), gameObject.RectBoxforCollision());
	}
	
	public  Rect RectBoxforCollision()
	{ 
		Rect rectangle = new Rect((int)position.x, (int)position.y,
				(int)position.x+width,(int)position.y+height); 
		return rectangle;		
	}

	public Vector2 getCenter() {
		return new Vector2(position.x + width / 2, position.y + height / 2);
	}
	

}
