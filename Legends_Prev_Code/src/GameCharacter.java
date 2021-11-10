//super class for monsters and heros used to store the basic information

public class GameCharacter {
	private String Name;
	private double healthPower;
	private int gameLevel;
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	public int getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}

	public double getHealthPower() {
		return healthPower;
	}
	
	public void setHealthPower(double healthPower) {
		this.healthPower = healthPower;
	}


}
