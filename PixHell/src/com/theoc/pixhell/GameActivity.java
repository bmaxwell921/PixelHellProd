package com.theoc.pixhell;

import java.io.IOException;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.model.LevelObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;

public class GameActivity extends Activity
{
	public static final long GAME_RATE = 16;
	public static final long FRAME_RATE = 32;
	
	private AssetManager 	assetMgr;
	
	private Thread       gameThread;
	//private Thread dispThread;
	private GameView     view;
	private LevelObject  model;
	private InputManager inputManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_game);
		this.assetMgr = this.getAssets();
		
		this.setSensorListners();
		try
		{
			AssetMap.init(this.assetMgr);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// link M-V
		this.model = new LevelObject(this.inputManager);
		this.view = (GameView) this.findViewById(R.id.game_view_primary);	
		this.view.addInputManager(this.inputManager);
		this.view.addModel(this.model);
		
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
		if (this.gameThread != null) {
			this.gameThread.start();		
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
		
	
	// Register the event listener and sensor type.
    private void setSensorListners()
    {
    	// Accelerometer (Tilt)
        this.inputManager.sensorManager.registerListener(this.inputManager.mEventListener, this.inputManager.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
                SensorManager.SENSOR_DELAY_GAME);
        // Magnetic Field (Compass Orientation)
        this.inputManager.sensorManager.registerListener(this.inputManager.mEventListener, this.inputManager.sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), 
                SensorManager.SENSOR_DELAY_GAME);
    }
	
	private void initThread() {
		// Set Update Thread
		Runnable game_runner = 
			new Runnable()
			{

				@Override
				public void run()
				{
					while (view.run)
					{
						try {
							Thread.sleep(GAME_RATE);
							model.update(GAME_RATE);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					finish();
				}
			};
		this.gameThread = new Thread(game_runner);
		
		/*Runnable disp_runner = 
				new Runnable()
				{

					@Override
					public void run()
					{
						while (view.run)
						{
							try {
								Thread.sleep(GAME_RATE);
								model.update(GAME_RATE);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						finish();
					}
				};
		this.dispThread = new Thread(disp_runner);*/
	}
}
