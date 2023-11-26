package game;

import java.awt.Graphics2D;

import ui.Menu;
import ui.TitleScreen;

public class StateManager {
    Menu menu = new Menu();
    TitleScreen titleScreen = new TitleScreen();

    GameState gameState = GameState.TITLESCREEN;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        switch (gameState) {
            case TITLESCREEN:
                break;
            case MENU:
                break;
            case GAME:
                GameScreen.levelManager.init();
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
                GameScreen.levelManager.currentLevel.draw(g2);
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
                GameScreen.levelManager.currentLevel.update();
                GameScreen.levelManager.update();
                break;
            case END:
                break;
        }
    }
}
