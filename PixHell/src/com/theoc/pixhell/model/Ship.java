package com.theoc.pixhell.model;

import java.util.List;

import android.graphics.Bitmap;

import com.theoc.pixhell.infoboxes.StatInfo;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;
import com.theoc.pixhell.weaponLaunchers.BulletLauncher;
import com.theoc.pixhell.weaponLaunchers.Launcher;
import com.theoc.pixhell.weaponLaunchers.WeaponsArray;

public abstract class Ship extends GameObject {
	protected static final int UP = -1;
	protected static final int DOWN = 1;
	protected final static Vector2 DEFAULT_MAX_VEL = new Vector2(100, 100);
	protected static final int missileCooldown = 1000;
	protected static final int bulletCooldown = 500;
	
	protected static final int defaultHealth = 100;
	protected static final int defaultDamage = 5;
	private int fireDirection;
	private WeaponsArray weapons;

	public StatInfo stats;

	public Ship(Bitmap image, Vector2 location, int fireDirection) {
		this(image, location, fireDirection, defaultDamage, defaultDamage);
	}
	
	public Ship(Bitmap image, Vector2 location, int fireDirection, int missileDamage, int bulletDamage) {
		super(image, location, DEFAULT_MAX_VEL, Constants.SHIP_WIDTH, Constants.SHIP_HEIGHT);
		stats = new StatInfo(defaultHealth, missileCooldown, missileDamage, bulletCooldown, bulletDamage);
		this.fireDirection = fireDirection;
		weapons = new WeaponsArray();
		this.addLauncher(new BulletLauncher(bulletDamage, bulletCooldown));
	}
	
	public void setLauncher(Launcher l) {
		weapons.removeAllAndAdd(l);
	}
	
	public void addLauncher(Launcher l) {
		weapons.addLauncher(l);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		checkIsAlive();
	}
	
	protected void checkIsAlive() {
		isAlive = stats.getHealth() > 0;
	}

	public void applyDamage(int damage) {
		stats.changeHealth(-1 * damage);
	}

	public List<Weapon> Fire(float time) {
		return weapons.getFiredWeapons(this.getCenter(), new Vector2(width, height), 
				fireDirection, time);

	}

}










