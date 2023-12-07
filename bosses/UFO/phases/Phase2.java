package bosses.UFO.phases;

import java.awt.Color;
import java.util.ArrayList;

import bosses.Boss;
import bosses.Phase;
import bosses.specialAttacks.Bayblade;
import bosses.specialAttacks.SpecialAttack;
import game.Bullet;
import game.GameScreen;
import sounds.SoundEffect;

public class Phase2 extends Phase {
  public Boss boss;
  public double shotTimer = 0;
  public double baybladeTimer = 0;
  public boolean moveRight = true;
  public ArrayList<SpecialAttack> specialAttacks;
  public ArrayList<Bullet> bullets;
  public boolean baybladeRight = true;
  public SoundEffect shotSound = new SoundEffect();

  public Phase2(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    specialAttacks = boss.getSpecialAttacks();
    bullets = boss.getBullets();
    bullets();
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
        bullets
            .add(new Bullet(boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20, 0, Color.RED, 8f,
                Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))));
      }
      shotSound.play(4);
    }

    if (shotTimer == 25) {
      for (int i = 0; i < 360; i += 10) {
        bullets
            .add(new Bullet(boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 20, 0, Color.RED, 8f,
                Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))));
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
        specialAttacks.add(new Bayblade(boss, boss.getX(), 150, 0.2f, 1.5f, 100, Color.yellow));
        baybladeRight = false;
      } else {
        specialAttacks.add(new Bayblade(boss, boss.getX() + boss.getSize(), 150, -0.2f, 1.5f, 100, Color.yellow));
        baybladeRight = true;
      }
    }

    baybladeTimer++;
    if (baybladeTimer == 500) {
      baybladeTimer = 0;
    }
  }

  public void destroy() {

  }
}
