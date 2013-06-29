package com.theoc.pixhell.model;

import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.Queue;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class Enemy extends Ship {
	Queue<Point> Pathqueue;
	Point TargetPos;
	public Enemy(Bitmap image) {
		this(image, defaultFireRate);
	}
	
	public void setTargetPos(Point targetPos) {
		TargetPos = targetPos;
	}

	public Enemy(Bitmap image, float fireRate) {
		super(image, fireRate);
	}
	
	public Enemy(Bitmap image, Point location, Point velocity) {
		super (image, location, velocity, defaultFireRate);
	}
	
	public Enemy(Bitmap image, Point location, Point velocity, float fireRate) {
		super(image, location, velocity, fireRate);
	}
	
	protected void moveToLocation(Point dest,float time) {
		//Moves the current Enemy toward the given point
		this.position.x =(int) (this.position.x+this.velocity.x*time);
		this.position.y =(int) (this.position.y+this.velocity.y*time);
			
	}
	public boolean closeTo(Point targetPos)
    {
		int xPos=targetPos.x-this.position.x;
		int yPos=targetPos.y-this.position.y;
		return false;
        
    }
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		setTargetPos(Pathqueue.peek());
		if(closeTo(TargetPos))
		{
			Pathqueue.poll();
		}
		moveToLocation(Pathqueue.peek(),time);
		
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
