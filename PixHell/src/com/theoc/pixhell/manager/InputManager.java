package com.theoc.pixhell.manager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.theoc.pixhell.utilities.DirectionalVector;

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
    private DirectionalVector<Integer> tiltVector = null;
	
	public InputManager(SensorManager sensorManager) {
		this.tiltVector = new DirectionalVector<Integer>(0, 0);
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
	
	public DirectionalVector<Integer> getTiltVector() {
		if (this.mValuesAccel[0] > 2.0f) {
			this.tiltVector.x = -1;
		} else if (this.mValuesAccel[0] < -2.0f) {
			this.tiltVector.x = 1;
		}
		else {
			this.tiltVector.x = 0;
		}
		
		if (this.mValuesAccel[1] > 2.0f) {
			this.tiltVector.y = 1;
		} else if (this.mValuesAccel[1] < -2.0f) {
			this.tiltVector.y = -1;
		}
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
	
}