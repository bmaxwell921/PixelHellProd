package com.theoc.pixhell.model;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;


public abstract class GameObject {
	public Point position;
	public Point velocity;
	public int height;
	public int width;
	public Bitmap image;
	
	public abstract void update(float time);
	public abstract boolean CollidesWith(GameObject gameObject);
	
	
	public  Rect RectBoxforCollision()
	{ 
		Rect Rectangle = new Rect(position.x,position.y,position.x+width,position.y+height); 
		
		
		return Rectangle;
		
	}


	

}
