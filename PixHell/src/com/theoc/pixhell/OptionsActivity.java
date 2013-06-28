package com.theoc.pixhell;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
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

public class OptionsActivity extends Activity implements OnItemClickListener {

	private static final int START_VALUE = 0;
	private static final int MAX_TILT_VALUE = 100;
	ListView lv;
	String menuOptions[] = { "Tilt Sensitivity", "Wallet", "Difficulty",
			"Weapon Cache" };
	String DIFFICULTIES[] = {"Easy","Medium","Difficult","Hell"};
	boolean DEFAULT_DIFFICULTIES[]={true,false,false,false};
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
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

	}

	private void createDifficultyDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
	
		builder.setMultiChoiceItems(DIFFICULTIES,DEFAULT_DIFFICULTIES,new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				
				
			}
		});
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				
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
		String val = "2";
		String message = "You have $" + val + " in your account.";
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

		sb.setMax(MAX_TILT_VALUE);
		sb.setProgress(START_VALUE);

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
				tv.setText("" + getConvertedValue(progress));
			}
		});

		builder.setTitle("Tilt Sensitivity");
		builder.setView(v);

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

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

	public String getConvertedValue(int value) {
		DecimalFormat df = new DecimalFormat("0.00");
		float result = value * 0.1f;
		return df.format(result);
	}
}
