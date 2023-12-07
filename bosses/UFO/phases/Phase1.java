package bosses.UFO.phases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import bosses.Boss;
import bosses.Phase;
import bosses.specialAttacks.Beam;
import bosses.specialAttacks.SpecialAttack;
import enemies.Enemy;
import game.Bullet;
import game.GameScreen;
import sounds.SoundEffect;

public class Phase1 extends Phase {
  Boss boss;

  public double beamTimer = 0;
  public double shotTimer = 0;
  public boolean shootLeft = true;
  public double shotAngle = 35;
  public ArrayList<SpecialAttack> specialAttacks;
  public ArrayList<Bullet> bullets;
  Random rand = new Random();
  public int beamAttackType = 1;
  public SoundEffect soundEffect = new SoundEffect();

  public boolean moveRight = true;

  public Phase1(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    specialAttacks = boss.getSpecialAttacks();
    bullets = boss.getBullets();

    beams();
    bullets();
  }

  public void move() {
    if (beamTimer > 100) {
      if (moveRight) {
        boss.setX(boss.getX() + 1);
        if (boss.getX() > GameScreen.gameWidth / 2 + 100) {
          moveRight = false;
        }
      } else {
        boss.setX(boss.getX() - 1);
        if (boss.getX() < GameScreen.gameWidth / 2 - 100) {
          moveRight = true;
        }
      }
    }

  }

  public void beams() {
    if (beamTimer == 0) {
      if (beamAttackType == 0) {
        for (int i = -2; i < 5; i++) {
          specialAttacks.add(new Beam(boss.getX() + boss.getSize() + 30 * i, boss.getY() - 30 * i, 90, Color.RED));
          specialAttacks.add(new Beam(boss.getX() - 30 * i, boss.getY() - 30 * i, 90, Color.RED));
        }
      } else {
        for (int i = 0; i < 6; i++) {
          specialAttacks
              .add(new Beam(boss.getX() + boss.getSize() + 30 * i, boss.getY() - 10 * i, 90 - 5 * i, Color.RED));
          specialAttacks.add(new Beam(boss.getX() - 30 * i, boss.getY() - 10 * i, 90 + 2.5 * i, Color.RED));
        }

        for (int i = 0; i < 6; i++) {
          specialAttacks
              .add(new Beam(boss.getX() + boss.getSize() + 30 * i, boss.getY() + 60 * i, 90 + 6.5f * i, Color.RED));
          specialAttacks.add(new Beam(boss.getX() - 30 * i, boss.getY() + 60 * i, 90 - 6.5f * i, Color.RED));
        }
      }
    }

    beamTimer++;
    if (beamTimer == 400) {
      beamAttackType = new Random().nextInt(2);
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

      bullets.add(new Bullet(boss.getX() + boss.getSize() / 2, boss.getY() + boss.getHeight() / 2, 25, 0, Color.RED, 6f,
          Math.cos(Math.toRadians(shotAngle)), Math.sin(Math.toRadians(shotAngle))));
    }

    shotTimer += 20;
    if (shotTimer >= 50) {
      shotTimer = 0;
    }
  }

  public void destroy() {
  }
}
