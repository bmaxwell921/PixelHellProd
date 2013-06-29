package com.theoc.pixhell;

import android.test.ActivityInstrumentationTestCase2;

public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity>
{
@SuppressWarnings("unused")
	private GameActivity activity;

	public GameActivityTest()
	{
	    super(GameActivity.class);
	}
	
	
	public void setUp()
	{
	    activity = this.getActivity();
	}
	
	public void testGame()
	{
	    assertTrue(true);
	}
}