package com.theoc.pixhell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.amazon.inapp.purchasing.PurchasingManager;
import com.google.gson.Gson;
import com.theoc.pixhell.db.PixhellDBHelper;
import com.theoc.pixhell.db.StoreItemDTO;
import com.theoc.pixhell.model.HealthConsumable;
import com.theoc.pixhell.store.PowerupPurchaseObserver;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Preferences;

public class StoreActivity extends Activity implements OnItemClickListener {

	String currentUser;
	ListView lv;
	LayoutInflater inflater;
	public Map<String, String> requestIdPowerupMap;
	Gson gson;

	HealthConsumable healthConsumable;

	PixhellDBHelper dbHelper;

	private List<String> cheatCodes = new ArrayList<String>();
	String[] listViewData;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		populateCheatCodes();
		lv = (ListView) findViewById(R.id.listView1);

		gson = new Gson();

		HashMap<String, Integer> storeData = getStoreData();

		listViewData = formatData(storeData);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listViewData);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(this);

		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dbHelper = new PixhellDBHelper(getBaseContext());
		healthConsumable = new HealthConsumable();

	}

	private String[] formatData(HashMap<String, Integer> storeData) {
		String[] temp = new String[storeData.size()];
		Iterator it = storeData.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			String humanReadableName = Constants.SKU_TO_NAME_MAP.get(pairs.getKey());
			temp[i++] = humanReadableName + "-" + pairs.getValue();
			it.remove(); // avoids a ConcurrentModificationException
		}
		return temp;
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
			showCheatDialog();
			break;
		case 2:
			buyLife();
			break;
		
		}

	}

	private void populateCheatCodes() {
		cheatCodes = new ArrayList<String>();
		cheatCodes.add("CHEAT01");
		cheatCodes.add("CHEAT02");
		cheatCodes.add("CHEAT03");
		cheatCodes.add("CHEAT04");
		cheatCodes.add("CHEAT05");
		cheatCodes.add("CHEAT06");		
	}
	
	private boolean isCheatMatch(String cheat){
		if(cheatCodes.indexOf(cheat) > -1){
			cheatCodes.remove(cheat);
			return true;
		}
		return false;
	}
	
	private void showCheatDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		int checkedValue = getSharedPreferences(
				Preferences.applicationIdentifier, MODE_PRIVATE).getInt(
				Preferences.difficultyIdentifier, 0);
		final View v = inflater.inflate(R.layout.dialog_cheat, null);
		builder.setTitle("Cheat Code");
		builder.setView(v);
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				EditText cheat = (EditText) v.findViewById(R.id.cheatText);
				final String cheatValue = cheat.getText().toString();
				if(isCheatMatch(cheatValue)){
					update(Constants.HEALTH_SKU);
					update(Constants.HEALTH_SKU);
					updateUI();
				}
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		builder.create().show();
		
	}
	private void buyLife() {

		PurchasingManager.initiatePurchaseRequest(Constants.LIFE_SKU);
	}

	private void buyHealth() {
		PurchasingManager.initiatePurchaseRequest(Constants.HEALTH_SKU);

	}

	public void setCurrentUser(String userId) {
		currentUser = userId;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	/**
	 * Update the persistent storage by 1. User bought 1 unit of stuff.
	 * 
	 * @param string
	 */
	public void update(String data) {

		// update
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
	
	public void updateUI(){

		// update UI


		Log.w("UpdateUI", "resuming");

		HashMap<String, Integer> storeData = getStoreData();

		listViewData = formatData(storeData);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listViewData);
		
		Log.i("something",listViewData+"");

		lv.setAdapter(adapter);

		}


}
