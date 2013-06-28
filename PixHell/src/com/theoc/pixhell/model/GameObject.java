package com.theoc.pixhell.model;

import android.graphics.Point;

public abstract class GameObject {
	
	public Point position;
	public Point velocity;
	public float height;
	public float width;
	
	public abstract void update(float time);
	public abstract boolean CollidesWith(GameObject gameObject);

}
