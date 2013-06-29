package com.theoc.pixhell;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.model.GameObject;
import com.theoc.pixhell.model.LevelObject;
import com.theoc.pixhell.utilities.Vector2;



public final class GameView extends View
	implements Observer
{	
	private Paint       brush  = null;
	private LevelObject model  = null;
	public boolean      run    = true;
	private long        framesDrawn = 0;
	
	private InputManager inputManager = null;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setKeepScreenOn(true);
		
		this.brush = new Paint();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		boolean b = false;
		
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			b = true;
			
			if (this.inputManager != null) {
				this.inputManager.setTouched(true);
			}
		} else if (e.getAction() == MotionEvent.ACTION_UP) {
			b = true;
			
			if (this.inputManager != null) {
				this.inputManager.setTouched(false);
			}
		}
		
		return b;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//int width = canvas.getWidth();
		//int height = canvas.getHeight();
		
		//Bitmap bb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		this.brush.setColor(Color.BLACK);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), this.brush);
		
		if (this.model != null) {
			for (GameObject obj : this.model.getOnscreenObjects()) {
				int x = (int) obj.position.x;
				int y = (int) obj.position.y;
				canvas.drawBitmap(obj.image, x, y, this.brush);
			}
		}
		
		this.framesDrawn++;
		this.brush.setColor(Color.WHITE);
		canvas.drawText("Frames Drawn: " + this.framesDrawn, 10, 15, this.brush);
		canvas.drawText("GameRate: " + GameActivity.GAME_RATE, 10, 30, this.brush);
		
		Vector2 dir = this.inputManager.getTiltVector();
		canvas.drawText("Tilt-X: " + dir.x, 10, 60, this.brush);
		canvas.drawText("Tilt-Y: " + dir.y, 10, 75, this.brush);
	}


	@Override
	public void update(Observable observable, Object data) {
		this.postInvalidate();		
	}
	
	public void addModel(LevelObject model) {
		this.model = model;
		this.model.addObserver(this);
	}
	
	public void addInputManager(InputManager input) {
		this.inputManager = input;
	}
}
