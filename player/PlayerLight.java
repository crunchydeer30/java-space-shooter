package player;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import attacks.Attack;
import attacks.Sphere;
import game.GameScreen;
import game.KeyboardManager;
import sounds.SoundEffectPlayer;

public class PlayerLight extends Player {
    KeyboardManager keyboardManager = GameScreen.keyboardManager;
	private Image sprite = new ImageIcon("assets/graphics/spaceship.png").getImage();
	private double size = 50;
	private double width;
	private double height;
	private double x;
	private double y;
	private double speed = 8f;
	private double rateOfFire = 5f;
	private double shotTime = 0;
	private double damage = 25;
	public double maxHP = 200;
	public double currentHP = maxHP;
	public SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();
	public ArrayList<Attack> attacks = new ArrayList<Attack>();

	public PlayerType type = PlayerType.LIGHT;

	public PlayerType getPlayerType() {
		return type;
	}

  public PlayerLight() {
		calculateDimensions();
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
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

	public void setPosition(double x, double y) {
		this.x = x - size / 2;
		this.y = y - size / 2;
	};

	public void attack() {
		if (shotTime == 0) {
			attacks.add(new Sphere(this, x, y, 10, damage, Color.ORANGE, 12f, 270));
			attacks.add(new Sphere(this, x + size - 5, y, 10, damage, Color.ORANGE, 12f, 270));
			soundEffectPlayer.play("shot");
		}
		shotTime += rateOfFire;

		if (shotTime > 50) {
			shotTime = 0;
		}
	}
}
