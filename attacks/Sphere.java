package attacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import enemies.Enemy;
import game.Entity;

public class Sphere extends Attack {
    private Shape shape;
    private double x;
    private double y;
    private double velocity;
    private double size;
    private Color color;
    private double angle;
    private double damage;
    public int bulletAngle = 0;
    public Entity entity;

    public Sphere(Entity entity, double x, double y, double size, double damage, Color color, double velocity, double angle) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.velocity = velocity;
        this.angle = angle;
        this.shape = new Ellipse2D.Double(0, 0, size, size);
        this.damage = damage;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public double getDamage() {
        return damage;
    }

    public Shape getHitbox() {
        return new Area(new Ellipse2D.Double(x, y, size, size));
    }

    public boolean inBounds(int width, int height) {
        if (x > width || x < 0 || y > height || y < 0) {
            return false;
        }
        return true;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocity() {
        return velocity;
    }

    public void update() {
        x += Math.toRadians(Math.cos(angle)) * velocity;
        y += Math.toRadians(Math.sin(angle)) * velocity;
        removeBullet();
    }

    public void removeBullet() {
        if (!inBounds(GameScreen.gameWidth, GameScreen.gameHeight)) {
            ArrayList<Bullet> playerBullets = GameScreen.levelManager.currentLevel.getPlayer().getBullets();
            ArrayList<Enemy> enemies = GameScreen.levelManager.currentLevel.getEnemies();

            if (playerBullets.indexOf(this) != 1) {
                playerBullets.remove(this);
            }

            for (int i = 0; i < enemies.size(); i++) {
                ArrayList<Bullet> bullets = enemies.get(i).getBullets();
                if (bullets.indexOf(this) != -1) {
                    bullets.remove(this);
                    break;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.setColor(color);
        g2.translate(x, y);
        g2.fill(shape);
        g2.setTransform(oldTransform);
    }

    public void destroy() {

    }
}
