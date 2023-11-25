package enemies;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import game.Bullet;

public class Grunt extends Enemy {
    Random rand = new Random();

    public double size = 64;
    private double x;
    private double y;
    private float speed = 1.5f;
    private Image sprite = new ImageIcon("graphics/grunt.png").getImage();
    private double rateOfFire = .25f;
    private double shotTime = 0;

    public int movementType = 0;
    private double movementTime = 0;   
    public ArrayList<Bullet> bullets = new ArrayList<>();

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
        if (movementTime == 0) {
            movementType = rand.nextInt(3);
        }

        if (movementType == 0) {
            y += speed;
        } else if (movementType == 1) {
            y += speed;
            x += speed;
        } else if (movementType == 2) {
            y += speed;
            x -= speed;
        }

        movementTime++;
        if (movementTime > 50) {
            movementTime = 0;
        }
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
            bullets.add(new Bullet(x + size / 2 - 5, y, 10, Color.YELLOW, 8f, Math.cos(Math.toRadians(90)), Math.sin(Math.toRadians(90))));
        }
        shotTime += rateOfFire;
        if (shotTime > 50) {
            shotTime = 0;
        }
    }
}
