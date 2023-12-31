package levels;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import bosses.Boss;
import bosses.JET.Jet;
import enemies.Enemy;
import game.Effect;
import game.GameScreen;
import player.Player;
import sounds.MusicPlayer;

public class Level2 extends Level {
    public Player player;
    public ArrayList<Enemy> enemies;
    public boolean isCompleted = false;

    public int enemiesCount = 1;

    public int enemiesKilled = 0;
    public int enemiesSpawned = 0;
    public int enenySpawnTimer = 0;
    public int enenySpawnDelay = 200;

    public Image backgroundImage = new ImageIcon("assets/graphics/background_4.png").getImage();

    public MusicPlayer levelMusic = new MusicPlayer("super_stripe");

    public ArrayList<Effect> effects = new ArrayList<Effect>();

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public Boss getBoss() {
        return null;
    }

    public MusicPlayer getLevelMusic() {
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
            Boss enemy = new Jet();

            enemy.setPosition(GameScreen.gameWidth / 2 - enemy.getSize() / 2, 100);
            enemies.add(enemy);
            enemiesSpawned++;
        }
    }
}
