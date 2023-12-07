package attacks;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import enemies.Enemy;
import game.Entity;
import game.GameScreen;
import player.Player;

public abstract class Attack {
  public Entity entity;

  public abstract void update();
  public abstract void draw(Graphics2D g2);
  public abstract Shape getHitbox();
  public abstract Entity getEntity();
  public abstract double getDamage();

  public void registerHit() {
    this.entity = getEntity();

    if (entity instanceof Player) {
      ArrayList<Enemy> enemies = GameScreen.levelManager.currentLevel.getEnemies();
      for (Enemy enemy : enemies) {
        if (this.getHitbox().getBounds2D().intersects(enemy.getHitbox().getBounds2D())) {
          enemy.setCurrentHP(enemy.getCurrentHP() - getDamage());
          entity.getAttacks().remove(this);
        };
      } 
    } else if (entity instanceof Enemy) {
      Player player = GameScreen.levelManager.currentLevel.getPlayer();
      if (this.getHitbox().getBounds2D().intersects(player.getHitbox().getBounds2D())) {
        player.setCurrentHP(player.getCurrentHP() - getDamage());
        entity.getAttacks().remove(this);
      }
    }
  }
}
