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
	
	private float curFireRate;
	private float baseFireRate;
	
	private int missileCooldown;
	private int missileDamage;
	
	private int bulletCooldown;
	private int bulletDamage;
	
	public StatInfo(int maxHealth, int missileCooldown, int missileDamage,
			int bulletCooldown, int bulletDamage) {
		this.curHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.missileCooldown = missileCooldown;
		this.missileDamage = missileDamage;
		this.bulletCooldown = bulletCooldown;
		this.bulletDamage = bulletDamage;
//		this.baseFireRate = baseFireRate;
//		this.curFireRate = this.baseFireRate;
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
	
	public void restoreHealth() {
		this.curHealth = this.maxHealth;
	}
	
	public int getMissileCooldown() {
		return missileCooldown;
	}
	
	public int getMissileDamage() {
		return missileDamage;
	}
	
	public int getBulletCooldown() {
		return bulletCooldown;
	}
	
	public int getBulletDamage() {
		return bulletDamage;
	}
//	public float getCurFireRate() {
//		return curFireRate;
//	}
//	
//	public void resetFireRate() {
//		curFireRate = baseFireRate;
//	}
//	
//	public void setScreenPressFireRate() {
//		if (curFireRate < screenPressModifier * baseFireRate) {
//			curFireRate = screenPressModifier * baseFireRate;
//		}
//	}
}
