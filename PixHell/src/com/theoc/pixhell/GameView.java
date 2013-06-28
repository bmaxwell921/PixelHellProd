package com.theoc.pixhell;

import java.util.Observable;
import java.util.Observer;

import com.theoc.pixhell.model.GameObject;
import com.theoc.pixhell.model.LevelObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public final class GameView extends View
	implements Observer
{	
	Paint       brush  = null;
	LevelObject model  = null;
	boolean     run    = true;
	long        framesDrawn = 0;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.brush = new Paint();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		boolean b = false;
		
		return b;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		this.brush.setColor(Color.BLACK);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), this.brush);
		
		if (this.model != null) {
			for (GameObject obj : this.model.getOnscreenObjects()) {
				int x = obj.position.x;
				int y = obj.position.y;
				canvas.drawBitmap(obj.image, x, y, this.brush);
			}
		}
		
		this.framesDrawn++;
		this.brush.setColor(Color.WHITE);
		canvas.drawText("Frames: " + this.framesDrawn, 10, 15, this.brush);
		canvas.drawText("GameRate: " + GameActivity.GAME_RATE, 10, 30, this.brush);
		
	}


	@Override
	public void update(Observable observable, Object data) {
		this.postInvalidate();		
	}
	
	public void addModel(LevelObject model) {
		this.model = model;
		this.model.addObserver(this);
	}
}
