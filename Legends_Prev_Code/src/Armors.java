import java.util.ArrayList;
import java.util.List;

//Created to call parser to load the config file, read from the output 2d list and map the armors to the armor class
public class Armors {
	
	private ArrayList<Armor> armorList;
	private ArrayList<ArrayList<String>> armorTextList;
	
	public Armors(FileToList fileReader) {
		this.armorTextList = new ArrayList<ArrayList<String>>();
		armorTextList = fileReader.readFile("Armory.txt");
	}
	//uses parser to read from file
	public List<Armor> createArmorList(){
		armorList = new ArrayList<>();
		for(int i =1; i<armorTextList.size();i++) {
			Armor armor = new Armor();
			armor.mapObject(armorTextList.get(i));
			armorList.add(armor);
		}
		return armorList;
	}

	public List<Armor> getArmorList() {
		return armorList;
	}
	
	public void displayArmors() {
		GameDesigns.tableWithLines(armorTextList, true);
	}

}
