package bosses.JET.phases;

import java.awt.Color;
import java.util.Random;

import attacks.Sphere;
import attacks.special.Beam;
import attacks.special.HomingBeam;
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
    beams();
    bullets();
  }

  public void move() {

    if (beamTimer > 150) {
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
    if (beamTimer == 10) {
      boss.getAttacks().add(new Beam(boss, boss.getX() - 80, boss.getY() + 80, 90, Color.RED, 25, 100, 40));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize() + 80, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 20) {
      boss.getAttacks().add(new Beam(boss, boss.getX() - 40, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize() + 40, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 30) {
      boss.getAttacks().add(new Beam(boss, boss.getX(), boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize(), boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 40) {
      boss.getAttacks().add(new Beam(boss, boss.getX() + 40, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize() - 40, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 50) {
      boss.getAttacks().add(new Beam(boss, boss.getX() + 80, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      boss.getAttacks()
          .add(new Beam(boss, boss.getX() + boss.getSize() - 80, boss.getY() + 80, 90, Color.RED, 50, 100, 40));
      soundEffectPlayer.play("laser_big");
    }


    if (beamTimer >= 200) {
      beamTimer = 0;
    }

    beamTimer++;
  }

  public void bullets() {
    if (shotTimer >= 75) {
      for (int i = 0; i < 180; i += 10 + new Random().nextInt(6)) {
        boss.getAttacks()
            .add(new Sphere(boss, boss.getX() + boss.getWidth() / 2, boss.getY() + boss.getHeight() / 2, 20,
                5, Color.YELLOW, 8f, i));
      }
      shotTimer = 0;
    }
    shotTimer++;
  }
}
