import java.util.ArrayList;
import java.util.List;

//Created to call parser to load the config file, read from the output 2d list and map the weapons to the weapon class
public class Weapons {
	
	private List<Weapon> weaponsList;
	private ArrayList<ArrayList<String>> weaponsTextList;
	
	public Weapons(FileToList fileReader) {
		this.weaponsTextList = new ArrayList<ArrayList<String>>();
		weaponsTextList = fileReader.readFile("Weaponry.txt");
	}

	public List<Weapon> getWeaponsList() {
		return weaponsList;
	}
	
	public List<Weapon> createWeaponsList(){
		weaponsList = new ArrayList<>();
		for(int i =1; i<weaponsTextList.size();i++) {
			Weapon weapon = new Weapon();
			weapon.mapObject(weaponsTextList.get(i));
			weaponsList.add(weapon);
		}
		return weaponsList;
	}
	
	public void displayWeapons() {
		GameDesigns.tableWithLines(weaponsTextList, true);
	}

}
