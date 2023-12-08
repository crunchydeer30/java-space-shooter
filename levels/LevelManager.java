package levels;

import java.util.ArrayList;

import game.GameScreen;
import stateManager.GameStateType;

public class LevelManager {
    public Level currentLevel;
    public ArrayList<Level> levelList = new ArrayList<>();
    public int currentLevelIdx;

    public void init() {
        currentLevelIdx = 0;
        levelList.removeAll(levelList);
        levelList.add(new Level1());
        levelList.add(new Level2());
        // levelList.add(new Level3());
        levelList.add(new Level4());
        currentLevel = levelList.get(0);
        currentLevel.init();
    }

    public void update() {
        currentLevel.update();
        
        if (GameScreen.keyboardManager.isKeyEscape) {
            GameScreen.stateManager.setGameState(GameStateType.MENU);
        }

        if (currentLevel.isCompleted() == true) {
            int curIdx = levelList.indexOf(currentLevel);

            if (curIdx == levelList.size() - 1) {
                GameScreen.stateManager.setGameState(GameStateType.MENU);
            } else {
                Level prevLevel = currentLevel;
                currentLevel = levelList.get(curIdx + 1);
                levelList.remove(prevLevel);
                currentLevel.init();
                currentLevelIdx++;
            }
        }
    }

    public void reset() {
        if (currentLevel != null) {
            currentLevel.levelMusic.stop();
        }
        currentLevelIdx = 0;
        levelList.removeAll(levelList);
    }
}
