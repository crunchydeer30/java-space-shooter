package attacks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import game.Entity;
import game.GameScreen;
import player.Player;

public class Laser extends Attack {
  public Entity entity;

  public double startX;
  public double startY;
  public double endX;
  public double endY;
  public double speed;
  public double timer = 0;
  public double chargeTime = 100;
  public double liveTime = 150;
  public double angle;
  public Color color;
  public double damage = 5;
  public Player player = GameScreen.levelManager.currentLevel.getPlayer();
  public double length;

  public Laser(Entity entity, double startX, double startY, double length, double speed, double damage, double angle,
      Color color) {
    this.endX = startX;
    this.endY = startY;
    this.angle = angle;
    this.color = color;
    this.speed = speed;
    this.length = length;
    this.player = GameScreen.levelManager.currentLevel.getPlayer();
    this.entity = entity;
  }

  public void update() {
    startX += Math.cos(Math.toRadians(angle)) * speed;
    startY += Math.sin(Math.toRadians(angle)) * speed;
    endY += Math.sin(Math.toRadians(angle)) * speed + length;
    endX += Math.cos(Math.toRadians(angle)) * speed + length;
  }

  public void checkBounds() {
    if (startX > GameScreen.gameWidth || startX < 0 || startY > GameScreen.gameHeight || startY < 0) {
      entity.getAttacks().remove(this);
    }
  }

  public Entity getEntity() {
    return entity;
  }

  public double getDamage() {
    return damage;
  }

  public Shape getHitbox() {
    return new Line2D.Double(startX, startY, endX, endY);
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.setStroke(new BasicStroke(5));
    g2.drawLine((int) startX, (int) startY, (int) endX, (int) endY);

    g2.setColor(Color.YELLOW);
    g2.draw(getHitbox());
  }
}
