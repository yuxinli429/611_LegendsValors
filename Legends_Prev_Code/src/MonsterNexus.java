import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//child class to Nexus class. Origin of Monsters on each lane and creates monsters based on the hero level in each of the lane
public class MonsterNexus extends NexusCell{

	
	List<Monster> monsterList;
	List<Hero> heroList;
	
	@Override
	public void moveToCell(Hero hero) {
		// TODO Auto-generated method stub
		
	}
	
	public MonsterNexus(List<Monster> monsterList,List<Hero> heroList) {
		this.monsterList = new ArrayList<Monster>();
		this.heroList = new ArrayList<Hero>();
		this.monsterList = monsterList;	
		this.heroList = heroList;
	}
	//Function to create the monsters based on the hero level in each lane
	public List<Monster> createMonsters(GameConfig gameconfig) {
		List<Monster> cellMonsters = new ArrayList<Monster>();
		List<Integer> monsterNexusList = gameconfig.getMonsterNexusList();
		Random rn = new Random();
		int randomLocation = rn.nextInt(2)+1;
		int y;
		if(randomLocation == 1)
			y=0;
		else
			y=1;
		for(int i=0;i<heroList.size();i++) {
			List<Integer> propableMonsters = new ArrayList<Integer>();
			for(int j=0;j<monsterList.size();j++) {
				if(heroList.get(i).getGameLevel() == monsterList.get(j).getGameLevel()) {
					propableMonsters.add(j);
				}				
			}
			Monster newMonster;
			do {
				int randomMonster = rn.nextInt(propableMonsters.size());
				newMonster = monsterList.get(propableMonsters.get(randomMonster));
			}while(cellMonsters.contains(newMonster));
			cellMonsters.add(newMonster);
			newMonster.setHealthPower(100);
			newMonster.setCharacterPosition(monsterNexusList.get(y)+1);
			newMonster.setCharacterSymbol("M"+String.valueOf(i+1));
			y=y+3;
		}
		return cellMonsters;
	}

}
