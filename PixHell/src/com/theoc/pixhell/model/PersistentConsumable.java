package com.theoc.pixhell.model;


public class PersistentConsumable implements Consumable {

	String skuString;
	int count;
	boolean isIncrement;
	
	
	
	public PersistentConsumable() {
		super();
		this.skuString = null;
		this.count = 0;
		this.isIncrement = true;
	}

	public PersistentConsumable(String skuString, int count, boolean isIncrement) {
		super();
		this.skuString = skuString;
		this.count = count;
		this.isIncrement = isIncrement;
	}

	public boolean isIncrement() {
		return isIncrement;
	}

	public void setIncrement(boolean isIncrement) {
		this.isIncrement = isIncrement;
	}

	public String getSkuString() {
		return skuString;
	}

	public void setSkuString(String skuString) {
		this.skuString = skuString;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
