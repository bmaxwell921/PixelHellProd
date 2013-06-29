package com.theoc.pixhell.model;

import com.theoc.pixhell.utilities.Constants;

public class HealthConsumable extends PersistentConsumable {

	public HealthConsumable() {
		super(Constants.HEALTH_SKU, 0, true);
	}

	public HealthConsumable(String skuString, int count, boolean isIncrement) {
		super(skuString, count, isIncrement);
	}

	
}
