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
		
		this.view = (GameView) this.findViewById(R.id.game_view_primary);
		this.model = new LevelObject();
		this.model.addObserver(this.view);
		
		this.initThread();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (this.updateThread != null) {
			this.updateThread.start();		
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override 
	public void onRestart() {
		super.onRestart();
	}
		
	
	private void initThread() {
		// Set Update Thread
		Runnable runner = 
			new Runnable()
			{
				long cycleRate = 30;
				@Override
				public void run()
				{
					while (view.run)
					{
						try {
							
							Thread.sleep(cycleRate);
							model.update(cycleRate);
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
