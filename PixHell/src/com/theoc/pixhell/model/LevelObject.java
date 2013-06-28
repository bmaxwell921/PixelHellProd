package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class LevelObject extends Observable
{
	public Bitmap background;
	
	public LevelObject() {
		//Bitmap image = BimapFacotyr.decodestream(assets.open("file"))
		background = BitmapFactory.decodeStream(assets.open("Background.png"));
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
