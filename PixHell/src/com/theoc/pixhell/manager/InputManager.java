package com.theoc.pixhell.manager;

import java.util.Observable;
import java.util.Observer;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class InputManager 
{
	public SensorManager       sensorManager  = null;
	public SensorEventListener mEventListener = null;
	
	private final float[] mValuesMagnet      = {0f, 0f, 0f};
    private final float[] mValuesAccel       = {0f, 0f, 0f};
    private final float[] mValuesOrientation = {0f, 0f, 0f};
    private final float[] mRotationMatrix    = {0f, 0f, 0f,
    		                                    0f, 0f, 0f,
    		                                    0f, 0f, 0f};
    
    private boolean isTouched = false;
	
	public InputManager() {
		this.mEventListener =
			new SensorEventListener()
			{
	            public void onAccuracyChanged(Sensor sensor, int accuracy) { }
	
				@Override
				public void onSensorChanged(SensorEvent event) 
				{
	                // TODO Auto-generated method stub
	                switch (event.sensor.getType()) {
	                case Sensor.TYPE_ACCELEROMETER:
	                    System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
	                    break;
	
	                case Sensor.TYPE_MAGNETIC_FIELD:
	                    System.arraycopy(event.values, 0, mValuesMagnet, 0, 3);
	                    break;
	                }
	            }
			};
	}
	
	public Point getTiltVector() {
		//TODO
		return null;
	}
	
	public boolean screenIsTouched() {
		return this.isTouched;
	}

	public void setTouched(boolean touched) {
		this.isTouched = touched;
	}
	
}
