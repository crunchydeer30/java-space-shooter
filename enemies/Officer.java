package enemies;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import game.Bullet;

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
    public ArrayList<Bullet> bullets = new ArrayList<>();
    private double maxHP = 100;
    private double currentHP = maxHP;
    private double damage = 20;
    public int movementType = 0;
    private double movementTime = 0;

    public Officer() {
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
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

    public void shoot() {
        if (shotTime == 0) {
            bullets.add(new Bullet(x + size / 2 - 5, y + size, 10, damage, Color.WHITE, 4f, Math.cos(Math.toRadians(90)), Math.sin(Math.toRadians(90))));
            bullets.add(new Bullet(x + size / 2 - 5, y + size, 10, damage, Color.WHITE, 4f, Math.cos(Math.toRadians(60)), Math.sin(Math.toRadians(60))));
            bullets.add(new Bullet(x + size / 2 - 5, y + size, 10, damage, Color.WHITE, 4f, Math.cos(Math.toRadians(120)), Math.sin(Math.toRadians(120))));
        }
        shotTime += rateOfFire;
        if (shotTime > 50) {
            shotTime = 0;
        }
    }
}
