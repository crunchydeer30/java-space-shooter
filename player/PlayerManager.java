package player;
import java.util.ArrayList;

public class PlayerManager {
    public ArrayList<PlayerType> playerList = new ArrayList<PlayerType>();
    PlayerType selectedPlayerType;

    public PlayerManager() {
        playerList.add(PlayerType.LIGHT);
        playerList.add(PlayerType.HEAVY);
        selectedPlayerType = playerList.get(1);
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
