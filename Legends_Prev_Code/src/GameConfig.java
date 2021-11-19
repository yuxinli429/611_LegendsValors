import java.util.List;

//Gameconfig class used to store the static values and use throughout the game

public class GameConfig {
	
	private int playersCount;
	private int teamSize;
	private int gameSize;

	
	public int getPlayersCount() {
		return playersCount;
	}
	
	public void setPlayersCount(int playersCount) {
		this.playersCount = playersCount;
	}
	
	public int getTeamSize() {
		return teamSize;
	}
	
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public int getGameSize() {
		return gameSize;
	}

	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}


}
