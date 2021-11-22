import java.util.ArrayList;
import java.util.List;


//Class created to store the list of monsters, map the monster to monster class from the list obtained from parser
public class Monsters {
	
	private List<Monster> monstersList;

	public List<Monster> getMonstersList() {
		return monstersList;
	}
	
	//Function to create monsters from the files and maintain the list of monsters to parse
	public List<Monster> createMonsterList(FileToList readFile){
		monstersList = new ArrayList<>();
		ArrayList<ArrayList<String>> dragonsList = new ArrayList<ArrayList<String>>();
		dragonsList = readFile.readFile("Dragons.txt");
		for(int i =1; i<dragonsList.size();i++) {
			Dragon dragonMonster = new Dragon();
			dragonMonster.mapObject(dragonsList.get(i));
			monstersList.add(dragonMonster);
		}
		ArrayList<ArrayList<String>> exoskeletonsList = new ArrayList<ArrayList<String>>();
		exoskeletonsList = readFile.readFile("Exoskeletons.txt");
		//GameDesigns.tableWithLines(exoskeletonsList, true);
		//System.out.println(exoskeletonsList.get(3).get(0)+" "+exoskeletonsList.get(2).get(1));
		for(int i =1; i<exoskeletonsList.size();i++) {
			Exoskeleton exoskeletonMonster = new Exoskeleton();
			exoskeletonMonster.mapObject(exoskeletonsList.get(i));
			monstersList.add(exoskeletonMonster);
		}
		ArrayList<ArrayList<String>> spiritsList = new ArrayList<ArrayList<String>>();
		spiritsList = readFile.readFile("Spirits.txt");
		for(int i =1; i<spiritsList.size();i++) {
			Spirit spiritMonster = new Spirit();
			spiritMonster.mapObject(spiritsList.get(i));
			monstersList.add(spiritMonster);
		}			
		return monstersList;		
	}

}
