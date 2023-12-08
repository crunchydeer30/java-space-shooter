package bosses.Devil.phases;

import java.awt.Color;
import java.util.Random;

import attacks.special.Beam;
import attacks.special.Pulse;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffectPlayer;

public class Phase6 extends Phase {
  public double pulseTimer = 0;
  Random rand = new Random();
  public double beamTimer = 0;
  public boolean moveRight = true;
  public Boss boss;

  public Phase6(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    pulse();
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

  public void pulse() {
    if (pulseTimer == 0) {
      boss.getAttacks().add(new Pulse(boss, GameScreen.gameWidth * 0.25, GameScreen.gameHeight * 0.25, -1f, 1.5f, 100, Color.RED));
      boss.getAttacks().add(new Pulse(boss, GameScreen.gameWidth * 0.75, GameScreen.gameHeight * 0.25, 1f, 1.5f, 100, Color.RED));
    }

    pulseTimer++;
    if (pulseTimer >= 200) {
      pulseTimer = 0;
    }
  }

  public void beams() {
    if (beamTimer == 0) {
      for (int i = -2; i < 5; i++) {
        boss.getAttacks().add(new Beam(boss, boss.getX() + boss.getSize() + 30 * i, boss.getY() - 30 * i, 90, Color.RED, 100, 150, 40));
        boss.getAttacks().add(new Beam(boss, boss.getX() - 30 * i, boss.getY() - 30 * i, 90, Color.RED, 100, 150, 40));
      }
    }

    beamTimer++;
    if (beamTimer >= 200) {
      beamTimer = 0;
    }
  }

  public void destroy() {

  }
}
