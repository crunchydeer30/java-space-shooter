package levels;
import java.util.ArrayList;
import java.util.Random;

import enemies.Enemy;
import enemies.Grunt;
import game.GameScreen;
import game.Player;

public class Level3 extends Level {
    public Player player;
    public ArrayList<Enemy> enemies;
    public boolean isCompleted = false;

    public int enemiesCount = 5;
    public int enemiesKilled = 0;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void spawnEnemies() {
        enemiesCount = 3;
        enemiesKilled = 0;
        for (int i = 0; i < enemiesCount; i++) {
            Enemy enemy = new Grunt();
            Random rand = new Random();
            enemy.setPosition(rand.nextInt(GameScreen.gameWidth) - enemy.getSize(), 50);
            enemies.add(enemy);
        }
    }
}