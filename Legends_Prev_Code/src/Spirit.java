import java.util.ArrayList;

//child class to Monster class used to set the monster type as Spirit for all spirits created from Monsters class using mapper of the spirit class

public class Spirit extends Monster{
	
	public Spirit() {
		this.setMonsterType(MonsterType.SPIRITS);
	}

}
