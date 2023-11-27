package game;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerShip extends Player {
    KeyboardManager keyboardManager = GameScreen.keyboardManager;
	private Image sprite = new ImageIcon("graphics/officer.png").getImage();
	private double size = 64;
	private double width;
	private double height;
	private double x;
	private double y;
	private double speed = 4f;
	public ArrayList<Bullet> bullets = new ArrayList<>();
	private double rateOfFire = 3f;
	private double shotTime = 0;
	private double damage = 50;
	public double maxHP = 100;
	public double currentHP = maxHP;

    public PlayerShip() {
		calculateDimensions();
	}

	public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

	public double getHeight() {
		return this.height;
	}

	public double getWidth() {
		return this.width;
	}

	public double getCurrentHP() {
		return this.currentHP;
	}

	public void setCurrentHP(double currentHP) {
		this.currentHP = currentHP;
	}

	public double getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(double maxHP) {
		this.maxHP = maxHP;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSize() {
		return size;
	}

	public Image getSprite() {
		return this.sprite;
	}

	public double getSpeed() {
		return speed;
	}

	public double getRateOfFire() {
		return rateOfFire;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setPosition(double x, double y) {
		this.x = x - size / 2;
		this.y = y - size / 2;
	};

	public void shoot() {
		if (shotTime == 0) {
			bullets.add(new Bullet(x + size / 2 - 5, y, 10, damage, Color.ORANGE, 8f, -Math.cos(Math.toRadians(90)),
					-Math.sin(Math.toRadians(90))));
			GameScreen.soundManager.play(1);
		}
		shotTime += rateOfFire;

		if (shotTime > 50) {
			shotTime = 0;
		}
	}

    @Override
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
}
