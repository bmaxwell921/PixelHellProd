package com.theoc.pixhell.model;

import java.util.Queue;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.theoc.pixhell.utilities.Vector2;

public abstract class Enemy extends Ship {
	private final double CLOSE_DIST = 2;
	Queue<Vector2> pathQueue;
	public Enemy(Bitmap image) {
		this(image, defaultFireRate);
	}

	public Enemy(Bitmap image, Vector2 location) {
		this(image, location, defaultFireRate);
	}
	
	public Enemy(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Enemy(Bitmap image, Vector2 location, float fireRate) {
		this(image, location, DEFAULT_MAX_VEL, fireRate);
	}
	
	public Enemy(Bitmap image, Vector2 location, Vector2 maxVel) {
		super (image, location, maxVel, defaultFireRate);
	}
	
	public Enemy(Bitmap image, Vector2 location, Vector2 maxVel, float fireRate) {
		super(image, location, maxVel, fireRate);
	}
	
	
	public void setPathQueue(Queue<Vector2> queue) {
		pathQueue = queue;
	}
	
	protected void moveToLocation(Vector2 dest, float time) {
		//Moves the current Enemy toward the given point
		Vector2 toward = Vector2.subtract(dest, position);
		toward.normalize();
		Vector2 velTime = Vector2.multiply(maxVel, time * millisToSeconds);
		position.add(Vector2.componentwiseMult(toward, velTime));
	}
	public boolean closeTo(Vector2 targetPos)
    {
		return Vector2.distance(position, targetPos) < CLOSE_DIST;
    }
	@Override
	public void update(float time) {
		if (pathQueue.isEmpty()) {
			isAlive = false;
			return;
		}
		if(closeTo(pathQueue.peek()))
		{
			pathQueue.poll();
		}
		moveToLocation(pathQueue.peek(), time);	
	}
}
