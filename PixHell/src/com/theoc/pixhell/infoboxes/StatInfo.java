package com.theoc.pixhell.infoboxes;

/**
 * Box to hold stat info for Ships
 * @author brandon
 *
 */
public class StatInfo {
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
}
