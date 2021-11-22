//child class to Hero class used to set the hero type as Paladin for all paladins created from Heroes class using mapper of the Paladin class

public class Paladin extends Hero{
	
	public Paladin() {
		this.setHeroType(HeroType.PALADINS);
	}

}
