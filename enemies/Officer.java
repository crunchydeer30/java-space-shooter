package enemies;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import attacks.Attack;
import attacks.HomingSphere;
import attacks.Sphere;

public class Officer extends Enemy {
    private Random rand = new Random();
    private Image sprite = new ImageIcon("assets/graphics/officer.png").getImage();
    private double size = 64;
	private double width;
	private double height;
    private double x;
    private double y;
    private float speed = 2f;
    private double rateOfFire = .5f;
    private double shotTime = 0;
    private double maxHP = 100;
    private double currentHP = maxHP;
    private double damage = 20;
    public int movementType = 0;
    private double movementTime = 0;
    public ArrayList<Attack> attacks = new ArrayList<Attack>();

    public Officer() {
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
        return currentHP;
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

    public void move() {
        if (movementTime == 0) {
            movementType = rand.nextInt(3);
        }

        if (movementType == 0) {
            y += 0.5 * speed;
        } else if (movementType == 1) {
            y += 0.5 * speed;
            x += speed;
        } else if (movementType == 2) {
            y += 0.5 * speed;
            x -= speed;
        }

        movementTime++;
        if (movementTime > 50) {
            movementTime = 0;
        }
    }

    public void attack() {
        if (shotTime >= 75) {
            for (int i = 60; i <= 120; i += 15) {
                attacks.add(new Sphere(this, x + size / 2 - 5, y + size, 15, damage, Color.WHITE, 6f, i));
            }
            shotTime = 0;
        }
        shotTime += rateOfFire;
    }
}
