package com.theoc.pixhell.weaponLaunchers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Vector2;

public class WeaponsArray {
	private Set<Launcher> launchers;
	
	public WeaponsArray() {
		launchers = new HashSet<Launcher>();
	}
	
	public void addLauncher(Launcher l) {
		launchers.add(l);
	}
	
	public void removeAllAndAdd(Launcher l) {
		launchers.clear();
		addLauncher(l);
	}
	
	public List<Weapon> getFiredWeapons(Vector2 shipCenter, Vector2 shipSize, int direction, float dt) {
		List<Weapon> proj = new ArrayList<Weapon>();
		for (Launcher l : launchers) {
			if (l.canFire(dt)) {
				proj.addAll(l.launch(shipCenter, shipSize, direction));
			}
		}
		return proj;
	}
}
