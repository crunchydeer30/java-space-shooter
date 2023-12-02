package player;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Bullet;
import game.GameScreen;
import game.KeyboardManager;
import sounds.SoundEffect;

public class PlayerLight extends Player {
    KeyboardManager keyboardManager = GameScreen.keyboardManager;
	private Image sprite = new ImageIcon("assets/graphics/spaceship.png").getImage();
	private double size = 64;
	private double width;
	private double height;
	private double x;
	private double y;
	private double speed = 4f;
	public ArrayList<Bullet> bullets = new ArrayList<>();
	private double rateOfFire = 3f;
	private double shotTime = 0;
	private double damage = 25;
	public double maxHP = 100;
	public double currentHP = maxHP;
	public SoundEffect shotSound = new SoundEffect();

	public PlayerType type = PlayerType.HEAVY;

	public PlayerType getPlayerType() {
		return type;
	}

    public PlayerLight() {
		calculateDimensions();
	}

	public double getDamage() {
        return damage;
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
			shotSound.play(1);
		}
		shotTime += rateOfFire;

		if (shotTime > 50) {
			shotTime = 0;
		}
	}
}
