package bosses.specialAttacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GameScreen;

public class Pulse extends SpecialAttack {
  public double speed;
  public double size;
  public double x;
  public double y;
  public Color color;
  public double angle = 90;
  public double liveTime = 1000;
  public double timer = 0;
  public ArrayList<SpecialAttack> specialAttacks;
  public double angleIncrement;

  public double shotTime = 0;
  public double rateOfFire = 10;

  public Pulse(double x, double y, double angleIncrement, double speed, double size, Color color) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.angleIncrement = angleIncrement;
    this.specialAttacks = GameScreen.levelManager.currentLevel.getBoss().getSpecialAttacks();
  }

  public void update() {
    if (timer >= liveTime) {
      if (specialAttacks.indexOf(this) != -1) {
        specialAttacks.remove(this);
      }
    }

    timer++;
    angle += angleIncrement;
    x += Math.cos(Math.toRadians(angle)) * speed;
    y += Math.sin(Math.toRadians(angle)) * speed;

    shoot();
    for (int i = 0; i < specialAttacks.size(); i++) {
      specialAttacks.get(i).update();
    }
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.fillOval((int) x, (int) y, (int) size, (int) size);

    for (int i = 0; i < specialAttacks.size(); i++) {
      specialAttacks.get(i).draw(g2);
    }
  }

  public void shoot() {
    if (shotTime == 0) {
      for (int i = 0; i < 360; i += 10) {
        specialAttacks.add(new Circle(x + size / 2, y + size / 2, i, 6f, 20, color) {
          @Override
          public void update() {
            x += Math.cos(Math.toRadians(angle)) * speed;
            y += Math.sin(Math.toRadians(angle)) * speed;
          }
        });
      }
    }
    shotTime++;
    if (shotTime > 200) {
      shotTime = 0;
    }
  }
}
