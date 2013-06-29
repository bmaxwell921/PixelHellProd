package com.theoc.pixhell.model;

import java.util.Queue;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class Enemy extends Ship {
	Queue<Point> pathQueue;
	public Enemy(Bitmap image) {
		this(image, defaultFireRate);
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
	
	
	public void setPathQueue(Queue<Point> queue) {
		pathQueue = queue;
	}
	
	protected void moveToLocation(Point dest,float time) {
		//Moves the current Enemy toward the given point
		this.position.x =(int) (this.position.x+this.velocity.x*time);
		this.position.y =(int) (this.position.y+this.velocity.y*time);
			
	}
	public boolean closeTo(Point targetPos)
    {
		boolean b =false;
		int xPos=targetPos.x-this.position.x;
		int yPos=targetPos.y-this.position.y;
		Point newPoint= new Point(xPos, yPos);
		
		if((newPoint.x-this.velocity.x)+(newPoint.y-this.velocity.y)<.5)
		{
			b=true;
		}
		return b; 
        
    }
	@Override
	public void update(float time) {
		if(closeTo(pathQueue.peek()))
		{
			pathQueue.poll();
		}
		moveToLocation(pathQueue.peek(),time);
		
		
	}

	@Override
	public boolean CollidesWith(GameObject gameObject) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
