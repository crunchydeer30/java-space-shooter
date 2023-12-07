package enemies;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import bosses.specialAttacks.SpecialAttack;
import game.Bullet;
import game.GameScreen;
import game.Entity;

public abstract class Enemy extends Entity {
    public void registerIncomingDamage() {
        ArrayList<Bullet> playerBullets = GameScreen.levelManager.currentLevel.getPlayer().getBullets();

        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bullet = playerBullets.get(i);
            Shape bulletHitbox = bullet.getHitbox();

            if (bulletHitbox.intersects(this.getHitbox().getBounds2D())) {
                playerBullets.remove(bullet);
                this.setCurrentHP(this.getCurrentHP() - bullet.getDamage());
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

    public void draw(Graphics2D g2) {
		g2.drawImage(getSprite(), (int)getX(), (int)getY(),  (int)getWidth(), (int)getHeight(), null);
		drawHP(g2);
        // drawHitbox(g2);
        drawBullets(g2);
	}

}
