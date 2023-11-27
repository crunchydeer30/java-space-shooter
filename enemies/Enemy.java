package enemies;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import game.Bullet;
import game.GameScreen;
import game.Entity;

public abstract class Enemy extends Entity {
    
    public void registerIncomingDamage() {
        ArrayList<Bullet> playerBullets = GameScreen.levelManager.currentLevel.getPlayer().getBullets();

        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bullet = playerBullets.get(i);
            Area bulletHitbox = new Area(bullet.getHitbox());

            if (bulletHitbox.intersects(this.getHitbox())) {
                playerBullets.remove(bullet);
                this.setCurrentHP(this.getCurrentHP() - bullet.getDamage());
                // GameScreen.soundManager.play(2);
                break;
            }
        }
    }

    public void checkBounds() {
        if (!inBounds(GameScreen.gameWidth, GameScreen.gameHeight)) {
            Random rand = new Random();
            setPosition(rand.nextInt(GameScreen.gameWidth), getSize());
        }
    }

    public void update() {
        move();
        shoot();
        updateBullets();
        registerIncomingDamage();
        checkBounds();

        if (getCurrentHP() <= 0) {
            GameScreen.levelManager.currentLevel.getEnemies().remove(this);
            GameScreen.levelManager.currentLevel.setEnemiesKilled(GameScreen.levelManager.currentLevel.getEnemiesKilled() + 1);
        }
    }
}
