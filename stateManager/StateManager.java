package stateManager;

import java.awt.Graphics2D;
import java.util.HashMap;

public class StateManager {
    public static HashMap<GameStateType, GameState> gameStates = new HashMap<>();
    public GameState currentGameState;  

    public StateManager() {
        gameStates.put(GameStateType.MENU, new StateMenu());
        gameStates.put(GameStateType.GAMEPLAY, new StateGameplay());
        gameStates.put(GameStateType.OPTIONS, new StateOptions());
        gameStates.put(GameStateType.GAMEOVER, new StateGameOver());
        setGameState(GameStateType.MENU);
    }

    public GameState getGameState() {
        return currentGameState;
    }

    public void setGameState(GameStateType gameStateType) {
        if (currentGameState != null) {
            currentGameState.dispose();
        }
        currentGameState = gameStates.get(gameStateType);
        currentGameState.init();
    }

    public void draw(Graphics2D g2) {
        currentGameState.draw(g2);
    }

    public void update() {
        currentGameState.update();
    }
}
