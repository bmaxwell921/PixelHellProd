package com.theoc.pixhell.model;

public class Explosion {
	public final int LifeTime =10000;
	public int Timeleft;
	public boolean isAlive;
	public final int MilliSeconds =500;
	
	
	public Explosion() {
		super();
		Timeleft =LifeTime;
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
