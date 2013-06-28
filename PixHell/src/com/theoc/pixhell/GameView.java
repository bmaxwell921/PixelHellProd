package com.theoc.pixhell;

import java.util.Observable;
import java.util.Observer;

import com.theoc.pixhell.model.LevelObject;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public final class GameView extends View
	implements Observer
{
	LevelObject model;
	

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		boolean b = false;
		
		return b;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		
	}


	@Override
	public void update(Observable observable, Object data)
	{
		this.postInvalidate();		
	}
}
