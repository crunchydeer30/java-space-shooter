package attacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import game.Entity;
import game.GameScreen;

public class Sphere extends Attack {
    private Shape shape;
    private double x;
    private double y;
    private double speed;
    private double size;
    private Color color;
    private double angle;
    private double damage;
    public int bulletAngle = 0;
    public Entity entity;

    public Sphere(Entity entity, double x, double y, double size, double damage, Color color, double speed, double angle) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.speed = speed;
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

    public void checkBounds() {
        if (x > GameScreen.gameWidth || x < 0 || y > GameScreen.gameHeight || y < 0) {
            entity.getAttacks().remove(this);
        }
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

    public double getSpeed() {
        return speed;
    }

    public void update() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
        checkBounds();
        registerHit();
    }

    public void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.setColor(color);
        g2.translate(x, y);
        g2.fill(shape);
        g2.setTransform(oldTransform);
    }
}
