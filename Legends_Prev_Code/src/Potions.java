import java.util.ArrayList;
import java.util.List;


//Created to call parser to load the config file, read from the output 2d list and map the potions to the potion class
public class Potions {
	
	private List<Potion> potionsList;
	ArrayList<ArrayList<String>> potionsTextList;
	
	public Potions(FileToList readFile) {
		this.potionsTextList =  new ArrayList<ArrayList<String>>();
		potionsTextList = readFile.readFile("Potions.txt");
	}
	
	public List<Potion> createPotionsList(){
		potionsList = new ArrayList<>();
		for(int i =1; i<potionsTextList.size();i++) {
			Potion potion = new Potion();
			potion.mapObject(potionsTextList.get(i));
			potionsList.add(potion);
		}
		return potionsList;
	}
	
	public void displayPotionsList() {
		GameDesigns.tableWithLines(potionsTextList, true);
	}

	public List<Potion> getPotionsList() {
		return potionsList;
	}
	

}
