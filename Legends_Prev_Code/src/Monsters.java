import java.util.ArrayList;
import java.util.List;


//Class created to store the list of monsters, map the monster to monster class from the list obtained from parser
public class Monsters {
	
	private List<Monster> monstersList;

	public List<Monster> getMonstersList() {
		return monstersList;
	}
	public List<Monster> createMonsterList(FileToList readFile){
		monstersList = new ArrayList<>();
		ArrayList<ArrayList<String>> dragonsList = new ArrayList<ArrayList<String>>();
		dragonsList = readFile.readFile("Dragons.txt");
		for(int i =1; i<dragonsList.size();i++) {
			Monster monster = new Monster();
			monster.mapObject(dragonsList.get(i), MonsterType.DRAGONS);
			monstersList.add(monster);
		}
		ArrayList<ArrayList<String>> exoskeletonsList = new ArrayList<ArrayList<String>>();
		exoskeletonsList = readFile.readFile("Exoskeletons.txt");
		//GameDesigns.tableWithLines(exoskeletonsList, true);
		//System.out.println(exoskeletonsList.get(3).get(0)+" "+exoskeletonsList.get(2).get(1));
		for(int i =1; i<exoskeletonsList.size();i++) {
			Monster monster = new Monster();
			monster.mapObject(exoskeletonsList.get(i), MonsterType.EXOSKELETONS);
			monstersList.add(monster);
		}
		ArrayList<ArrayList<String>> spiritsList = new ArrayList<ArrayList<String>>();
		spiritsList = readFile.readFile("Spirits.txt");
		for(int i =1; i<spiritsList.size();i++) {
			Monster monster = new Monster();
			monster.mapObject(spiritsList.get(i), MonsterType.SPIRITS);
			monstersList.add(monster);
		}			
		return monstersList;		
	}

}
