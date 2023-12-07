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

public class Phase4 extends Phase {
  public double beamTimer = 0;
  public double shotTimer = 0;
  public boolean shootLeft = true;
  public double shotAngle = 35;
  public ArrayList<SpecialAttack> specialAttacks;
  public ArrayList<Bullet> bullets;
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
    specialAttacks = boss.getSpecialAttacks();
    bullets = boss.getBullets();

    beams();
    bullets();
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
      for (int i = 0; i < GameScreen.gameHeight + (int)(GameScreen.gameHeight / 6); i += (int) (GameScreen.gameHeight / 6)) {
        specialAttacks.add(new Beam(0, i, 0, Color.RED) {

          @Override
          public void update() {
            this.liveTime = 300;
            this.timer++;
            this.startY -= 0.5;
            this.endY += Math.sin(Math.toRadians(this.angle)) * this.speed - 0.5;
            this.endX += Math.cos(Math.toRadians(this.angle)) * this.speed;

            if (this.timer > this.liveTime) {
              specialAttacks.remove(this);
            }
          }
        });
      }
    }
    

    beamTimer++;
    if (beamTimer == 400) {
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

      Random rand = new Random();
      bullets.add(new Bullet(rand.nextInt(GameScreen.gameWidth), rand.nextInt(0, 250), 30, 0, Color.RED, 8f,
          Math.cos(Math.toRadians(90)), Math.sin(Math.toRadians(90))));
    }

    shotTimer += 25;
    if (shotTimer >= 50) {
      shotTimer = 0;
    }
  }

  public void destroy() {

  }
}
