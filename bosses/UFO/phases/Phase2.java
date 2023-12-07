package bosses.UFO.phases;

import java.awt.Color;

import attacks.Sphere;
import attacks.special.Beyblade;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffect;

public class Phase2 extends Phase {
  public Boss boss;
  public double shotTimer = 0;
  public double baybladeTimer = 0;
  public boolean moveRight = true;
  public boolean baybladeRight = true;
  public SoundEffect shotSound = new SoundEffect();

  public Phase2(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    bayblades();
  }

  public void move() {
    if (moveRight) {
      boss.setX(boss.getX() + 0.5);
      if (boss.getX() > GameScreen.gameWidth / 2 + 100) {
        moveRight = false;
      }
    } else {
      boss.setX(boss.getX() - 0.5);
      if (boss.getX() < GameScreen.gameWidth / 2 - 100) {
        moveRight = true;
      }
    }
  }

  public void bullets() {
    if (shotTimer == 0) {

      for (int i = 0; i < 360; i += 20) {
        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20, 0, Color.RED, 8f, i));
      }
      shotSound.play(4);
    }

    if (shotTimer == 25) {
      for (int i = 0; i < 360; i += 10) {
        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20, 0, Color.RED, 8f, i));
      }
      shotSound.play(4);
    }

    shotTimer++;
    if (shotTimer == 150) {
      shotTimer = 0;
    }
  }

  public void bayblades() {
    if (baybladeTimer == 0) {
      if (baybladeRight) {
        boss.getAttacks().add(new Beyblade(boss, boss.getX(), 150, 0.2f, 1.5f, 100, Color.yellow));
        baybladeRight = false;
      } else {
        boss.getAttacks().add(new Beyblade(boss, boss.getX() + boss.getSize(), 150, -0.2f, 1.5f, 100, Color.yellow));
        baybladeRight = true;
      }
    }

    baybladeTimer++;
    if (baybladeTimer == 500) {
      baybladeTimer = 0;
    }
  }
}
