package com.theoc.pixhell.infoboxes;

/**
 * Box to hold stat info for Ships
 * @author brandon
 *
 */
public class StatInfo {
	private final int screenPressModifier = 2;
	
	private int health;
	private int damage;
	
	private float curFireRate;
	private float baseFireRate;
	
	public StatInfo(int health, int damage, float baseFireRate) {
		this.health = health;
		this.damage = damage;
		this.baseFireRate = baseFireRate;
		this.curFireRate = this.baseFireRate;
	}
	
	public void changeHealth(int amount) {
		health += amount;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public float getCurFireRate() {
		return curFireRate;
	}
	
	public void resetFireRate() {
		curFireRate = baseFireRate;
	}
	
	public void setScreenPressFireRate() {
		curFireRate = screenPressModifier * baseFireRate;
	}
}
