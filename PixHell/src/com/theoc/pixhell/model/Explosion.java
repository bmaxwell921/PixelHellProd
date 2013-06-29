package com.theoc.pixhell.model;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Vector2;

public class Explosion extends GameObject {
	public final int LifeTime =10000;
	public int Timeleft;
	public boolean isAlive;
	public final int MilliSeconds =500;
	public Vector2 position;
	
	
	
	public Explosion(Vector2 position){
		super(AssetMap.getImage(AssetMap.explosion),100,100);
		this.position=position;
		
	}
	
	


	public void Update(int time)
	{
		if(!isAlive)
		{
			return;
		}
		
		if(Timeleft < 0)
		{
			this.isAlive=false;
		}
		Timeleft -=MilliSeconds; 
	}

}
