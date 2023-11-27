package game;

import java.awt.Color;
import java.awt.geom.Area;
import javax.swing.ImageIcon;
import enemies.Enemy;
import java.util.ArrayList;
import java.awt.Image;

public abstract class Player extends Entity {
	KeyboardManager keyboardManager = GameScreen.keyboardManager;
	private Image sprite = new ImageIcon("graphics/spaceship.png").getImage();
	private double size = 64;
	private double width;
	private double height;
	private double x;
	private double y;
	private double speed = 4f;
	public static ArrayList<Bullet> bullets = new ArrayList<>();
	private double rateOfFire = 3f;
	private double shotTime = 0;
	private double damage = 50;
	public double maxHP = 100;
	public double currentHP = maxHP;

	public void setPosition(double x, double y) {
		this.x = x - size / 2;
		this.y = y - size / 2;
	};

	public void update() {
		move();
		updateBullets();
		checkBounds();
		registerIncomingDamage();
	}

	public void move() {
		if (keyboardManager.isKeyUp) {
			y = y - speed;
		}

		if (keyboardManager.isKeyDown) {
			y = y + speed;
		}

		if (keyboardManager.isKeyLeft) {
			x = x - speed;
		}

		if (keyboardManager.isKeyRight) {
			x = x + speed;
		}

		if (keyboardManager.isKeySpace) {
			shoot();
		}
	}

	public void checkBounds() {
		if (y > GameScreen.gameHeight - size) {
			y = GameScreen.gameHeight - size;
		} else if (y < 0) {
			y = 0;
		}

		if (x > GameScreen.gameWidth - size) {
			x = 0;
		} else if (x < 0) {
			x = GameScreen.gameWidth - size;
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
