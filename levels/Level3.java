package levels;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import bosses.Boss;
import bosses.ufo_boss.UFO_Boss;
import enemies.Enemy;
import enemies.Ufo;
import game.Effect;
import game.GameScreen;
import player.Player;
import sounds.MusicPlayer;

public class Level3 extends Level {
    public Player player;
    public ArrayList<Enemy> enemies;
    public boolean isCompleted = false;

    public int enemiesCount = 20;
    public int enemiesKilled = 0;

    public int enemiesSpawned = 0;
    public int enenySpawnTimer = 0;
    public int enenySpawnDelay = 150;
    public Boss boss;

    public Image backgroundImage = new ImageIcon("assets/graphics/background_4.png").getImage();

    public MusicPlayer levelMusic = new MusicPlayer("space_harrier");

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
            if (enenySpawnTimer == 0) {
                if (enemiesSpawned == 0) {
                    Boss boss = new UFO_Boss();
                    boss.setPosition(GameScreen.gameWidth / 2 - boss.getSize() / 2, 50);
                    enemies.add(boss);
                    this.boss = boss;
                    enemiesSpawned++;
                }
                if (boss.getCurrentHP() <= boss.getMaxHP() / 2) {
                    Enemy enemy = new Ufo();
                    enemy.setPosition(new Random().nextDouble(GameScreen.gameWidth - enemy.getSize()), 50);
                    enemies.add(enemy);
                    enemiesSpawned++;
                }

                if (boss.getCurrentHP() <= 0) {
                    enemies.removeAll(enemies);
                    enemiesKilled = enemiesCount;
                }
            }

            enenySpawnTimer++;
            if (enenySpawnTimer >= enenySpawnDelay) {
                enenySpawnTimer = 0;
            }
        }
    }
}