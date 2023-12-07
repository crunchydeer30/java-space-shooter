package attacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import game.Entity;
import game.GameScreen;
import game.Utils;
import player.Player;

public class HomingSphere extends Attack {
  public Shape shape;
  public double x;
  public double y;
  public double speed;
  public double size;
  public Color color;
  public double angle;
  public double damage;
  public int bulletAngle = 0;
  public Entity entity;
  public Player player = GameScreen.levelManager.currentLevel.getPlayer();
  public boolean targetCaptured = false;
  public double targetDistance;

  public HomingSphere(Entity entity, double x, double y, double size, double damage, Color color, double speed, double targetDistance) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.speed = speed;
        this.targetDistance = targetDistance;
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
    if (!targetCaptured && entity.getY() < player.getY()) {
      angle = Utils.getAnglePoints(player.getX(), player.getY(), x, y);
      if (Utils.getDistance(x, y, player.getX(), player.getY()) < targetDistance) {
        targetCaptured = true;
      }
    }
    x += -Math.cos(Math.toRadians(angle)) * speed;
    y += -Math.sin(Math.toRadians(angle)) * speed;
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
