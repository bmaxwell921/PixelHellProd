package com.theoc.pixhell.infoboxes;

/**
 * Box to hold stat info for Ships
 * @author brandon
 *
 */
public class StatInfo {
	private int health;
	private int damage;
	
	private int curFireRate;
	private int baseFireRate;
	
	public StatInfo(int health, int damage, int baseFireRate) {
		this.health = health;
		this.damage = damage;
		this.baseFireRate = baseFireRate;
		this.curFireRate = this.baseFireRate;
	}
}
