//child class to game class and super class to LNM game class. Created to categorise all the role playing games

public abstract class RolePlayGame extends Game {
	
	protected GameConfig GameConfig;
	
	
	protected RolePlayGame() {
		GameConfig = new GameConfig();		
    }
		
	/**
     * RPG game constructor with specific config
     */
    protected RolePlayGame(GameConfig GameConfig) {
        this.GameConfig = GameConfig;
    }


}
