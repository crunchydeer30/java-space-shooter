package bosses.UFO.phases;

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
  public boolean shootLeft = true;
  public double shotAngle = 35;
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
    beams();
    bullets();
  }

  public void move() {
    if (beamTimer > 100) {
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

  public void beams() {
    if (beamTimer == 0) {
      if (beamAttackType == 0) {
        for (int i = -2; i < 5; i++) {
          boss.getAttacks().add(new Beam(boss, boss.getX() + boss.getSize() + 30 * i, boss.getY() - 30 * i, 90, Color.RED));
          boss.getAttacks().add(new Beam(boss, boss.getX() - 30 * i, boss.getY() - 30 * i, 90, Color.RED));
        }
      } else {
        for (int i = 0; i < 6; i++) {
          boss
              .getAttacks().add(new Beam(boss, boss.getX() + boss.getSize() + 30 * i, boss.getY() - 10 * i, 90 - 5 * i, Color.RED));
          boss.getAttacks().add(new Beam(boss, boss.getX() - 30 * i, boss.getY() - 10 * i, 90 + 2.5 * i, Color.RED));
        }

        for (int i = 0; i < 6; i++) {
          boss
              .getAttacks().add(new Beam(boss, boss.getX() + boss.getSize() + 30 * i, boss.getY() + 60 * i, 90 + 6.5f * i, Color.RED));
          boss.getAttacks().add(new Beam(boss, boss.getX() - 30 * i, boss.getY() + 60 * i, 90 - 6.5f * i, Color.RED));
        }
      }
      soundEffectPlayer.play("beam");
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
      boss.getAttacks().add(new Sphere(boss, boss.getX() + boss.getWidth() / 2, boss.getY() + boss.getHeight() / 2, 25, 5, Color.RED, 12f, shotAngle));
    }

    shotTimer += 20;
    if (shotTimer >= 50) {
      shotTimer = 0;
    }
  }
}
