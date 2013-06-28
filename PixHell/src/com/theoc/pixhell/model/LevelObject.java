package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.graphics.Bitmap;

public class LevelObject extends Observable
{
	public Bitmap background;
	
	public LevelObject() {
		
	}
	
	public void update(long timeElapsed) {
		//
		
		
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public List<GameObject> getOnscreenObjects() {
		return new ArrayList<GameObject>();
	}
}
