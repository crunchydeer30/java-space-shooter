package enemies;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import attacks.Attack;
import attacks.Sphere;
import game.GameScreen;

public class Hiver extends Enemy {
  Random rand = new Random();
  private Image sprite = new ImageIcon("assets/graphics/hiver.png").getImage();
  private double size = 100;
  private double width;
  private double height;
  private double x;
  private double y;
  private float speed = 3f;
  private double rateOfFire = 1f;
  private double shotTime = 0;
  private double damage = 10;
  private double maxHP = 200;
  private double currentHP = maxHP;
  public int movementType = 0;
  private double movementTime = 0;
  public ArrayList<Attack> attacks = new ArrayList<Attack>();
  double deathTimer = 0;
  double liveAfterDeathTimer = 100;
  public boolean isDead = false;
  public boolean isExploded = false;

  @Override
  public void update() {
    move();
    attack();
    updateAttacks();
    checkBounds();

    if (getCurrentHP() <= 0) {
      if (!isDead) {
        GameScreen.levelManager.currentLevel
            .setEnemiesKilled(GameScreen.levelManager.currentLevel.getEnemiesKilled() + 1);
      }
      setHeight(0);
      setWidth(0);
      isDead = true;
    }

    if (isDead) {
      deathTimer++;

      if (!isExploded) {
        explode();
        isExploded = true;
      }

      if (deathTimer >= liveAfterDeathTimer) {
        setHeight(0);
        setWidth(0);
        GameScreen.levelManager.currentLevel.getEnemies().remove(this);
      }
    }
  }

  public Hiver() {
    calculateDimensions();
  }

  public ArrayList<Attack> getAttacks() {
    return attacks;
  }

  public double getDamage() {
    return damage;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return this.height;
  }

  public double getWidth() {
    return this.width;
  }

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

  public double getSpeed() {
    return speed;
  }

  public double getRateOfFire() {
    return rateOfFire;
  }

  public void move() {
    if (movementTime == 0) {
      movementType = rand.nextInt(3);
    }

    if (movementType == 0) {
      y += 0.5 * speed;
    } else if (movementType == 1) {
      y += 0.5 * speed;
      x += speed;
    } else if (movementType == 2) {
      y += 0.5 * speed;
      x -= speed;
    }

    movementTime++;
    if (movementTime > 50) {
      movementTime = 0;
    }
  }

  public void explode() {
    for (int i = 0; i <= 360; i += 20) {
      attacks.add(new Sphere(this, x + size / 2 - 5, y + size, 20, damage, Color.GREEN, 6f, i));
    }
  }

  public void attack() {
  }
}
