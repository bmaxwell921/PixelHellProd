package com.theoc.pixhell.model;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class Coins extends GameObject {
	public final int LifeTime =30000;
	public int timeLeft;
	
	public Coins(Vector2 position){
		super(AssetMap.getImage(AssetMap.coin), position, new Vector2(0, 50), 
				Constants.COIN_WIDTH, Constants.COIN_HEIGHT);		
		timeLeft = LifeTime;
	}

	@Override
	public void update(float time)
	{
		super.update(time);
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