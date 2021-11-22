//child class to Hero class used to set the hero type as Paladin for all paladins created from Heroes class using mapper of the Sorcerer class

public class Sorcerer extends Hero{
	
	public Sorcerer() {
		this.setHeroType(HeroType.SORCERERS);
	}

}
