package bosses.UFO.phases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import bosses.Boss;
import bosses.Phase;
import bosses.specialAttacks.SpecialAttack;
import enemies.Enemy;
import game.Bullet;
import game.GameScreen;
import sounds.SoundEffect;

public class Phase6 extends Phase {
  public double shotTimer = 0;
  public double baybladeTimer = 0;
  public boolean moveRight = true;
  public ArrayList<SpecialAttack> specialAttacks;
  public ArrayList<Bullet> bullets;
  public SoundEffect soundEffect = new SoundEffect();
  public int shotPattern = 0;
  public Boss boss;

  public Phase6(Boss boss) {
    this.boss = boss;
  }

  public Boss getBoss() {
    return boss;
  }

  public void shoot() {
    specialAttacks = boss.getSpecialAttacks();
    bullets = boss.getBullets();
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

  public void bullets() {
    if (shotTimer == 0) {
      shotPattern = new Random().nextInt(2);
      for (int i = 0; i < GameScreen.gameWidth; i += 40) {
        if (shotPattern == 0 && i >= GameScreen.gameWidth * 0.25 && i <= GameScreen.gameWidth * 0.4) {
          continue;
        }

        if (shotPattern == 1 && i >= GameScreen.gameWidth * 0.5 && i <= GameScreen.gameWidth * 0.65) {
          continue;
        }
        
        bullets
            .add(new Bullet(0 + i, boss.getY() + boss.getHeight() / 2, 40, 0, Color.RED, 8f,
                Math.cos(Math.toRadians(90)), Math.sin(Math.toRadians(90))));
      }
      soundEffect.play(4);
    }

    shotTimer++;
    if (shotTimer == 50) {
      shotTimer = 0;
    }
  }


  public void destroy() {

  }
}
