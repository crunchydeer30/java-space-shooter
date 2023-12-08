package bosses.ufo_boss.phases;

import java.awt.Color;
import java.util.Random;

import attacks.Sphere;
import attacks.special.Beam;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffectPlayer;

public class Phase1 extends Phase {
  Boss boss;

  public double beamTimer = 0;
  public double shotTimer = 0;
  Random rand = new Random();
  public int beamAttackType = 1;
  public SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();
  public boolean moveRight = true;

  public Phase1(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    bullets();
  }

  public void move() {
    if (shotTimer <= 100) {
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

  }

  public void bullets() {
    if (shotTimer >= 100) {
      if (shotTimer % 5 == 0) {
        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2 + 20, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.ORANGE, 8f, 60));
        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.ORANGE, 8f, 80));

        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.ORANGE, 8f, 100));

        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2 - 20, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.ORANGE, 8f, 120));
      }

    }

    if (shotTimer >= 150) {
      shotTimer = 0;
    }

    shotTimer++;
  }
}
