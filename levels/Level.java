package levels;
import java.awt.Graphics2D;
import java.util.ArrayList;

import enemies.Enemy;
import game.Background;
import game.GameScreen;
import game.GameState;
import game.Player;

public abstract class Level {
    public Player player;

    public abstract Background getBackground();

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
        getBackground().update();
        player.update();
        
        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).update();
        }

        if (getEnemiesKilled() == getEnemiesCount()) {
            setIsCompleted(true);
        }

        if (getPlayer().getCurrentHP() <= 0) {
            GameScreen.stateManager.setGameState(GameState.MENU);
        }
    }

    public void draw(Graphics2D g2) {
        getBackground().draw(g2);
        if (player != null) {
            player.draw(g2);
        }
        spawnEnemies();
        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).draw(g2);
        }
    }

    public void init() {
        setEnemies(new ArrayList<Enemy>());
        setPlayer(new Player());
        player = getPlayer();
        player.setPosition(GameScreen.gameWidth / 2, GameScreen.gameHeight * 0.75);
    }
}
