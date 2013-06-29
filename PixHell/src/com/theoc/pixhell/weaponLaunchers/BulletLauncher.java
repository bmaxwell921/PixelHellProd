package com.theoc.pixhell.weaponLaunchers;

import java.util.ArrayList;
import java.util.List;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.model.BulletWeapon;
import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class BulletLauncher extends Launcher {

	public BulletLauncher(int damage, int coolDown) {
		super(damage, coolDown);
	}

	@Override
	public void setUniqueKey() {
		uniqueKey = "BULLET_LAUNCHER";		
	}

	@Override
	protected List<Weapon> getProjectiles(Vector2 shipCenter, Vector2 shipSize,
			int direction) {
		List<Weapon> proj = new ArrayList<Weapon>();
		Vector2 pos = Vector2.add(shipCenter, new Vector2(-Constants.BULLET_WIDTH / 2f, 
				-shipSize.y / 2));
		proj.add(new BulletWeapon(AssetMap.getImage(AssetMap.shot), pos, 
				Vector2.multiply(DEFAULT_SPEED, direction), damage));
		return proj;	
	}

}
