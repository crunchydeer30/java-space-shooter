package bosses.UFO.phases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import bosses.Boss;
import bosses.Phase;
import bosses.specialAttacks.Beam;
import bosses.specialAttacks.Circle;
import bosses.specialAttacks.SpecialAttack;
import bosses.specialAttacks.Pulse;
import enemies.Enemy;
import game.Bullet;
import game.GameScreen;

public class Phase5 extends Phase {
  public double pulseTimer = 0;
  public ArrayList<SpecialAttack> specialAttacks;
  public ArrayList<Bullet> bullets;
  Random rand = new Random();
  public double beamTimer = 0;
  public boolean moveRight = true;
  public Boss boss;

  public Phase5(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    specialAttacks = boss.getSpecialAttacks();
    bullets = boss.getBullets();

    pulse();
    beams();
    // bullets();
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
      specialAttacks.add(new Pulse(GameScreen.gameWidth * 0.25, GameScreen.gameHeight * 0.25, -1f, 1.5f, 100, Color.RED));
      specialAttacks.add(new Pulse(GameScreen.gameWidth * 0.75, GameScreen.gameHeight * 0.25, 1f, 1.5f, 100, Color.RED));
    }

    pulseTimer++;
    if (pulseTimer == 1000) {
      pulseTimer = 0;
    }
  }

  public void beams() {
    if (beamTimer == 0) {
        for (int i = -2; i < 5; i++) {
          specialAttacks.add(new Beam(boss.getX() + boss.getSize() + 30 * i, boss.getY() - 30 * i, 90, Color.RED));
          specialAttacks.add(new Beam(boss.getX() - 30 * i, boss.getY() - 30 * i, 90, Color.RED));
        }
    }

    beamTimer++;
    if (beamTimer == 400) {
      beamTimer = 0;
    }
  }

  public void destroy() {

  }
}
