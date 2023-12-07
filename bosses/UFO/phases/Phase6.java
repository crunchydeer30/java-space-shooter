package bosses.UFO.phases;

import bosses.Boss;
import bosses.Phase;
import game.GameScreen;
import sounds.SoundEffect;

public class Phase6 extends Phase {
  public double shotTimer = 0;
  public double baybladeTimer = 0;
  public boolean moveRight = true;
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

  public void destroy() {

  }
}
