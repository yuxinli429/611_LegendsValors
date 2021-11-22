//child class to Hero class used to set the hero type as Warrior for all warriors created from Heroes class using mapper of the Warror class

public class Warrior extends Hero{
	
	public Warrior() {
		this.setHeroType(HeroType.WARRIORS);
	}
}
