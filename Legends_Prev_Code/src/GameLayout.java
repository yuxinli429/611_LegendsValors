//super class used as a based to every game's basic structure

public abstract class GameLayout {
	
	private int gameStartPosition;
	private int gameSize;
	
	public GameLayout(int gameSize) {
		setGameStartPosition(1);
		setGameSize(gameSize);
	}
	
	public GameLayout(int position, int gameSize) {
		setGameStartPosition(position);
		setGameSize(gameSize);
	}
	
	
	/**
     * Draw the game layout
     */
	public abstract void drawLayout();
	
	/**
     * Reset the game layout
     */
	public abstract void resetLayout();

	public int getGameStartPosition() {
		return gameStartPosition;
	}

	public void setGameStartPosition(int gameStartPosition) {
		this.gameStartPosition = gameStartPosition;
	}

	public int getGameSize() {
		return gameSize;
	}

	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}

}
