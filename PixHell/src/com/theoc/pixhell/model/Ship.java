package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.infoboxes.StatInfo;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Vector2;

public abstract class Ship extends GameObject {
	protected final static Vector2 DEFAULT_MAX_VEL = new Vector2(100, 100);
	protected static final float defaultFireRate = 1000;
	protected static final int defaultHealth = 100;
	protected static final int defaultDamage = 25;
	private static final int shipWidth = 100;
	private static final int shipHeight = 100;
	public float FiringTime;
	
	private StatInfo stats;
	
	
	public Ship(Bitmap image) {
		this(image, defaultFireRate);
		
	}
	
	public Ship(Bitmap image, float fireRate) {
		super(image);
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
		this.FiringTime=this.stats.getCurFireRate();
		
	}
	
	public Ship(Bitmap image, Vector2 location, Vector2 maxVel) {
		this(image, location, maxVel, defaultFireRate);
		
	}
	
	public Ship(Bitmap image, Vector2 location, Vector2 maxVel, float fireRate) {
		super(location, maxVel, shipHeight, shipWidth, image);
		stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
		this.FiringTime=this.stats.getCurFireRate();
		
	}
	
	public void applyDamage(int damage) {
		stats.changeHealth(-1 * damage);
	}
	
	public BulletWeapon Fire(float time)
	{
		if(this.FiringTime < 0)
		{
		this.stats.resetFireRate();
		this.FiringTime=this.stats.getCurFireRate();
		BulletWeapon bullet = new BulletWeapon(new Vector2(this.position.x+1,this.position.y),new Vector2(1,1), 1, 1,AssetMap.getImage(AssetMap.shot));
		return bullet;
		}
		else
		{
		this.FiringTime=this.FiringTime-100;
		return null;
		}
		
	}
	
	
}
