package attacks.special;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import attacks.Attack;
import game.Entity;
import game.GameScreen;
import game.Utils;
import player.Player;

public class HomingBeam extends Attack {
  public Entity entity;

  public double startX;
  public double startY;
  public double endX;
  public double endY;
  public double speed = 40;
  public double timer = 0;
  public double chargeTime = 100;
  public double liveTime = 150;
  public double angle;
  public Color color;
  public double radius = 5;
  public double damage = 5;
  public Player player = GameScreen.levelManager.currentLevel.getPlayer();
  public boolean targetCaptured = false;
  public double targetDistance = 500;

  public HomingBeam(Entity entity, double startX, double startY, double angle, Color color, double chargeTime,
      double liveTime) {
    this.startX = startX;
    this.startY = startY;
    this.endX = startX;
    this.endY = startY;
    this.angle = angle;
    this.color = color;
    this.player = GameScreen.levelManager.currentLevel.getPlayer();
    this.entity = entity;
    this.chargeTime = chargeTime;
    this.liveTime = liveTime;
  }

  public void update() {
    timer++;
    if (timer < chargeTime) {
      radius += 0.25;

      if (!targetCaptured) {
        angle = Utils.getAnglePoints(player.getX(), player.getY(), endX, endY);
      }

      endY += -Math.sin(Math.toRadians(angle)) * speed;
      endX += -Math.cos(Math.toRadians(angle)) * speed;
    }

    targetCaptured = true;

    if (timer > liveTime) {
      entity.getAttacks().remove(this);
    }

    if (timer > chargeTime) {
      registerHit();
    }
  }

  @Override
  public void registerHit() {
    if (getHitbox().intersects(player.getHitbox().getBounds2D())) {
      player.setCurrentHP(player.getCurrentHP() - damage);
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
    if (timer > chargeTime) {
      g2.setColor(color);
      g2.setStroke(new BasicStroke(10));
      g2.drawLine((int) startX, (int) startY, (int) endX, (int) endY);
    }

    g2.setColor(Color.WHITE);
    g2.setStroke(new BasicStroke(2));
    g2.drawLine((int) startX, (int) startY, (int) endX, (int) endY);

    g2.setColor(color);
    g2.fillOval((int) (startX - radius / 2), (int) (startY - radius / 2), (int) (radius), (int) (radius));
    g2.setColor(Color.WHITE);
    g2.fillOval((int) (startX - radius / 2 * 0.65), (int) (startY - radius / 2 * 0.65), (int) (radius * 0.65),
        (int) (radius * 0.65));
  }
}
