package bosses.UFO.phases;

import java.awt.Color;
import java.util.Random;

import attacks.special.Beam;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;

public class Phase3 extends Phase {
  public double beamTimer = 0;
  public double shotTimer = 0;
  public boolean shootLeft = true;
  public double shotAngle = 35;
  Random rand = new Random();
  public int beamAttackType = 1;
  public boolean moveRight = true; 
  public Boss boss;

  public Phase3(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
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

  public void beams() {
    if (beamTimer == 0) {
      for (int i = 0; i < GameScreen.gameHeight + (int)(GameScreen.gameHeight / 4); i += (int) (GameScreen.gameHeight / 4)) {
        boss.getAttacks().add(new Beam(boss, 0, i, 90, Color.RED));
      }
    }
    

    beamTimer++;
    if (beamTimer == 400) {
      beamAttackType = new Random().nextInt(2);
      beamTimer = 0;
    }
  }
}
