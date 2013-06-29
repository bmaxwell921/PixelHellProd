package com.theoc.pixhell;

import java.util.Currency;
import java.util.Map;

import com.amazon.inapp.purchasing.PurchasingManager;
import com.amazon.inapp.purchasing.PurchasingObserver;
import com.theoc.pixhell.db.PixhellDBHelper;
import com.theoc.pixhell.model.HealthConsumable;
import com.theoc.pixhell.store.PowerupPurchaseObserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class StoreActivity extends Activity implements OnItemClickListener {

	String currentUser;
	ListView lv;
	LayoutInflater inflater;
	ArrayAdapter<String> Adapter;
	public Map<String, String> requestIdPowerupMap;

	HealthConsumable healthConsumable;

	PixhellDBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		lv = (ListView) findViewById(R.id.listView1);
		
		HashMap<String,Integer> storeData = getStoreData();
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuOptions));
		
		lv.setOnItemClickListener(this);

		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dbHelper = new PixhellDBHelper(getBaseContext());
		healthConsumable = new HealthConsumable();

	}

	private HashMap<String, Integer> getStoreData() {
		String 
		return null;
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
	 * update the DB about the count of the Item purchased and also the the
	 * StoreActivity View.
	 */
	public void update() {
		// TODO Auto-generated method stub

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
