package player;

import java.awt.geom.Area;
import enemies.Enemy;
import game.Bullet;
import game.Entity;
import game.GameScreen;
import game.KeyboardManager;

import java.util.ArrayList;

public abstract class Player extends Entity {
	KeyboardManager keyboardManager = GameScreen.keyboardManager;

	public abstract PlayerType getPlayerType();

	public void setPosition(double x, double y) {
		setX(getX() - (getSize() / 2));
		setY(getY() - (getSize() / 2));
	};

	public void update() {
		move();
		updateBullets();
		checkBounds();
		registerIncomingDamage();
	}

	public void move() {
		if (keyboardManager.isKeyUp) {
			setY(getY() - getSpeed());
		}

		if (keyboardManager.isKeyDown) {
			setY(getY() + getSpeed());
		}

		if (keyboardManager.isKeyLeft) {
			setX(getX() - getSpeed());
		}

		if (keyboardManager.isKeyRight) {
			setX(getX() + getSpeed());
		}

		if (keyboardManager.isKeySpace) {
			shoot();
		}
	}

	public void checkBounds() {
		if (getY() > GameScreen.gameHeight - getSize()) {
			setY(GameScreen.gameHeight - getSize());
		} else if (getY() < 0) {
			setY(0);
		}

		if (getX() > GameScreen.gameWidth - getSize()) {
			setX(0);;
		} else if (getX() < 0) {
			setX(GameScreen.gameWidth - getSize());
		}
	}

	@Override
	public void updateBullets() {
        for (int i = 0; i < getBullets().size(); i++) {
            getBullets().get(i).update();
        }
    }

	public void registerIncomingDamage() {
		ArrayList<Enemy> enemies = GameScreen.levelManager.currentLevel.getEnemies();

		for (int i = 0; i < enemies.size(); i++) {
			ArrayList<Bullet> enemyBullets = enemies.get(i).getBullets();
			for (int j = 0; j < enemyBullets.size(); j++) {
				Bullet bullet = enemyBullets.get(j);
				Area bulletHitbox = new Area(bullet.getHitbox());
				if (bulletHitbox.intersects(this.getHitbox())) {
					enemyBullets.remove(bullet);
					this.setCurrentHP(this.getCurrentHP() - bullet.getDamage());
					break;
				}
			}
		}
	}
}
