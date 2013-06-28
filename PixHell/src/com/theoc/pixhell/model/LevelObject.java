package com.theoc.pixhell.model;

import java.util.Observable;

public class LevelObject extends Observable
{
	public LevelObject() {
		
	}
	
	public void update(long timeElapsed) {
		
		
		this.setChanged();
		this.notifyObservers();
	}
}
