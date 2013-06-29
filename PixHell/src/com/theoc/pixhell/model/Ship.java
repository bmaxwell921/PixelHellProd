package com.theoc.pixhell.model;

import android.graphics.Bitmap;

import com.theoc.pixhell.infoboxes.StatInfo;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public abstract class Ship extends GameObject {
	protected static final int UP = -1;
	protected static final int DOWN = 1;
	protected final static Vector2 DEFAULT_MAX_VEL = new Vector2(100, 100);
	protected static final float defaultFireRate = 1000;
	protected static final int defaultHealth = 100;
	protected static final int defaultDamage = 5;
	
	private int fireDirection;

	public float FiringTime;

	public StatInfo stats;

	public Ship(Bitmap image, Vector2 location, int fireDirection) {
		this(image, location, fireDirection, defaultDamage);
	}
	
	public Ship(Bitmap image, Vector2 location, int fireDirection, int damage) {
		super(image, location, DEFAULT_MAX_VEL, Constants.SHIP_WIDTH, Constants.SHIP_HEIGHT);
		stats = new StatInfo(defaultHealth, damage, defaultFireRate);
		this.FiringTime = this.stats.getCurFireRate();
		this.fireDirection = fireDirection;
	}

	//
	// public Ship(Bitmap image, float fireRate) {
	// super(image);
	// stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	// this.FiringTime = this.stats.getCurFireRate();
	//
	// }
	//
	// public Ship(Bitmap image, Vector2 location, Vector2 maxVel) {
	// this(image, location, maxVel, defaultFireRate);
	//
	// }
	//
	// public Ship(Bitmap image, Vector2 location, Vector2 maxVel, float
	// fireRate) {
	// super(location, maxVel, shipHeight, shipWidth, image);
	// stats = new StatInfo(defaultHealth, defaultDamage, fireRate);
	// this.FiringTime = this.stats.getCurFireRate();
	// }
	
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

	public Weapon Fire(float time) {
		if (this.FiringTime < 0) {
			this.stats.resetFireRate();
			this.FiringTime = this.stats.getCurFireRate();
			Weapon weapon = new BulletWeapon(
					AssetMap.getImage(AssetMap.missile), new Vector2(

					this.position.x + (Constants.SHIP_WIDTH) / 2
							- (Constants.BULLET_WIDTH) / 2, this.position.y + (this.height * fireDirection)),
					new Vector2(0, fireDirection),stats.getDamage());
			return weapon;

		} else {

			this.FiringTime = this.FiringTime - time;

			return null;
		}

	}

}










