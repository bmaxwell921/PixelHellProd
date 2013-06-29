package com.theoc.pixhell;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story);

		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setMovementMethod(new ScrollingMovementMethod());

		try {
			Bitmap background = (Bitmap) BitmapFactory.decodeStream(getAssets()
					.open("img/Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

}
