package com.theoc.pixhell.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.theoc.pixhell.infoboxes.WaveInfo;
import com.theoc.pixhell.logic.AIFactory;
import com.theoc.pixhell.logic.AssetMap;
import com.theoc.pixhell.manager.InputManager;
import com.theoc.pixhell.manager.SoundManager;
import com.theoc.pixhell.utilities.Constants;
import com.theoc.pixhell.utilities.Difficulty;
import com.theoc.pixhell.utilities.GameState;
import com.theoc.pixhell.utilities.WeaponType;
import com.theoc.pixhell.weaponLaunchers.BulletLauncher;
import com.theoc.pixhell.weaponLaunchers.CircleBlaster;
import com.theoc.pixhell.weaponLaunchers.MissileLauncher;
import com.theoc.pixhell.weaponLaunchers.TriBlasterLauncher;

public class LevelObject extends Observable {
	public int screenWidth, screenHeight;
	public GameState curGameState;
	private GameState onPauseState;

	private GameObject background;
	private List<Ship> enemies;
	public Ship player;

	private List<Weapon> playerShots;
	private List<Weapon> enemyShots;

	// private List<GameObject> consumables;
	// private List<Explosion>

	private AIFactory factory;
	private SoundManager sm;
	int randomNumber;
	int randomNumberForSound;
	int coinNumber;
	int score;

	// TODO make this better
	private final int TEAR_DOWN_TIMER = 3000;
	private int timeLeftForTearDown = 0;
	private List<GameObject> explosions;
	private List<GameObject> coins;

	public LevelObject(int screenWidth, int screenHeight,
			GameStartProperties properties, InputManager im, SoundManager sm) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.sm = sm;
		background = new Background(AssetMap.getImage(AssetMap.backgroundKey),
				screenWidth, screenHeight);
		enemies = new LinkedList<Ship>();
		
		// highly used object - added synchronous access
		List<Weapon> playerShots_ns = new LinkedList<Weapon>();
		playerShots = Collections.synchronizedList(playerShots_ns);
		
		// highly used object - added synchronous access
		List<Weapon> enemyShots_ns = new LinkedList<Weapon>();
		enemyShots = Collections.synchronizedList(enemyShots_ns);
		explosions = new LinkedList<GameObject>();
		coins = new LinkedList<GameObject>();
		// TODO don't have this hard coded here
		player = new Player(AssetMap.getImage(AssetMap.playerOne), im,
				screenWidth, screenHeight, 100);
		

		Random randomGenerator = new Random();
		randomNumber = randomGenerator.nextInt(1) % 2;
		randomNumberForSound = randomGenerator.nextInt(1) % 3;
		coinNumber = properties.wallet;
		score = 0;

		transitionToState(GameState.BETWEEN_WAVE);
		onPauseState = GameState.IN_WAVE;

