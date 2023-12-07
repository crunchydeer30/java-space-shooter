package bosses.Devil.phases;

import java.awt.Color;
import java.util.Random;

import attacks.Sphere;
import attacks.special.Beam;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;

public class Phase3 extends Phase {
  public double beamTimer = 0;
  public double shotTimer = 0;
  public boolean shootLeft = true;
  public double shotAngle = 35;
  Random rand = new Random();
  public boolean moveRight = true; 
  public Boss boss;

  public Phase3(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    beams();
    bullets();
  }

  public void move() {
    if (moveRight) {
      boss.setX(boss.getX() + 2);
      if (boss.getX() > GameScreen.gameWidth / 2 + 100) {
        moveRight = false;
      }
    } else {
      boss.setX(boss.getX() - 2);
      if (boss.getX() < GameScreen.gameWidth / 2 - 100) {
        moveRight = true;
      }
    }
  }

  public void beams() {
    if (beamTimer == 0) {
      for (int i = 0; i < GameScreen.gameHeight + (int)(GameScreen.gameHeight / 4); i += (int) (GameScreen.gameHeight / 4)) {
        boss.getAttacks().add(new Beam(boss, 0, i, 0, Color.RED));
      }
    }
    
    beamTimer++;
    if (beamTimer == 400) {
      beamTimer = 0;
    }
  }

  public void bullets() {
    if (shotTimer == 0) {
      if (shootLeft) {
        shotAngle += 20;
        if (shotAngle > 180) {
          shootLeft = false;
        }
      } else {
        shotAngle -= 20;
        if (shotAngle < 0) {
          shootLeft = true;
        }
      }
      boss.getAttacks().add(new Sphere(boss, boss.getX() + boss.getWidth() / 2, boss.getY() + boss.getHeight() / 2, 25, 5, Color.RED, 12f, shotAngle));
    }

    shotTimer += 20;
    if (shotTimer >= 50) {
      shotTimer = 0;
    }
  }
}
