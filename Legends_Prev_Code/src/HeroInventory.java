import java.util.ArrayList;
import java.util.List;

//class used to maintan hero's inventory
public class HeroInventory {
	
	private List<Potion> potionsList;
	private List<Spell> spellsList;
	private List<Armor> armorsList;
	private List<Weapon> weaponsList;
	
	//to create empty inventory
	public HeroInventory() {
		potionsList = new ArrayList<Potion>();
		spellsList = new ArrayList<Spell>();
		armorsList = new ArrayList<Armor>();
		weaponsList = new ArrayList<Weapon>();
	}

	public List<Potion> getPotionsList() {
		return potionsList;
	}

	public void setPotionsList(List<Potion> potionsList) {
		this.potionsList = potionsList;
	}

	public List<Spell> getSpellsList() {
		return spellsList;
	}

	public void setSpellsList(List<Spell> spellsList) {
		this.spellsList = spellsList;
	}

	public List<Armor> getArmorsList() {
		return armorsList;
	}

	public void setArmorsList(List<Armor> armorsList) {
		this.armorsList = armorsList;
	}

	public List<Weapon> getWeaponsList() {
		return weaponsList;
	}

	public void setWeaponsList(List<Weapon> weaponsList) {
		this.weaponsList = weaponsList;
	}
	//Functions corresponding to display the types of inventories
	public void displaySpellDetails(){
		ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
		ArrayList<String> header = new ArrayList<String>();
		header.add("Name");
		header.add("Cost");
		header.add("Required Level");
		header.add("Damage");
		header.add("Mana required");
		details.add(header);
		for(int i =0; i<spellsList.size();i++) {
			details.add(spellsList.get(i).getDetails());
		}
		GameDesigns.tableWithLines(details, true);		
	}
	
	public void displayArmorsDetails(){
		ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
		ArrayList<String> header = new ArrayList<String>();
		header.add("Name");
		header.add("Cost");
		header.add("Required Level");
		header.add("Damage Reduction");
		details.add(header);
		for(int i =0; i<armorsList.size();i++) {
			details.add(armorsList.get(i).getDetails());
		}
		GameDesigns.tableWithLines(details, true);		
	}
	
	public void displayPotionsDetails(){
		ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
		ArrayList<String> header = new ArrayList<String>();
		header.add("Name");
		header.add("Cost");
		header.add("Required Level");
		header.add("Attribute Increase");
		header.add("Attributes Impacted");
		details.add(header);
		for(int i =0; i<potionsList.size();i++) {
			details.add(potionsList.get(i).getDetails());
		}
		details.forEach(System.out::println);
		GameDesigns.tableWithLines(details, true);		
	}
	
	public void displayWeaponsDetails(){
		ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
		ArrayList<String> header = new ArrayList<String>();
		header.add("Name");
		header.add("Cost");
		header.add("Required Level");
		header.add("Damage");
		header.add("Required Hands");
		details.add(header);
		for(int i =0; i<weaponsList.size();i++) {
			details.add(weaponsList.get(i).getDetails());
		}
		GameDesigns.tableWithLines(details, true);		
	}

}
