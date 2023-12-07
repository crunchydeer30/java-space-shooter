package player;

import java.util.ArrayList;

import enemies.Enemy;
import game.Attack;
import game.GameScreen;

public abstract class PlayerAttack extends Attack {
  public ArrayList<Enemy> enemies = GameScreen.levelManager.currentLevel.getEnemies();

  public abstract void registerHit() {
    for (int )
  }
}
