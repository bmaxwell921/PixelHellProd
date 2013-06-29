package com.theoc.pixhell;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.model.GameObject;
import com.theoc.pixhell.model.LevelObject;
import com.theoc.pixhell.utilities.GameState;

public final class GameView extends View
	implements Observer
{	
	private Paint       brush  = null;
	private LevelObject model  = null;
	public boolean      run    = true;
	
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
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		//- Draw ALL objects --------------------------------
		if (this.model != null) {
			for (GameObject obj : this.model.getOnscreenObjects()) {
				int x = (int) obj.position.x;
				int y = (int) obj.position.y;
				canvas.drawBitmap(obj.image, x, y, this.brush);
			}
		}
		
		//- Health Bar ----------------------------------------
		int health = this.model.player.stats.getHealth();
		double h_1 = (100 - health) * 2.56d;
		double h_2 = (health / 100d) * 250d;
		this.brush.setColor(Color.rgb((int) h_1, (int) h_2, (int) h_1 / 2));
		int hu = height / 100;
		int wu = width / 100;
		canvas.drawRect(0, (100-health) * hu, wu, height, this.brush);
		
		//- Paused -------------------------------------------
		if (this.model.isPaused()) {
			this.brush.setColor(Color.GRAY);
			this.brush.setAlpha(128);
			canvas.drawRect(0, 0, width, height, this.brush);
		}
		
		//- KillScreen ---------------------------------------
		if (this.model.curGameState == GameState.TEAR_DOWN) {
			this.brush.setColor(Color.RED);
			this.brush.setAlpha(128);
			canvas.drawRect(0, 0, width, height, this.brush);
		}		
		
		//- Verbose Text --------------------------------------
		this.brush.setColor(Color.WHITE);
		canvas.drawText("Score: " + this.model.getScore(), 10, 20, this.brush);
		canvas.drawText("Coins: " + this.model.getCoinNumber(), 10, 35, this.brush);
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
