package com.theoc.pixhell;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.gson.Gson;
import com.theoc.pixhell.db.StoreItemDTO;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Preferences;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MenuView view = (MenuView) findViewById(R.id.menuView1);
		view.setContent(getAssets());

		// required initializations for the StoreActivity
		initStoreData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// button clicks
	public void startGameActivity(View v) {
		startActivity(new Intent(MainActivity.this, GameActivity.class));
	}

	public void startStoreActivity(View v) {
		startActivity(new Intent(MainActivity.this, StoreActivity.class));
	}

	public void startOptionsActivity(View v) {
		startActivity(new Intent(MainActivity.this, OptionsActivity.class));
	}

	public void startStoryActivity(View v) {
		startActivity(new Intent(MainActivity.this, StoryActivity.class));
	}

	public void initStoreData() {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		temp.put(Constants.HEALTH_SKU, 0);
		temp.put(Constants.LIFE_SKU, 0);
		temp.put(Constants.WEAPON1_SKU, 0);
		temp.put(Constants.CHEAT_SKU, 0);

		Gson gson = new Gson();
		StoreItemDTO wrapper = new StoreItemDTO();
		wrapper.data = temp;
		String serializedMap = gson.toJson(wrapper);

		getSharedPreferences(Preferences.applicationIdentifier, MODE_PRIVATE)
				.edit()
				.putString(Preferences.persistantStorageIdentifier,
						serializedMap).commit();
	}
}
