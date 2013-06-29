package com.theoc.pixhell;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazon.inapp.purchasing.PurchasingManager;
import com.google.gson.Gson;
import com.theoc.pixhell.db.PixhellDBHelper;
import com.theoc.pixhell.db.StoreItemDTO;
import com.theoc.pixhell.model.HealthConsumable;
import com.theoc.pixhell.store.PowerupPurchaseObserver;
import com.theoc.pixhell.utilities.Preferences;

public class StoreActivity extends Activity implements OnItemClickListener {

	String currentUser;
	ListView lv;
	LayoutInflater inflater;
	ArrayAdapter<String> Adapter;
	public Map<String, String> requestIdPowerupMap;
	Gson gson;

	HealthConsumable healthConsumable;

	PixhellDBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		lv = (ListView) findViewById(R.id.listView1);

		gson = new Gson();

		HashMap<String, Integer> storeData = getStoreData();

		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, storeData.keySet()
						.toArray(new String[storeData.size()])));

		lv.setOnItemClickListener(this);

		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dbHelper = new PixhellDBHelper(getBaseContext());
		healthConsumable = new HealthConsumable();

	}

	private HashMap<String, Integer> getStoreData() {
		String wrapperStr = getPresistentPref();
		if (wrapperStr != null) {
			StoreItemDTO wrapper = gson
					.fromJson(wrapperStr, StoreItemDTO.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			buyHealth();
			break;
		case 1:
			buyLife();
			break;
		}

	}

	private void buyLife() {
		// TODO Auto-generated method stub

	}

	private void buyHealth() {
		// TODO Auto-generated method stub

	}

	public void setCurrentUser(String userId) {
		currentUser = userId;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * Update the persistent storage by 1.
	 * User bought 1 unit of stuff.
	 * @param string 
	 */
	public void update(String data) {
		
		//update
		HashMap<String, Integer> temp = getStoreData();
		int newCount = temp.get(data) + 1;
		temp.put(data, newCount);
		
		// push it
		StoreItemDTO wrapper = new StoreItemDTO();
		wrapper.data = temp;
		String serializedMap = gson.toJson(wrapper);

		getSharedPreferences(Preferences.applicationIdentifier, MODE_PRIVATE)
				.edit()
				.putString(Preferences.persistantStorageIdentifier,
						serializedMap).commit();
	}

	public void onStart() {

		super.onStart();

		PowerupPurchaseObserver buttonClickerObserver = new PowerupPurchaseObserver(
				this);

		PurchasingManager.registerObserver(buttonClickerObserver);

	}

	@Override
	protected void onResume() {

		super.onResume();

		PurchasingManager.initiateGetUserIdRequest();

	};

}
