package bosses.Devil.phases;

import java.awt.Color;
import java.util.Random;

import attacks.Sphere;
import attacks.special.Beam;
import attacks.special.HomingBeam;
import attacks.special.Pulse;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffectPlayer;

public class Phase5 extends Phase {
  public double pulseTimer = 0;
  Random rand = new Random();
  public double beamTimer = 0;
  public boolean moveRight = true;
  public Boss boss;
  public double shotTimer;
  public int attackType = 0;
  public SoundEffectPlayer soundEffectPlayer = new SoundEffectPlayer();

  public Phase5(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    bullets();
    beams();
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
    double projectileSize = 40;
    if (shotTimer >= 50) {
      double safeSpace = attackType == 0 ? 0.35 : 0.65;
      attackType = new Random().nextInt(2);
      for (int i = 0; i < GameScreen.gameWidth; i += projectileSize) {
        if (i > GameScreen.gameWidth * safeSpace && i < GameScreen.gameWidth * (safeSpace + 0.2)) {
          continue;
        }
        boss.getAttacks().add(new Sphere(boss, i, 0, projectileSize, 20, Color.RED, 10f, 90));
      }
      shotTimer = 0;
    }

    shotTimer++;
  }

  public void beams() {
    if (beamTimer == 15) {
      boss.getAttacks().add(new HomingBeam(boss, boss.getX() + 80, boss.getY() + 80, 90, Color.RED, 35, 100));
      soundEffectPlayer.play("laser_big");
    }
    if (beamTimer == 30) {
      boss.getAttacks().add(new HomingBeam(boss, boss.getX() + boss.getSize() - 80, boss.getY() + 80, 90, Color.RED, 35, 75));
      soundEffectPlayer.play("laser_big");
    }
    if (beamTimer == 45) {
      boss.getAttacks()
          .add(new HomingBeam(boss, boss.getX() + 80, boss.getY() + 80, 90, Color.RED, 35, 75));
          soundEffectPlayer.play("laser_big");
    }
    if (beamTimer == 60) {
      boss.getAttacks()
          .add(new HomingBeam(boss, boss.getX() + boss.getSize() - 80, boss.getY() + 80, 90, Color.RED, 35, 75));
          soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 75) {
      boss.getAttacks()
          .add(new HomingBeam(boss, boss.getX() + 80, boss.getY() + 80, 90, Color.RED, 35, 75));
          soundEffectPlayer.play("laser_big");
    }

    if (beamTimer == 90) {
      boss.getAttacks()
          .add(new HomingBeam(boss, boss.getX() + boss.getSize() - 80, boss.getY() + 80, 90, Color.RED, 35, 75));
          soundEffectPlayer.play("laser_big");
    }


    if (beamTimer >= 300) {
      beamTimer = 0;
    }
    beamTimer++;
  }

  public void destroy() {

  }
}
