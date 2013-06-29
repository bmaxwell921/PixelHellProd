package com.theoc.pixhell.infoboxes;

/**
 * Box to hold stat info for Ships
 * @author brandon
 *
 */
public class StatInfo {
	private final float screenPressModifier = 0.5f;
	
	private int curHealth;
	private int maxHealth;
	private int damage;
	
	private float curFireRate;
	private float baseFireRate;
	
	public StatInfo(int maxHealth, int damage, float baseFireRate) {
		this.curHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.damage = damage;
		this.baseFireRate = baseFireRate;
		this.curFireRate = this.baseFireRate;
	}
	
	public void changeHealth(int amount) {
		curHealth += amount;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getHealth() {
		return curHealth;
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
