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
	public static final int BULLET_HEIGHT=100;
	public static final int BULLET_WIDTH=100;
	public static final int MISSILE_HEIGHT=100;
	public static final int MISSILE_WIDTH=100;
	
	public static final int EXPLOSION_WIDTH = 100;
	public static final int EXPLOSION_HEIGHT = 100;
	
	public static final int ENEMY_CRASH_DAMAGE = 25;
	
	public static final Map<String, Integer> SKU_TO_TYPE_MAP ;
	public static final Map<String, String> SKU_TO_NAME_MAP ;
	
	static {
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    Map<String, String> nameMap = new HashMap<String, String>();
	    map.put(HEALTH_SKU, 0);
	    map.put(LIFE_SKU, 1);
	    
	    nameMap.put(HEALTH_SKU, "Health");
	    nameMap.put(LIFE_SKU, "Life");
	    // ...
	    SKU_TO_TYPE_MAP = Collections.unmodifiableMap(map);
	    SKU_TO_NAME_MAP = Collections.unmodifiableMap(nameMap);
	}
}
