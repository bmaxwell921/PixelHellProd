package com.theoc.pixhell.model;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class Explosion extends GameObject {
	public final int LifeTime =10000;
	public int timeLeft;
	public final int milliSeconds =2000;
	
	
	
	public Explosion(Vector2 position){
		super(AssetMap.getImage(AssetMap.explosion), position, Vector2.ZERO, 
				Constants.EXPLOSION_WIDTH, Constants.EXPLOSION_HEIGHT);		
		timeLeft = milliSeconds;
	}

	@Override
	public void update(float time)
	{
		if(!isAlive)
		{
			return;
		}
		
		if(timeLeft < 0)
		{
			this.isAlive=false;
		}
		timeLeft -= milliSeconds; 
	}

}
