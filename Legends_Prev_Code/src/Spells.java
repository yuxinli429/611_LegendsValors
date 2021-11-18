import java.util.ArrayList;
import java.util.List;

//Created to call parser to load the config file, read from the output 2d list and map the spells to the spell class
public class Spells {
	
	private List<Spell> spellsList;
	private ArrayList<ArrayList<String>> icespellList;
	private ArrayList<ArrayList<String>> fireSpellsList;
	private ArrayList<ArrayList<String>> lightningSpellsList;
	
	public Spells(FileToList filereader) {
		this.icespellList = new ArrayList<ArrayList<String>>();
		icespellList = filereader.readFile("IceSpells.txt");
		this.fireSpellsList = new ArrayList<ArrayList<String>>();
		fireSpellsList = filereader.readFile("FireSpells.txt");
		this.lightningSpellsList = new ArrayList<ArrayList<String>>();
		lightningSpellsList = filereader.readFile("LightningSpells.txt");
	}

	public List<Spell> getSpellsList() {
		return spellsList;
	}
	
	public List<Spell> createSpellsList(){
		spellsList = new ArrayList<>();
		for(int i =1; i<icespellList.size();i++) {
			IceSpell iceSpell = new IceSpell();
			iceSpell.mapObject(icespellList.get(i));
			spellsList.add(iceSpell);
		}
		for(int i =1; i<fireSpellsList.size();i++) {
			FireSpell fireSpell = new FireSpell();
			fireSpell.mapObject(fireSpellsList.get(i));
			spellsList.add(fireSpell);
		}
		for(int i =1; i<lightningSpellsList.size();i++) {
			LightningSpell lightningSpell = new LightningSpell();
			lightningSpell.mapObject(lightningSpellsList.get(i));
			spellsList.add(lightningSpell);
		}			
		return spellsList;		
	}
	
	public void displaySpellsList() {
		ArrayList<ArrayList<String>> allSpellsList = new ArrayList<ArrayList<String>>();
		
		for(int i=1;i<icespellList.size();i++) {
			icespellList.get(i).add(SpellTypes.ICESPELL.getSpellTypeName());
		}
		allSpellsList.addAll(icespellList);
		allSpellsList.get(0).add("Spell Type");
		
		for(int i=1;i<fireSpellsList.size();i++) {
			fireSpellsList.get(i).add(SpellTypes.FIRESPELL.getSpellTypeName());
		}
		for(int i=1;i<fireSpellsList.size();i++) {
			allSpellsList.add(fireSpellsList.get(i));
		}
		
		for(int i=1;i<lightningSpellsList.size();i++) {
			lightningSpellsList.get(i).add(SpellTypes.LIGHTNINGSPELL.getSpellTypeName());
		}
		for(int i=1;i<lightningSpellsList.size();i++) {
			allSpellsList.add(lightningSpellsList.get(i));
		}
		GameDesigns.tableWithLines(allSpellsList, true);
	}

}
