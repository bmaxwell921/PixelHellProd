package com.theoc.pixhell;

import java.util.Currency;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoreActivity extends Activity {

	String currentUser;
	ListView lv;
	String menuOptions[] = { "Tilt Sensitivity", "Wallet", "Difficulty",
			"Weapon Cache" };
	
	public Map<String, String> requestIdPowerupMap ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuOptions));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}

	public void setCurrentUser(String userId) {
		currentUser = userId;		
	}

	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * update the DB about the count of the Item purchased and also the the StoreActivity View.
	 */
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
