package com.theoc.pixhell.db;

public class PurchasedConsumable {

	String iapSku;
	int count;
	
	
	
	public PurchasedConsumable() {
		iapSku = null;
		count = 0;
	}
	public PurchasedConsumable(String iapSku, int count) {
		super();
		this.iapSku = iapSku;
		this.count = count;
	}
	public String getIapSku() {
		return iapSku;
	}
	public void setIapSku(String iapSku) {
		this.iapSku = iapSku;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
