package com.theoc.pixhell.model;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class Coins extends GameObject {
	public final int LifeTime =10000;
	public int timeLeft;
	public final int milliSeconds = 1000;
	
	
	
	public Coins(Vector2 position){
		super(AssetMap.getImage(AssetMap.coin), position, Vector2.ZERO, 
				Constants.COIN_WIDTH, Constants.COIN_HEIGHT);		
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
		timeLeft -= time; 
	}

}