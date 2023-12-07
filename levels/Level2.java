package levels;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import bosses.Boss;
import enemies.Enemy;
import enemies.Grunt;
import game.GameScreen;
import player.Player;

public class Level2 extends Level {
    public Player player;
    public ArrayList<Enemy> enemies;
    public boolean isCompleted = false;

    public int enemiesCount = 2;
    
    public int enemiesKilled = 0;
    public int enemiesSpawned = 0;
    public int enenySpawnTimer = 0;
    public int enenySpawnDelay = 200;

    public Image backgroundImage = new ImageIcon("assets/graphics/background.png").getImage();

    public int levelMusic = 0;

    public Boss getBoss() {
        return null;
    }

    public int getLevelMusic() {
        return levelMusic;
    }

    public Image getBackground() {
        return backgroundImage;
    }

    public void setEnemiesSpawned(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
    }

    public int getEnemiesSpawned() {
        return enemiesSpawned;
    }

    public int getEnenySpawnTimer() {
        return enenySpawnTimer;
    }

    public int getEnemySpawnDelay() {
        return enenySpawnDelay;
    }

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
        if (enemiesSpawned < enemiesCount) {
            if (enenySpawnTimer == 0) {
                Enemy enemy = new Grunt();
                Random rand = new Random();
                enemy.setPosition(rand.nextInt((int)(GameScreen.gameWidth - enemy.getSize())), 50);
                enemies.add(enemy);
                enemiesSpawned++;
            }

            enenySpawnTimer++;
            if (enenySpawnTimer >= enenySpawnDelay) {
                enenySpawnTimer = 0;
            }
        }
    }
}
