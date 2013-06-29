package com.theoc.pixhell.manager;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.theoc.pixhell.utilities.Vector2;

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
    private float   tiltSensitivity = 5f;
    private boolean isTouched = false;
    //private DirectionalVector<Integer> tiltVector = null;
    private Vector2 tiltVector = null;
	
	public InputManager(SensorManager sensorManager) {
		this.tiltVector = new Vector2(0f, 0f);
		this.sensorManager = sensorManager;
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
	
	public Vector2 getTiltVector() {
		float x = mValuesAccel[0] * (1f + (.1f * this.tiltSensitivity));
		float y = mValuesAccel[1] * (1f + (.1f * this.tiltSensitivity));
		
		// X-Left
		if (x > 4.0f) {
			this.tiltVector.x = -5f;
		} else if (x > 1.5f) {
			this.tiltVector.x = -2f;
		} 
		// X-Right
		else if (x < -4.0f) {
			this.tiltVector.x = 5f;
		} else if (x < -1.5f) {
			this.tiltVector.x = 2f;
		} 
		// X- Stationary
		else {
			this.tiltVector.x = 0f;
		}
		
		
		// Y-Down
		if (y > 2.5f) {
			this.tiltVector.y = 3f;
		} else if (y > 1.25f) {
			this.tiltVector.y = 1f;
		}
		// Y-Up
		else if (y < -2.5f) {
			this.tiltVector.y = -3f;
		} else if (y < -1.25f) {
			this.tiltVector.y = -1f;
		}
		// Y-Stationary
		else {
			this.tiltVector.y = 0;
		}
		
		return this.tiltVector;
	}
	
	public boolean screenIsTouched() {
		return this.isTouched;
	}

	public void setTouched(boolean touched) {
		this.isTouched = touched;
	}
	
	public void setTiltSensitivity(float sensitivity) {
		this.tiltSensitivity = sensitivity;
	}
	
}