		// TODO get Difficulty this from elsewhere
		factory = new AIFactory(Difficulty.EASY, new WaveInfo(10, 2000, 100),
				screenWidth, screenHeight);
		setUpNextWave();
	}

	public void update(long timeElapsed) {
		if (curGameState == GameState.PAUSE) {
			// Do nothing
		} else if (curGameState == GameState.IN_WAVE) {
			inWaveUpdate(timeElapsed);
		} else if (curGameState == GameState.BETWEEN_WAVE) {
			setUpNextWave();
		} else if (curGameState == GameState.TEAR_DOWN) {
			tearDownUpdate(timeElapsed);
		}

		this.setChanged();
		this.notifyObservers();
	}

	public void pause() {
		if (!isPaused()) {
			onPauseState = curGameState;
			transitionToState(GameState.PAUSE);
		}
	}

	public boolean isPaused() {
		return curGameState == GameState.PAUSE;
	}

	public void resume() {
		transitionToState(onPauseState);
		onPauseState = GameState.IN_WAVE;
	}

	public void setPlayerWeapon(WeaponType weapon) {
		if (weapon == WeaponType.BULLET) {
			player.addLauncher(new BulletLauncher(player.stats
					.getBulletDamage(), player.stats.getBulletCooldown(), false));
		} else if (weapon == WeaponType.MISSILE) {
			player.addLauncher(new MissileLauncher(player.stats
					.getMissileDamage(), player.stats.getMissileCooldown(), false));
		} else if (weapon == WeaponType.TRI_BLASTER) {
			player.addLauncher(new TriBlasterLauncher(player.stats.getBulletDamage(), 
					player.stats.getBulletCooldown(), false));
		} else if (weapon == WeaponType.CIRCLE_BLASTER) {
			player.addLauncher(new CircleBlaster(player.stats.getBulletDamage(), 
					player.stats.getBulletCooldown(), false));
		}
	}

	public int getCoinNumber() {
		return coinNumber;
	}

    
	
	public int getScore() {
		return score;
	}

	public void setCoins(List<GameObject> coins) {
		this.coins = coins;
	}


	public List<GameObject> getCoins() {
		return coins;
	}

	private void inWaveUpdate(long timeElapsed) {
		doUpdates(timeElapsed);
		doCollisionChecks();
		spawnNewEnemies(timeElapsed);
		checkOffScreenGameObjects();
		checkEndGame();
	}

	private void doUpdates(float timeElapsed) {
		// Move everyone, then check collisions
		player.update(timeElapsed);
		List<Weapon> weapons = player.Fire(timeElapsed);
		if (weapons != null && !weapons.isEmpty()) {
			sm.playSoundEffect(AssetMap.SHOT_BULLET);
			playerShots.addAll(weapons);
		}
		for (Ship ship : enemies) {
			ship.update(timeElapsed);

			List<Weapon> enemyWeapon = ship.Fire(timeElapsed);
			Random randomGenerator = new Random();
			randomNumberForSound = randomGenerator.nextInt(4) % 2;
			if (enemyWeapon != null && !enemyWeapon.isEmpty()) {
				if (randomNumberForSound == 0) {
					sm.playSoundEffect(AssetMap.SHOT_MISSILE);
				} else if (randomNumberForSound == 1) {
					sm.playSoundEffect(AssetMap.SHOT_LASER);
				} else if (randomNumberForSound == 2) {
					sm.playSoundEffect(AssetMap.SHOT_BULLET);
				} else {
					sm.playSoundEffect(AssetMap.SHOT_BOMB);
				}
			}
			enemyShots.addAll(enemyWeapon);
		}

		for (GameObject shot : playerShots) {
			shot.update(timeElapsed);
		}

		for (GameObject shot : enemyShots) {
			shot.update(timeElapsed);

		}

		for (GameObject exp : explosions) {
			exp.update(timeElapsed);
		}
		
		for (GameObject coin : coins) {
			coin.update(timeElapsed);
		}

		checkDeaths();

		background.update(timeElapsed);
	}

	private void doCollisionChecks() {
		playerEnemyCollisions();
		playerEnemyShotCollisions();
		enemyPlayerShotCollisions();
		playerCoinCollisions();
	}

	private void spawnNewEnemies(float dt) {
		if (factory.timeForSpawn(dt)) {
			enemies.add(factory.spawnEnemy());
		}
	}

	private void playerEnemyCollisions() {
		for (Iterator<Ship> iter = enemies.iterator(); iter.hasNext();) {
			Ship enemy = iter.next();
			if (player.CollidesWith(enemy)) {
				player.applyDamage(Constants.ENEMY_CRASH_DAMAGE);
				explosions.add(new Explosion(enemy.position));
				iter.remove();
			}
		}
	}

	private void playerEnemyShotCollisions() {
		handleShipShotCollision(player, enemyShots);
	}

	private void enemyPlayerShotCollisions() {
		for (Ship enemy : enemies) {
			handleShipShotCollision(enemy, playerShots);
		}
	}

	private void playerCoinCollisions() {
		coinCollision(player, coins);
	}

	private void checkDeaths() {
		for (Iterator<Ship> iter = enemies.iterator(); iter.hasNext();) {
			Ship ship = iter.next();
			Random randomGenerator = new Random();
			randomNumber = randomGenerator.nextInt(4) % 2;
			randomNumberForSound = randomGenerator.nextInt(4) % 3;
			if (!ship.isAlive) {
				if (randomNumber == 1) {

					explosions.add(new Explosion(ship.position));
					if (randomNumberForSound == 0) {
						sm.playSoundEffect(AssetMap.ENEMY_KILL_WHOOSH);
					}
					if (randomNumberForSound == 1) {
						sm.playSoundEffect(AssetMap.ENEMY_KILL_RATTLE);
					}
					if (randomNumberForSound == 2) {
						sm.playSoundEffect(AssetMap.ENEMY_KILL_DEREZ);
					}
					score = score + 10;
				} else {
					coins.add(new Coins(ship.position));
				}
				iter.remove();
			}
		}

		for (Iterator<GameObject> iter = explosions.iterator(); iter.hasNext();) {
			GameObject exp = iter.next();
			if (!exp.isAlive) {
				iter.remove();
			}
		}

		for (Iterator<GameObject> iter = coins.iterator(); iter.hasNext();) {
			GameObject coin = iter.next();
			if (!coin.isAlive) {
				iter.remove();
			}
		}

	}

	private void handleShipShotCollision(Ship single, List<Weapon> others) {
		// for (Weapon other : others) {
		for (Iterator<Weapon> iter = others.iterator(); iter.hasNext();) {
			Weapon other = iter.next();
			if (single.CollidesWith(other)) {
				single.applyDamage(other.damage);
				iter.remove();
			}
		}
	}

	private void coinCollision(Ship single, List<? extends GameObject> others) {
		// for (Weapon other : others) {
		for (Iterator<? extends GameObject> iter = others.iterator(); iter
				.hasNext();) {
			Coins other = (Coins) iter.next();
			if (single.CollidesWith(other)) {
				other.isAlive = false;
				coinNumber++;
				iter.remove();
			}
		}
	}

	private void checkOffScreenGameObjects() {
		checkListOffScreen(enemies);
		checkListOffScreen(playerShots);
		checkListOffScreen(enemyShots);
	}

	private void checkListOffScreen(List<? extends GameObject> objs) {
		for (Iterator<? extends GameObject> iter = objs.iterator(); iter
				.hasNext();) {
			if (isOffScreen(iter.next())) {
				iter.remove();
			}
		}
	}

	private void checkEndGame() {
		if (player.stats.getHealth() <= 0) {
			transitionToState(GameState.TEAR_DOWN);
			sm.playSoundEffect(AssetMap.PLAYER_KILL_GAMEOVER);
		}
	}

	private boolean isOffScreen(GameObject obj) {
		return !background.RectBoxforCollision().contains(
				obj.RectBoxforCollision());
	}

	private void setUpNextWave() {
		factory.moveToNextWave();
		transitionToState(GameState.IN_WAVE);
	}

	private void tearDownUpdate(float dt) {
		timeLeftForTearDown -= dt;
		if (timeLeftForTearDown <= 0) {
			transitionToState(GameState.GAME_OVER);
		}
	}

	private void transitionToState(GameState state) {
		curGameState = state;
		if (state == GameState.TEAR_DOWN) {
			timeLeftForTearDown = TEAR_DOWN_TIMER;
		}
	}

	public List<GameObject> getOnscreenObjects() {
		List<GameObject> onScreen = new ArrayList<GameObject>();
		onScreen.add(background);
		onScreen.addAll(enemies);
		onScreen.add(player);
		onScreen.addAll(enemyShots);
		onScreen.addAll(playerShots);
		onScreen.addAll(explosions);
		onScreen.addAll(coins);
		return onScreen;
	}
}
