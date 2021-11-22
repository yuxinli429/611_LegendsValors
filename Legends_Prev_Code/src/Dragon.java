import java.util.ArrayList;
//child class to Monster class used to set the monster type as Dragon for all dragons created from Monsters class using mapper of the Dragon class

public class Dragon extends Monster{
	
	public Dragon() {
		this.setMonsterType(MonsterType.DRAGONS);
	}

}
