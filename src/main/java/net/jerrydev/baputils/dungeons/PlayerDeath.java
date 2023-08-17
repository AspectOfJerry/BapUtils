package net.jerrydev.baputils.dungeons;

public class PlayerDeath {
    public String playerName;
    public CatacombsMobs suspect;

    public PlayerDeath(String _playerName, CatacombsMobs _suspect) {
        this.playerName = _playerName;
        this.suspect = _suspect;
    }

    public static void handleChat(String cleanMessage) {

    }
}
