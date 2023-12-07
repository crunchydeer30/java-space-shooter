package levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import bosses.Boss;
import enemies.Enemy;
import game.BackgroundManager;
import game.GameScreen;
import game.Utils;
import player.Player;
import sounds.MusicPlayer;
import stateManager.GameStateType;

public abstract class Level {
    public abstract Image getBackground();

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

    public abstract MusicPlayer getLevelMusic();

    public abstract Boss getBoss();

    abstract void spawnEnemies();

    public boolean startingCutscenePlayed = false;
    public boolean endingCutscenePlayed = true;

    public MusicPlayer levelMusic;

    public void update() {

        if (!startingCutscenePlayed) {
            playStartingCutscene();
        } else if (!endingCutscenePlayed) {
            playEndingCutscene();
        } else {
            GameScreen.backgroundManager.update();
            getPlayer().update();

            for (int i = 0; i < getEnemies().size(); i++) {
                getEnemies().get(i).update();
            }

            if (getEnemiesKilled() == getEnemiesCount()) {
                endingCutscenePlayed = false;
            }

            if (getPlayer().getCurrentHP() <= 0) {
                GameScreen.stateManager.setGameState(GameStateType.MENU);
            }

            spawnEnemies();
        }
    }

    public void draw(Graphics2D g2) {

        if (GameScreen.levelManager.currentLevel.getPlayer() != null) {
            GameScreen.levelManager.currentLevel.getPlayer().draw(g2);
        }

        for (int i = 0; i < getEnemies().size(); i++) {
            getEnemies().get(i).draw(g2);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 36));

        if (!startingCutscenePlayed) {
            String text = "Level " + (GameScreen.levelManager.currentLevelIdx + 1);
            g2.drawString(text, Utils.centerX(g2, text), (int) (GameScreen.gameHeight * 0.8));
        }
    }

    public void init() {
        levelMusic = getLevelMusic();
        levelMusic.play();
        BackgroundManager.setBackground(getBackground());
        setEnemies(new ArrayList<Enemy>());
        setPlayer(GameScreen.playerManager.createPlayer());
        getPlayer().setPosition(GameScreen.gameWidth / 2, GameScreen.gameHeight);
    }

    public void playStartingCutscene() {
        getPlayer().setY(getPlayer().getY() - 3);
        if (getPlayer().getY() <= GameScreen.gameHeight * 0.75) {
            startingCutscenePlayed = true;
        }
    }

    public void playEndingCutscene() {
        getPlayer().setY(getPlayer().getY() - 5);
        if (getPlayer().getY() <= 100) {
            endingCutscenePlayed = true;
            levelMusic.stop();
            setIsCompleted(true);
        }
        getPlayer().update();
    }
}
