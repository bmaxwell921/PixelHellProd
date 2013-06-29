package com.theoc.pixhell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.amazon.insights.AmazonInsights;
import com.amazon.insights.CustomEvent;
import com.google.gson.Gson;
import com.theoc.pixhell.db.StoreItemDTO;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.manager.SoundManager;
import com.theoc.pixhell.model.*;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.GameState;
import com.theoc.pixhell.utilities.Preferences;

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
			AssetMap.initImage(this.assetMgr);
			AssetMap.initSound(context, sp);
			mp.setDataSource(this.assetMgr.openFd("mus/game_theme.mp3").getFileDescriptor()); 
			mp.prepare();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		this.soundManager = new SoundManager(am, mp, sp);
		
		
		this.getPresistentPref();
		HashMap<String, Integer> skuMap = this.getStoreData();
		int health = skuMap.get(Constants.HEALTH_SKU);	
		//int life = skuMap.get(Constants.LIFE_SKU);	
		ArrayList<PersistentConsumable> defInv = new ArrayList<PersistentConsumable>();
		for (int i = 0; i < health; i++) {
			defInv.add(new HealthConsumable());
		}
		/*for (int i = 0; i < life; i++) {
			defInv.add(new HealthConsumable());
		}*/
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		// link M-V
		this.model = new LevelObject(width, height, defInv, this.inputManager, this.soundManager);
		this.view = (GameView) this.findViewById(R.id.game_view_primary);	
		this.view.addInputManager(this.inputManager);
		this.view.addModel(this.model);
		
		this.initThread();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
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
		this.model.resume();
		
		CustomEvent.create("_session.start").record();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		this.soundManager.pauseTheme();
		this.model.pause();
		CustomEvent.create("_session.pause").record();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		this.soundManager.resumeTheme();
		this.model.resume();
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
		this.model.pause();
		this.soundManager.stopTheme();
		CustomEvent.create("_session.stop").record();
		
		// MWAHAHAHAHAHAHAHAHAHAHA!!!!! - Force Custom Event Submission
		Context context = getApplicationContext();
		Intent intent = new Intent(context, com.amazon.insights.InsightsProcessingService.class);
		intent.setAction("SubmitMeasurements");
		intent.putExtra("force", true);
		context.startService(intent);
		android.util.Log.w("f8f453bb8cf44935871480432bf58224", "CODE TO FORCE SUBMISSION: I am a horrible person [^_^]");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.weapons:
	    		model.pause();
	    		return true;
	        case R.id.bullet:
	        	Log.i("WEAPON:", "Bullet");
	        	model.setPlayerWeapon(BulletWeapon.class);
	        	model.resume();
	            return true;
	        case R.id.missile:
	        	Log.i("WEAPON:", "Missile");
	        	model.setPlayerWeapon(BulletWeapon.class);
	        	model.resume();
	            return true;
	        case R.id.inventory:
	        	model.pause();
	        	return true;
	        case R.id.health_pack:
	        	updateStore(Constants.HEALTH_SKU);
	        	model.player.stats.restoreHealth();
	        	model.resume();
	        	return true;
	        case R.id.store:
	        	finish();
	        	startActivity(new Intent(GameActivity.this, StoreActivity.class));
	        	return true;
	        case R.id.options:
	        	finish();
	        	startActivity(new Intent(GameActivity.this, OptionsActivity.class));
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
					while (model.curGameState != GameState.GAME_OVER)
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
	
	// PERSISTENCE =======================================================
	
	private HashMap<String, Integer> getStoreData() {
		String wrapperStr = getPresistentPref();
		if (wrapperStr != null) {
			StoreItemDTO wrapper = new Gson().fromJson(wrapperStr, StoreItemDTO.class);
			HashMap<String, Integer> hashMap = wrapper.data;
			return hashMap;
		}
		return null;
	}

	private String getPresistentPref() {
		return getSharedPreferences(Preferences.applicationIdentifier,
				MODE_PRIVATE).getString(
				Preferences.persistantStorageIdentifier, null);
	}
	
	/**
	 * Update the persistent storage by 1.
	 * User bought 1 unit of stuff.
	 * @param string 
	 */
	public void updateStore(String data) {
		
		//update
		HashMap<String, Integer> temp = getStoreData();
		int newCount = temp.get(data) - 1;
		temp.put(data, newCount);
		
		// push it
		StoreItemDTO wrapper = new StoreItemDTO();
		wrapper.data = temp;
		String serializedMap = new Gson().toJson(wrapper);

		getSharedPreferences(Preferences.applicationIdentifier, MODE_PRIVATE)
				.edit()
				.putString(Preferences.persistantStorageIdentifier,
						serializedMap).commit();
	}
	
}
