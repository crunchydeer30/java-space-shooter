package bosses.specialAttacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GameScreen;

public class Circle extends SpecialAttack {
  public double speed;
  public double size;
  public double x;
  public double y;
  public Color color;
  public double angle;
  public ArrayList<SpecialAttack> specialAttacks;

  public Circle(double x, double y, double angle, double speed, double size, Color color) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.size = size;
    this.color = color;
    this.angle = angle;
    this.specialAttacks = GameScreen.levelManager.currentLevel.getBoss().getSpecialAttacks();
  }
  
  public void update() {
    angle+=0.2f;
    x += Math.cos(Math.toRadians(angle)) * speed;
    y += Math.sin(Math.toRadians(angle)) * speed;
  }

  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.fillOval((int)x, (int)y, (int)size, (int)size);
  }
}
