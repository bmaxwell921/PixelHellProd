package com.theoc.pixhell.utilities; 

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String HEALTH_SKU = "com.theoc.pixhell.health";
	public static final String WEAPON1_SKU = "com.theoc.pixhell.weapon1";
	public static final String WEAPON2_SKU = "com.theoc.pixhell.weapon2";
	public static final String WEAPON3_SKU = "com.theoc.pixhell.weapon3";
	public static final String LIFE_SKU = "com.theoc.pixhell.life";
	public static final int SHIP_WIDTH=100;
	public static final int SHIP_HEIGHT=100;
	public static final int BULLET_HEIGHT=20;
	public static final int BULLET_WIDTH=20;
	
	public static final int ENEMY_CRASH_DAMAGE = 50;
	
	public static final Map<String, Integer> SKU_TO_TYPE_MAP ;
	
	static {
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put(HEALTH_SKU, 0);
	    map.put(LIFE_SKU, 1);
	    // ...
	    SKU_TO_TYPE_MAP = Collections.unmodifiableMap(map);
	}
}
