package com.theoc.pixhell;

import com.theoc.pixhell.model.LevelObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameActivity extends Activity
{
	private Thread updateThread;
	private GameView view;
	private LevelObject model;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	
	private void initThread() {
		// Set Update Thread
		Runnable runner = 
			new Runnable()
			{
				@Override
				public void run()
				{
					while (model.active)
					{
						try {
							Thread.sleep(30);
							model.relaySensors(mValuesAccel, mValuesMagnet);
							model.update();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					finish();
				}
			};
		this.updateThread = new Thread(runner);
	}
}
