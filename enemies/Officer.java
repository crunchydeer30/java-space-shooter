package enemies;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Bullet;

public class Officer extends Enemy {
    public double size = 64;
    private double x;
    private double y;
    private float speed = 2f;
    private Image sprite = new ImageIcon("graphics/officer.png").getImage();
    private double rateOfFire = .5f;
    private double shotTime = 0;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    private double maxHP = 100;
    private double currentHP = maxHP;
    private double damage = 20;

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

    public void move() {
        y += speed;
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
