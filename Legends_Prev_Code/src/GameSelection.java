//class being called from main method has game selection options

public class GameSelection {
	
	private Game[] games;
	
	public GameSelection() {
        games = new Game[2];
        games[0] = new LegendsOfValor();
    }
	
	public void startGame() {
		//String audioFilePath = "bensound-epic.wav";
        GameMusic player = new GameMusic();
        try {
			player.playMusic(GameConstants.LNM_FILE_PATH+"bensound-epic.wav");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameDesigns.welcomeDesign("WELCOME");
		games[0].startGame();
		System.out.println("Thank you for playing !!");
	}

}
