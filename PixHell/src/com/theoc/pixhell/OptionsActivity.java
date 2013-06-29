package com.theoc.pixhell;

import java.text.DecimalFormat;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.theoc.pixhell.global.WeaponsCache;
import com.theoc.pixhell.model.PersistentConsumable;
import com.theoc.pixhell.utilities.Difficulty;
import com.theoc.pixhell.utilities.Preferences;

public class OptionsActivity extends Activity implements OnItemClickListener {

	private static final int MAX_TILT_VALUE = 100;
	ListView lv;
	String menuOptions[] = { "Tilt Sensitivity", "Wallet", "Difficulty",
			"Weapon Cache" };
	String DIFFICULTIES[] = { "Easy", "Medium", "Difficult", "Hell" };
	Difficulty DEFAULT_DIFFICULTIES = Difficulty.EASY;
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);

		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuOptions));
		lv.setOnItemClickListener(this);

		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
		case 0:
			createTiltDialog();
			break;
		case 1:
			createWalletDialog();
			break;
		case 2:
			createDifficultyDialog();
			break;
		case 3:
			createWeaponsCacheDialog();
		}

	}

	private void createWeaponsCacheDialog() {
		Gson gson = new Gson();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// get weapon data
		// display in the list
		String weaponsCache = getSharedPreferences(
				Preferences.applicationIdentifier, MODE_PRIVATE).getString(
				Preferences.weaponsCacheIdentifier, null);
		if (weaponsCache != null) {
			WeaponsCache map = gson.fromJson(weaponsCache, WeaponsCache.class);
			HashMap<PersistentConsumable, Integer> weaponsList = map.weaponsCache;
		}

	}

	private void createDifficultyDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		int checkedValue = getSharedPreferences(
				Preferences.applicationIdentifier, MODE_PRIVATE).getInt(
				Preferences.difficultyIdentifier, 0);

		builder.setSingleChoiceItems(DIFFICULTIES, checkedValue,
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						getSharedPreferences(Preferences.applicationIdentifier,
								MODE_PRIVATE)
								.edit()
								.putInt(Preferences.difficultyIdentifier, which)
								.commit();
					}
				});

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
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

	private void createWalletDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Wallet");
		int val = getSharedPreferences(Preferences.applicationIdentifier,
				MODE_PRIVATE).getInt(Preferences.walletIdentifier, -1);

		String message = val == -1 ? "You have no money. You are broke."
				: "You have $" + val + " in your account.";

		builder.setMessage(message);
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		builder.create().show();
	}

	private void createTiltDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		View v = inflater.inflate(R.layout.dialog_tilt_senstivity, null);

		final SeekBar sb = (SeekBar) v.findViewById(R.id.seekBar1);
		final TextView tv = (TextView) v.findViewById(R.id.textView1);

		final DecimalFormat df = new DecimalFormat("0.00");

		sb.setMax(MAX_TILT_VALUE);

		float progressValue = getSharedPreferences(
				Preferences.applicationIdentifier, MODE_PRIVATE).getFloat(
				Preferences.tiltSensitivityIdentifier, 0.0f);

		tv.setText("" + progressValue);
		sb.setProgress(Math.round(progressValue));

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				float currentTilt = getConvertedValue(progress);

				tv.setText("" + df.format(currentTilt));
			}
		});

		builder.setTitle("Tilt Sensitivity");
		builder.setView(v);

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				getSharedPreferences(Preferences.applicationIdentifier,
						MODE_PRIVATE)
						.edit()
						.putFloat(Preferences.tiltSensitivityIdentifier,
								Float.valueOf(tv.getText().toString()))
						.commit();

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

	public float getConvertedValue(int value) {

		return value * 0.1f;

	}
}
