package game;

import java.awt.Graphics2D;

import ui.Menu;
import ui.Options;
import ui.TitleScreen;

public class StateManager {
    Menu menu = new Menu();
    TitleScreen titleScreen = new TitleScreen();
    Options options = new Options();

    GameState gameState = GameState.TITLESCREEN;

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        switch (gameState) {
            case TITLESCREEN:
                break;
            case MENU:
                GameScreen.levelManager.reset();
                break;
            case GAME:
                GameScreen.levelManager.init();
                break;
            case OPTIONS:
                break;
            case PAUSE:
                break;
            case END:
                break;
        }
    }

    public void draw(Graphics2D g2) {
        switch (gameState) {
            case TITLESCREEN:
                titleScreen.draw(g2);
                break;
            case MENU:
                menu.draw(g2);
                break;
            case GAME:
                GameScreen.backgroundManager.draw(g2);
                GameScreen.levelManager.currentLevel.draw(g2);
                break;
            case OPTIONS:
                options.draw(g2);
                break;
            case PAUSE:
                break;
            case END:
                break;
        }
    }

    public void update() {
        switch (gameState) {
            case TITLESCREEN:
                titleScreen.update();
                break;
            case MENU:
                menu.update();
                break;
            case GAME:
                GameScreen.backgroundManager.update();
                GameScreen.levelManager.update();
                break;
            case OPTIONS:
                options.update();
                break;
            case PAUSE:
                break;
            case END:
                break;
        }
    }
}
