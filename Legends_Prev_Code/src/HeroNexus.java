import java.util.List;
import java.util.Random;
import java.util.Scanner;

//HeroNexus class has inventory
public class HeroNexus extends NexusCell{
	
	private List<Potion> potions;
	private List<Spell> spells;
	private List<Weapon> weapons;
	private List<Armor> armors;
	private Potions potionsclass;
	private Spells spellsClass;
	private Weapons weaponsClass;
	private Armors armorsClass;
	
	//used for game logic and builder patterns is implemented here
	public HeroNexus(FileToList filereader) {
		this.potionsclass = new Potions(filereader);
		this.potions = potionsclass.createPotionsList();
		this.spellsClass = new Spells(filereader);
		this.spells = spellsClass.createSpellsList();
		this.weaponsClass = new Weapons(filereader);
		this.weapons = weaponsClass.createWeaponsList();
		this.armorsClass = new Armors(filereader);
		this.armors = armorsClass.createArmorList();	
	}
	
	//empty constructor used to display market designs on console
	public HeroNexus() {
		
	}

	@Override
	public void cellDesign() {
		// TODO Auto-generated method stub
		cellDesignTop();
		System.out.println("|     |");
		//cellDesignBottom();
		System.out.println();
		cellDesignTop();
		
	}
	
	public void cellDesignTop() {		
		System.out.print(GameConstants.ANSI_GREEN+CellType.HERONEXUS.getCellTypeDesign()+" - "+CellType.HERONEXUS.getCellTypeDesign()+" - "+CellType.HERONEXUS.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
	}		

	@Override
	public void moveToCell(Scanner scanner,LNMGameLayout lnmgameLayout) {
		// TODO Auto-generated method stub
		
	}

	public List<Potion> getPotions() {
		return potions;
	}

	public void setPotions(List<Potion> potions) {
		this.potions = potions;
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public List<Armor> getArmors() {
		return armors;
	}

	public void setArmors(List<Armor> armors) {
		this.armors = armors;
	}
	
	public void sellInventory(List<Hero> heroList, Scanner scanner) {
		
		for(int i=0; i<heroList.size();i++) {
			boolean isDone = false;
			while(!isDone) {
				int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,heroList.get(i).getName()+", Please enter the number you would like to buy/perform at the maket:\n1.Potions\n2.Spell\n3.Weapons\n4.Armor\n5.Exit HeroNexus\n6.Choose for Next Hero\nInput: ", 1,6);
				scanner.nextLine();	
				if(inventoryTypeOption==1)
					sellPotion(scanner,heroList.get(i));
				else if(inventoryTypeOption==2)
					sellSpells(scanner,heroList.get(i));
				else if(inventoryTypeOption==3)
					sellWeapons(scanner,heroList.get(i));
				else if(inventoryTypeOption==4)
					sellArmor(scanner,heroList.get(i));
				else if(inventoryTypeOption==5)
					return;
				else if(inventoryTypeOption==6) {
					if(i+1>heroList.size()) {
						System.out.println("No more heroes");
					}
					isDone = true;
				}
			}
				
		}
	}
	
	public void sellPotion(Scanner scanner,Hero hero) {
		potionsclass.displayPotionsList();
		boolean isDone = false;
		while(!isDone) {
			int doneOption =potions.size()+1;
			int potionsSelection = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the option you would like to buy or "+ doneOption+" if you are done: ", 1,potions.size()+1);
			scanner.nextLine();
			if(potionsSelection == potions.size()+1) {
				isDone = true;
			}
			else {
				boolean isPurchase = potions.get(potionsSelection-1).buy(hero);
				if(isPurchase)
					System.out.println("You bought a "+potions.get(potionsSelection-1).getName());
			}			
		}
	}
	
	public void sellArmor(Scanner scanner,Hero hero) {
		armorsClass.displayArmors();
		boolean isDone = false;
		while(!isDone) {
			int doneOption =armors.size()+1;
			int armorSelection = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the option you would like to buy or "+ doneOption+" if you are done: ", 1,armors.size()+1);
			scanner.nextLine();
			if(armorSelection == armors.size()+1) {
				isDone = true;
			}
			else {
				boolean isPurchase = armors.get(armorSelection-1).buy(hero);
				if(isPurchase)
					System.out.println("You bought a "+armors.get(armorSelection-1).getName());
			}			
		}
	}
	
	public void sellSpells(Scanner scanner,Hero hero) {
		spellsClass.displaySpellsList();
		boolean isDone = false;
		while(!isDone) {
			int doneOption =spells.size()+1;
			int spellSelection = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the option you would like to buy or "+ doneOption+" if you are done: ", 1,spells.size()+1);
			scanner.nextLine();
			if(spellSelection == spells.size()+1) {
				isDone = true;
			}
			else {
				boolean isPurchase = spells.get(spellSelection-1).buy(hero);
				if(isPurchase)
					System.out.println("You bought a "+spells.get(spellSelection-1).getName());
			}			
		}
	}
	
	public void sellWeapons(Scanner scanner,Hero hero) {
		weaponsClass.displayWeapons();
		boolean isDone = false;
		while(!isDone) {
			int doneOption =weapons.size()+1;
			int weaponSelection = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the option you would like to buy or "+doneOption +" if you are done: ", 1,weapons.size()+1);
			scanner.nextLine();
			if(weaponSelection == weapons.size()+1) {
				isDone = true;;
			}
			else {
				boolean isPurchase = weapons.get(weaponSelection-1).buy(hero);
				if(isPurchase)
					System.out.println("You bought a "+weapons.get(weaponSelection-1).getName());
			}			
		}
	}
	
	public void spawnHero(List<Hero> heroList, List<Integer> heroNexusList){
		Random rn = new Random();
		int randomLocation = rn.nextInt(2)+1;
		int y;
		if(randomLocation == 1)
			y=0;
		else
			y=1;
		for(Hero hero:heroList) {
			hero.setCharacterPosition(heroNexusList.get(y)+1);
			y=y+3;
		}
		
	}

}
