package com.theoc.pixhell.weaponLaunchers;

import java.util.ArrayList;
import java.util.List;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.model.BulletWeapon;
import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class TriBlasterLauncher extends Launcher {

	private final int xVel = 100;
	
	public TriBlasterLauncher(int damage, int coolDown, boolean isEnemy) {
		super(damage, coolDown, isEnemy);
	}

	@Override
	public void setUniqueKey() {
		uniqueKey = "TRI_BLASTER";		
	}

	@Override
	protected List<Weapon> getProjectiles(Vector2 shipCenter, Vector2 shipSize,
			int direction) {
		List<Weapon> proj = new ArrayList<Weapon>();
		
		//Middle shot
		Vector2 pos = Vector2.add(shipCenter, new Vector2(-Constants.BULLET_WIDTH / 2, 
				-shipSize.y / 2));
		proj.add(new BulletWeapon(super.getBulletImage(), pos, 
				Vector2.multiply(DEFAULT_SPEED, direction), damage));
		
		//Left shot
		pos = Vector2.add(shipCenter, new Vector2(-1.5f * Constants.BULLET_WIDTH,
				-shipSize.y / 2));
		Vector2 vel = new Vector2(-xVel, DEFAULT_SPEED.y * direction);
		proj.add(new BulletWeapon(super.getBulletImage(), pos, 
				vel, damage));
		
		//Right shot
		pos = Vector2.add(shipCenter, new Vector2(Constants.BULLET_WIDTH, 
				-shipSize.y / 2));
		vel = new Vector2(xVel, DEFAULT_SPEED.y * direction);
		proj.add(new BulletWeapon(super.getBulletImage(), pos,
				vel, damage));
		return proj;
	}

}
