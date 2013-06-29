package com.theoc.pixhell;

import java.io.IOException;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.manager.SoundManager;
import com.theoc.pixhell.model.LevelObject;

import com.amazon.insights.AmazonInsights;
import com.amazon.insights.CustomEvent;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;

public class GameActivity extends Activity
{
	public static final long GAME_RATE = 16;
	public static final long FRAME_RATE = 32;
	
	private AssetManager 	assetMgr  = null;
	
	private Thread       gameThread   = null;
	//private Thread dispThread;
	private GameView     view         = null;
	private LevelObject  model        = null;
	private InputManager inputManager = null;
	private SoundManager soundManager = null;
	
	int sid = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_game);
		this.assetMgr = this.getAssets();
		
		// Configure the Amazon Insights SDK
		AmazonInsights
		    .withApplicationKey("f8f453bb8cf44935871480432bf58224")
		    .withPrivateKey("M3TQ5VNO778YBH")
		    .withContext(getApplicationContext())
		    .initialize();
		
		AudioManager am   = (AudioManager) getSystemService(AUDIO_SERVICE);
		SoundPool    sp   = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		MediaPlayer  mp   = new MediaPlayer();
		this.inputManager = new InputManager((SensorManager) this.getSystemService(SENSOR_SERVICE));
		this.setSensorListners();
		try
		{
			Context context = this.getBaseContext();
			AssetMap.init(this.assetMgr);
			mp.setDataSource(this.assetMgr.openFd("mus/game_theme.mp3").getFileDescriptor()); 
			mp.prepare();
			sid = sp.load(context, AssetMap.MENU_SELECT_START, 1);
			sp.load(context, R.raw.menu_select01, 2);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		this.soundManager = new SoundManager(am, mp, sp);
		
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		// link M-V
		this.model = new LevelObject(width, height, this.inputManager, this.soundManager);
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
		this.soundManager.startTheme();
		
		CustomEvent.create("_session.start").record();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		this.soundManager.pauseTheme();
		CustomEvent.create("_session.pause").record();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.soundManager.resumeTheme();
		CustomEvent.create("_session.resume").record();
	}
	
	@Override 
	public void onRestart() {
		super.onRestart();
		this.soundManager.startTheme();
		CustomEvent.create("_session.start").record();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.gameThread.interrupt();
		this.soundManager.stopTheme();
		CustomEvent.create("_session.stop").record();
		
		// MWAHAHAHAHAHAHAHAHAHAHA!!!!! - Force Custom Event Submission
		Context context = getApplicationContext();
		Intent intent = new Intent(context, com.amazon.insights.InsightsProcessingService.class);
		intent.setAction("SubmitMeasurements");
		intent.putExtra("force", true);
		context.startService(intent);
		android.util.Log.w("f8f453bb8cf44935871480432bf58224", "DEBUG CODE TO FORCE SUBMISSION: I am a horrible person [^_^]");
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
					long tick = 0;
					while (view.run)
					{
						try {
							Thread.sleep(GAME_RATE);
							model.update(GAME_RATE);	
							
							tick += GAME_RATE;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if (tick > 1000) {
							tick = 0;
							Log.i("tick", "tock");
							soundManager.playSoundEffect(sid);
							//soundManager.playSoundEffect(AssetMap.MENU_SELECT_START);
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
