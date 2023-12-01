package player;
import java.util.ArrayList;

public class PlayerManager {
    public ArrayList<PlayerType> playerTypeList = new ArrayList<PlayerType>();
    public ArrayList<Player> playerModelList = new ArrayList<Player>();
    PlayerType selectedPlayerType;

    public PlayerManager() {
        playerTypeList.add(PlayerType.LIGHT);
        playerTypeList.add(PlayerType.HEAVY);
        selectedPlayerType = playerTypeList.get(0);
        for (int i = 0; i < playerTypeList.size(); i++) {
            playerModelList.add(createPlayer(playerTypeList.get(i)));
        }
    }

    public void setSelectedPlayerType(PlayerType selectedPlayerType) {
        this.selectedPlayerType = selectedPlayerType;
    }

    public Player createPlayer() {
        switch (selectedPlayerType) {
            case LIGHT:
                return new PlayerLight();
            case HEAVY:
                return new PlayerHeavy();
            default:
                return new PlayerLight();
        }
    }

    public Player createPlayer(PlayerType playerType) {
        switch (playerType) {
            case LIGHT:
                return new PlayerLight();
            case HEAVY:
                return new PlayerHeavy();
            default:
                return new PlayerLight();
        }
    }
}
