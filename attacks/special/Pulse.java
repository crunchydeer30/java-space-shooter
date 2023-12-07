package attacks.special;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import attacks.Attack;
import attacks.Sphere;
import game.Entity;

public class Pulse extends Attack {
  public Entity entity;
  public double speed;
  public double size;
  public double x;
  public double y;
  public Color color;
  public double angle = 90;
  public double liveTime = 1000;
  public double timer = 0;
  public double angleIncrement;
  public ArrayList<Sphere> minorSpeheres = new ArrayList<Sphere>();
  public double damage;
  public double shotTime = 0;
  public double rateOfFire = 10;

  public Pulse(Entity entity, double x, double y, double angleIncrement, double speed, double size, Color color) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.angleIncrement = angleIncrement;
    this.entity = entity;
  }

  public Entity getEntity() {
    return entity;
  }

  public double getDamage() {
    return damage;
  }

  public Shape getHitbox() {
    return new Ellipse2D.Double(x, y, size, size);
  }

  public void update() {
    if (timer >= liveTime) {
      entity.getAttacks().remove(this);
    }

    timer++;
    angle += angleIncrement;
    x += Math.cos(Math.toRadians(angle)) * speed;
    y += Math.sin(Math.toRadians(angle)) * speed;

    shoot();
    for (int i = 0; i < minorSpeheres.size(); i++) {
      minorSpeheres.get(i).update();
    }
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.fillOval((int) x, (int) y, (int) size, (int) size);

    for (int i = 0; i < minorSpeheres.size(); i++) {
      minorSpeheres.get(i).draw(g2);
    }
  }

  public void shoot() {
    if (shotTime == 0) {
      for (int i = 0; i < 360; i += 10) {
        minorSpeheres.add(new Sphere(entity, x + size / 2, y + size / 2, 20, damage, color, 6f, i));
      }
      shotTime++;
      if (shotTime > 200) {
        shotTime = 0;
      }
    }
  }
}