package game;

import java.util.ArrayList;

import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

public class LevelManager {
    public Level currentLevel;
    public ArrayList<Level> levelList = new ArrayList<>();
    public int currentLevelIdx;

    public void init() {
        currentLevelIdx = 0;
        levelList.removeAll(levelList);
        levelList.add(new Level1());
        levelList.add(new Level2());
        levelList.add(new Level3());
        levelList.add(new Level4());
        currentLevel = levelList.get(0);
        currentLevel.init();
    }

    public void update() {
        if (currentLevel.isCompleted() == true) {
            int curIdx = levelList.indexOf(currentLevel);

            if (curIdx == levelList.size() - 1) {
                GameScreen.stateManager.setGameState(GameState.MENU);
            } else {
                Level prevLevel = currentLevel;
                currentLevel = levelList.get(curIdx + 1);
                currentLevel.init();
                levelList.remove(prevLevel);
                currentLevelIdx++;
            }
        }
    }
}
