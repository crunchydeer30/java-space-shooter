package levels;
import java.awt.Graphics2D;
import java.util.ArrayList;

import enemies.Enemy;
import game.GameScreen;
import game.Player;

public abstract class Level {
    public abstract Player getPlayer();

    public abstract void setPlayer(Player player);

    public abstract ArrayList<Enemy> getEnemies();

    public abstract void setEnemies(ArrayList<Enemy> enemies);

    public abstract boolean isCompleted();

    public abstract void setIsCompleted(boolean isCompleted);

    public abstract int getEnemiesCount();

    public abstract int getEnemiesSpawned();

    public abstract int getEnenySpawnTimer();

    public abstract int getEnemySpawnDelay();

    public abstract int getEnemiesKilled();

    public abstract void setEnemiesKilled(int enemiesKilled);

    abstract void spawnEnemies();

    public void update() {
        getPlayer().update();
        
        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).update();
        }

        if (getEnemiesKilled() == getEnemiesCount()) {
            setIsCompleted(true);
        }
    }

    public void draw(Graphics2D g2) {
        getPlayer().draw(g2);
        spawnEnemies();
        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).draw(g2);
        }
    }

    public void init() {
        setPlayer(new Player());
        setEnemies(new ArrayList<Enemy>());
        getPlayer().setPosition(GameScreen.gameWidth / 2, GameScreen.gameHeight * 0.75);
    }
}
