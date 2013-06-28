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
	
	public  void update(float time)
	{
		
		position.x =(int) (position.x*velocity.x*time);
		position.y =(int) (position.y*velocity.y*time);
	}
	public abstract boolean CollidesWith(GameObject gameObject);
	
	
	public GameObject(Point position, Point velocity, int height, int width,
			Bitmap image) {
		super();
		this.position = position;
		this.velocity = velocity;
		this.height = height;
		this.width = width;
		this.image = image;
	}
	public  Rect RectBoxforCollision()
	{ 
		Rect Rectangle = new Rect(position.x,position.y,position.x+width,position.y+height); 
		
		
		return Rectangle;
		
	}


	

}
