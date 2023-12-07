package bosses.Devil.phases;

import java.awt.Color;
import java.util.Random;

import attacks.Sphere;
import attacks.special.Beam;
import bosses.Boss;
import bosses.Phase;
import game.GameScreen;

public class Phase4 extends Phase {
  public double beamTimer = 0;
  public double shotTimer = 0;
  public boolean shootLeft = true;
  public double shotAngle = 35;
  Random rand = new Random();
  public int beamAttackType = 1;
  public boolean moveRight = true;

  public Boss boss;

  public Phase4(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    beams();
    rain();
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
      for (int i = 0; i < GameScreen.gameHeight
          + (int) (GameScreen.gameHeight / 4); i += (int) (GameScreen.gameHeight / 4)) {
        boss.getAttacks().add(new Beam(boss, 0, i, 0, Color.RED, 75, 300, 40) {
          @Override
          public void update() {
            this.liveTime = beamTimer - 100;
            this.timer++;
            if (this.timer < this.chargeTime) {
              radius += 0.25;
            }

            this.endY += Math.sin(Math.toRadians(this.angle)) * this.speed + (new Random().nextDouble(-1, 1) - 0.5) * 4;
            this.endX += Math.cos(Math.toRadians(this.angle)) * this.speed;

            if (this.timer > this.liveTime) {
              this.entity.getAttacks().remove(this);
            }

            if (this.timer > this.chargeTime) {
              this.registerHit();
            }
          }
        });
      }
    }

    beamTimer++;
    if (beamTimer == 300) {
      beamTimer = 0;
    }
  }

  public void rain() {
    if (shotTimer >= 2) {
      boss.getAttacks()
          .add(new Sphere(boss, new Random().nextDouble(0, GameScreen.gameWidth), 0, 25, 5, Color.RED, 12f, 90));
      shotTimer = 0;
    }
    shotTimer++;
  }
}
