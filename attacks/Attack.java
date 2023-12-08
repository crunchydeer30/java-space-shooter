package attacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import enemies.Enemy;
import game.Effect;
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
          GameScreen.levelManager.currentLevel.getEffects().add(new Effect(enemy.getX() + enemy.getWidth() / 2, enemy.getY() + enemy.getHeight() / 2, enemy.getSize() / 10, 5, 3, 10, Color.orange));
        };
      } 
    } else if (entity instanceof Enemy) {
      Player player = GameScreen.levelManager.currentLevel.getPlayer();
      if (this.getHitbox().getBounds2D().intersects(player.getHitbox().getBounds2D())) {
        player.setCurrentHP(player.getCurrentHP() - getDamage());
        entity.getAttacks().remove(this);
        GameScreen.levelManager.currentLevel.getEffects().add(new Effect(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, player.getSize() / 10, 5f, 3, 10, Color.orange));
      }
    }
  }
}
