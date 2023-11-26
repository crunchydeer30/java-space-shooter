package levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import enemies.Enemy;
import game.Background;
import game.GameScreen;
import game.GameState;
import game.Player;

public abstract class Level {
    public abstract Background getBackground();

    public abstract Player getPlayer();

    public abstract void setPlayer(Player player);

    public abstract ArrayList<Enemy> getEnemies();

    public abstract void setEnemies(ArrayList<Enemy> enemies);

    public abstract boolean isCompleted();

    public abstract void setIsCompleted(boolean isCompleted);

    public abstract int getEnemiesCount();

    public abstract int getEnemiesSpawned();

    public abstract void setEnemiesSpawned(int enemiesSpawned);

    public abstract int getEnenySpawnTimer();

    public abstract int getEnemySpawnDelay();

    public abstract int getEnemiesKilled();

    public abstract void setEnemiesKilled(int enemiesKilled);

    abstract void spawnEnemies();

    public int titleTimer = 200;

    public void update() {
        getBackground().update();
        getPlayer().update();

        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).update();
        }

        if (getEnemiesKilled() == getEnemiesCount()) {
            setIsCompleted(true);
        }

        if (getPlayer().getCurrentHP() <= 0) {
            GameScreen.stateManager.setGameState(GameState.MENU);
        }

        if (titleTimer >= 0) {
            titleTimer--;
        }
    }

    public void draw(Graphics2D g2) {
        getBackground().draw(g2);
        if (getPlayer() != null) {
            getPlayer().draw(g2);
        }
        spawnEnemies();
        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).draw(g2);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        String text = "Level " + (GameScreen.levelManager.currentLevelIdx + 1);
        g2.drawString(text, 0, GameScreen.gameHeight);
    }

    public void init() {
        setEnemies(new ArrayList<Enemy>());
        setPlayer(new Player());
        getPlayer().setPosition(GameScreen.gameWidth / 2, GameScreen.gameHeight * 0.75);
    }
}
