package com.theoc.pixhell.weaponLaunchers;

import java.util.ArrayList;
import java.util.List;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.model.Missile;
import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class MissileLauncher extends Launcher {
	
	public MissileLauncher(int damage, int coolDown) {
		super(damage, coolDown);
		
	}

	@Override
	public void setUniqueKey() {
		this.uniqueKey = "MISSILE_LAUNCHER";
		
	}

	@Override
	protected List<Weapon> getProjectiles(Vector2 shipCenter, Vector2 shipSize, int direction) {
		List<Weapon> proj = new ArrayList<Weapon>();
		Vector2 pos = Vector2.add(shipCenter, new Vector2(-Constants.MISSILE_WIDTH / 2f, 
				-shipSize.y / 2));
		String key = direction < 0 ? AssetMap.missile : AssetMap.missileDown;
		proj.add(new Missile(AssetMap.getImage(key), pos, 
				Vector2.multiply(DEFAULT_SPEED, direction), damage));		
		return proj;
	}

}
