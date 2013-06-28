package com.theoc.pixhell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
}
