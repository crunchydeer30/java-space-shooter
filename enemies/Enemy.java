package enemies;

import java.awt.Graphics2D;
import java.util.Random;
import game.GameScreen;
import game.Entity;

public abstract class Enemy extends Entity {
    public void checkBounds() {
        if (!inBounds(GameScreen.gameWidth, GameScreen.gameHeight)) {
            Random rand = new Random();
            setPosition(rand.nextInt(GameScreen.gameWidth), getSize());
        }
    }

    public void update() {
        move();
        attack();
        updateAttacks();
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
        drawAttacks(g2);
	}

}
