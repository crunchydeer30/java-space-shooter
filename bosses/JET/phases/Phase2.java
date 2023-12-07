package bosses.JET.phases;

import java.awt.Color;
import java.util.Random;

import attacks.HomingSphere;
import attacks.special.Beam;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffectPlayer;

public class Phase2 extends Phase {
  Boss boss;

  public double beamTimer = 0;
  public double shotTimer = 0;
  Random rand = new Random();
  public SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();
  public boolean moveRight = true;

  public Phase2(Boss boss) {
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
    if (boss.getY() <= 50) {
      boss.setY(boss.getY() + 2);
    }

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
    if (beamTimer >= 300) {
      boss.getAttacks().add(new Beam(boss, boss.getX(), boss.getY() + 80, 90, Color.RED));
      boss.getAttacks().add(new Beam(boss, boss.getX() + 80, boss.getY() + 80, 90, Color.RED));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize(), boss.getY() + 80, 90, Color.RED));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize() - 80, boss.getY() + 80, 90, Color.RED));

      beamTimer = 0;
    }

    beamTimer++;
  }

  public void bullets() {
    if (shotTimer >= 50) {
      for (int i = 0; i < 180; i += 10) {
        boss.getAttacks()
            .add(new HomingSphere(boss, boss.getX() + 80, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.YELLOW, 6f, 600));
        boss.getAttacks()
            .add(new HomingSphere(boss, boss.getX() + boss.getWidth() - 80, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.YELLOW, 6f, 600));
      }
      shotTimer = 0;
    }
    shotTimer++;
  }
}
