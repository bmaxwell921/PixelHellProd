package com.theoc.pixhell.weaponLaunchers;

import java.util.List;

import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public abstract class Launcher {
	protected final Vector2 DEFAULT_SPEED = new Vector2(0, Constants.DEFAULT_WEAPON_SPEED); 
	protected String uniqueKey;
	
	protected int damage;
	
	protected final int COOLDOWN;
	protected int timeLeft;
	
	public Launcher(int damage, int coolDown) {
		this.damage = damage;
		setUniqueKey();
		COOLDOWN = coolDown;
		timeLeft = 0;
	}
	
	public abstract void setUniqueKey();
	
	public boolean canFire(float dt) {
		timeLeft -= dt;
		return timeLeft <= 0;
	}
	
	public List<Weapon> launch(Vector2 shipCenter, Vector2 shipSize, int direction) {
		List<Weapon> proj = getProjectiles(shipCenter, shipSize, direction);
		timeLeft = COOLDOWN;
		return proj;
	}
	
	protected abstract List<Weapon> getProjectiles(Vector2 shipCenter, Vector2 shipSize, int direction);
	
	@Override
	public int hashCode() {
		return uniqueKey.hashCode();
	}
}
