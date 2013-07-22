package com.theoc.pixhell.weaponLaunchers;

import java.util.ArrayList;
import java.util.List;

import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.model.BulletWeapon;
import com.theoc.pixhell.model.Weapon;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Vector2;

public class CircleBlaster extends Launcher {

	public CircleBlaster(int damage, int coolDown, boolean isEnemy) {
		super(damage, coolDown, isEnemy);
	}

	@Override
	public void setUniqueKey() {
		uniqueKey = "CIRCLE_BLASTER";
		
	}

	@Override
	protected List<Weapon> getProjectiles(Vector2 shipCenter, Vector2 shipSize,
			int direction) {
		List<Weapon> proj = new ArrayList<Weapon>();
		int leftXVel = (int)-DEFAULT_SPEED.y;
		int rightXVel = (int) DEFAULT_SPEED.y;
		int upYVel = leftXVel;
		int downYVel = rightXVel;
		
		Vector2 left = Vector2.add(shipCenter, new Vector2(-shipSize.x, 
				-Constants.BULLET_HEIGHT / 2));
		Vector2 leftVel = new Vector2(leftXVel, 0);
		proj.add(makeWeapon(left, leftVel));
		
		Vector2 right = Vector2.add(shipCenter, new Vector2(+shipSize.x, 
				-Constants.BULLET_HEIGHT / 2));
		Vector2 rightVel = new Vector2(rightXVel, 0);
		proj.add(makeWeapon(right, rightVel));
		
		Vector2 top = Vector2.add(shipCenter, 
				new Vector2(-Constants.BULLET_WIDTH / 2, -shipSize.y));
		Vector2 topVel = new Vector2(0, upYVel);
		proj.add(makeWeapon(top, topVel));
		
		Vector2 bot = Vector2.add(shipCenter, 
				new Vector2(-Constants.BULLET_WIDTH / 2, shipSize.y));
		Vector2 botVel = new Vector2(0, downYVel);
		proj.add(makeWeapon(bot, botVel));
		
		return proj;
	}
	
	private Weapon makeWeapon(Vector2 pos, Vector2 vel) {
		return new BulletWeapon(super.getBulletImage(), pos, vel, damage);
	}
	
	private float getYOfCircle(float x, Vector2 center, float radius) {
		float xCoord = x - center.x;
		return center.y + (float) Math.sqrt(radius - xCoord * xCoord);
	}
}
