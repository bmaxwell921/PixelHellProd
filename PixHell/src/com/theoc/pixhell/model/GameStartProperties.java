package com.theoc.pixhell.model;

import java.util.ArrayList;

public final class GameStartProperties
{
	public ArrayList<PersistentConsumable> defaultInventory;
	public int difficulty;
	public int wallet;
	
	public GameStartProperties(ArrayList<PersistentConsumable> defIn, int diff, int w) {
		this.defaultInventory = defIn;
		this.difficulty = diff;
		this.wallet = w;
	}
}
