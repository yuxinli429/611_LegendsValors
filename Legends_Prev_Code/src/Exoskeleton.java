//child class to Monster class used to set the monster type as Exoskeleton for all exoskeletons created from Monsters class using mapper of the Exoskeleton class

public class Exoskeleton extends Monster{
	
	public Exoskeleton() {
		this.setMonsterType(MonsterType.EXOSKELETONS);
	}

}
