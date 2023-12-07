package bosses;

import java.awt.Graphics2D;
import java.util.ArrayList;
import bosses.specialAttacks.SpecialAttack;
import enemies.Enemy;
import game.GameScreen;


public abstract class Boss extends Enemy {
    public abstract Phase getCurrentPhase();
    public abstract ArrayList<Phase> getPhases();
    public abstract ArrayList<SpecialAttack> getSpecialAttacks();

    public void update() {
        move();
        shoot();
        updateSpecialAttacks();
        updateBullets();
        registerIncomingDamage();
        checkBounds();

        if (getCurrentHP() <= 0) {
            GameScreen.levelManager.currentLevel.getEnemies().remove(this);
            GameScreen.levelManager.currentLevel.setEnemiesKilled(GameScreen.levelManager.currentLevel.getEnemiesKilled() + 1);
        }
    }

    public void draw(Graphics2D g2) {
        drawSpecialAttacks(g2);
		g2.drawImage(getSprite(), (int)getX(), (int)getY(),  (int)getWidth(), (int)getHeight(), null);
		drawHP(g2);
        drawBullets(g2);
	}

    public void updateSpecialAttacks() {
        for (int i = 0; i < getSpecialAttacks().size(); i++) {
            getSpecialAttacks().get(i).update();
        }
    }

    public void drawSpecialAttacks(Graphics2D g2) {
        for (int i = 0; i < getSpecialAttacks().size(); i++) {
            getSpecialAttacks().get(i).draw(g2);
        }
    }
}
