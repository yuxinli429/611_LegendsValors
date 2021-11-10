import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Hero class has attributes of all heroes and HeroType and child class of GameCharacter class and contains mapper function to create object

public class Hero extends GameCharacter{
	private double mana;
	private float money;
	private float experience;
	private HeroType heroType;
	private double dexterity;
	private double strength;
	private double agility;
	private int heroLocation;
	private HeroInventory heroInventory;
	private double damage;
	private Weapon heroWeapon;
	private Armor heroArmor;
	private int damageReduction;
	
	//Creates inventory of the hero when hero is created
	public Hero() {
		this.heroInventory = new HeroInventory();
		this.setDamageReduction(0);
		this.setGameLevel(1);
		this.setHealthPower(100*this.getGameLevel());
	}
	

	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public float getExperince() {
		return experience;
	}

	public void setExperince(float experience) {
		this.experience = experience;
	}

	public HeroType getHeroType() {
		return heroType;
	}

	public void setHeroType(HeroType heroType) {
		this.heroType = heroType;
	}

	public double getDexterity() {
		return dexterity;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double skill) {
		this.strength = skill;
	}

	public double getAgility() {
		return agility;
	}

	public void setAgility(double agility) {
		this.agility = agility;
	}

	public int getHeroLocation() {
		return heroLocation;
	}

	public void setHeroLocation(int heroLocation) {
		this.heroLocation = heroLocation;
	}
	
	//mapper to create Hero object rather than using new
	public void mapObject(List<String> heroList) {
		this.setGameLevel(1);
		this.setName(heroList.get(0));
		this.setMana(Double.parseDouble(heroList.get(1)));
		this.setStrength(Integer.parseInt(heroList.get(2)));
		this.setAgility(Integer.parseInt(heroList.get(3)));
		this.setDexterity(Integer.parseInt(heroList.get(4)));
		this.setMoney(Float.parseFloat(heroList.get(5)));
		this.setExperince(Float.parseFloat(heroList.get(6)));
		if(heroList.get(7) == "Paladin")
			this.setHeroType(HeroType.PALADINS);
		else if(heroList.get(7) == "Sorcerer")
			this.setHeroType(HeroType.SORCERERS);
		else
			this.setHeroType(HeroType.WARRIORS);		
	}


	public HeroInventory getHeroInventory() {
		return heroInventory;
	}
	
	//function used to sell in the market
	public void sellHeroInventory(Scanner scanner) {
		boolean isDone = false;
		while(!isDone) {
			int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,this.getName()+", Please enter the number you would like to sell/perform at the maket:\n1.Potions\n2.Spell\n3.Weapons\n4.Armor\n5.Exit Market\nInput: ", 1,5);
			scanner.nextLine();	
			if(inventoryTypeOption==1)
				sellPotion(scanner);
			else if(inventoryTypeOption==2)
				sellSpells(scanner);
			else if(inventoryTypeOption==3)
				sellWeapons(scanner);
			else if(inventoryTypeOption==4)
				sellArmor(scanner);
			else if(inventoryTypeOption==5)
				isDone = true;
		}
	}
	
	//fuction used to sell potions from hero's inventory
	public void sellPotion(Scanner scanner) {
		boolean done = false;
		while(!done) {
			int isDone = this.getHeroInventory().getPotionsList().size()+1;			
			this.getHeroInventory().displayPotionsDetails();
			System.out.println(this.getName()+", Please select an option you would like to sell or "+isDone+" if you want to exit");
			int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getPotionsList().size()+1);
			scanner.nextLine();
			if(inventoryTypeOption == isDone)
				done =true;
			else {
				this.getHeroInventory().getPotionsList().get(inventoryTypeOption-1).sell(this, inventoryTypeOption-1);
				this.getHeroInventory().getPotionsList().remove(inventoryTypeOption-1);
			}		
		}
	}
	
	//fuction used to sell spells from hero's inventory
	public void sellSpells(Scanner scanner) {
		boolean done = false;
		while(!done) {
			int isDone = this.getHeroInventory().getSpellsList().size()+1;			
			this.getHeroInventory().displaySpellDetails();
			System.out.println(this.getName()+", Please select an option you would like to sell or "+isDone+" if you want to exit");
			int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getSpellsList().size()+1);
			scanner.nextLine();
			if(inventoryTypeOption == isDone)
				done =true;
			else {
				this.getHeroInventory().getSpellsList().get(inventoryTypeOption-1).sell(this, inventoryTypeOption-1);
				this.getHeroInventory().getSpellsList().remove(inventoryTypeOption-1);
			}	
		}
			
	}
	
	////fuction used to sell weapons from hero's inventory
	public void sellWeapons(Scanner scanner) {		
		boolean done = false;
		while(!done) {
			int isDone = this.getHeroInventory().getWeaponsList().size()+1;			
			this.getHeroInventory().displayWeaponsDetails();
			System.out.println(this.getName()+", Please select an option you would like to sell or "+isDone+" if you want to exit");
			int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getWeaponsList().size()+1);
			scanner.nextLine();
			if(inventoryTypeOption == isDone)
				done =true;
			else {
				this.getHeroInventory().getWeaponsList().get(inventoryTypeOption-1).sell(this, inventoryTypeOption-1);	
				this.getHeroInventory().getWeaponsList().remove(inventoryTypeOption-1);
			}
		}
	
	}
	
	////fuction used to sell armors from hero's inventory
	public void sellArmor(Scanner scanner) {
		boolean done = false;
		while(!done) {
			int isDone = this.getHeroInventory().getWeaponsList().size()+1;			
			this.getHeroInventory().displayWeaponsDetails();
			System.out.println(this.getName()+", Please select an option you would like to sell or "+isDone+" if you want to exit");
			int inventoryTypeOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getArmorsList().size());
			scanner.nextLine();
			if(inventoryTypeOption == isDone)
				done =true;
			else {
				this.getHeroInventory().getArmorsList().get(inventoryTypeOption-1).sell(this, inventoryTypeOption-1);
				this.getHeroInventory().getArmorsList().remove(inventoryTypeOption-1);
			}	
		}	
	}
	
	//function called when hero fights with monster applicable for one-one
	public void fightMonster(Monster monster, Scanner scanner) {
		System.out.println(this.getName()+" will fight "+monster.getName());
		while(this.getHealthPower()>0 && monster.getHealthPower()>0) {
			System.out.println("Below are the character statistics: ");
			displayStatistics(monster);
			System.out.println("Choose from the below\n1.Attack\n2.Spell\n3.Potion\n4.Change Weapon\n5.Change Armor");
			int option = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,5);
			scanner.nextLine();
			if(option == 1) {
				if(this.getHeroWeapon()!= null)
					this.getHeroWeapon().hit(this,monster);
				else {
					double damageHeroCauses = (this.getStrength()*0.05) +(monster.getDefence()*0.0001);
					double newHealth = monster.getHealthPower() - damageHeroCauses;
					monster.setHealthPower(newHealth);
					DecimalFormat df = new DecimalFormat("#.##");
					df.format(damageHeroCauses);
					System.out.println(this.getName()+" caused "+damageHeroCauses+" to "+monster.getName());
				}
					
			}
			else if(option == 2) {
				this.getHeroInventory().displaySpellDetails();
				if(this.getHeroInventory().getSpellsList().size()>0) {
					int spellOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getSpellsList().size());
					scanner.nextLine();
					this.getHeroInventory().getSpellsList().get(spellOption-1).castSpell(this,monster);
				}
			}
			else if(option == 3) {
				this.getHeroInventory().displayPotionsDetails();
				if(this.getHeroInventory().getPotionsList().size()>0) {
					int potionOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getPotionsList().size());
					scanner.nextLine();
					this.getHeroInventory().getPotionsList().get(potionOption-1).use(this);
				}
			}
			else if(option == 4) {
				this.getHeroInventory().displayWeaponsDetails();
				if(this.getHeroInventory().getWeaponsList().size()>0) {
					int weaponOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getWeaponsList().size());
					scanner.nextLine();
					this.setHeroWeapon(this.getHeroInventory().getWeaponsList().get(weaponOption-1));
					System.out.println(this.getName()+" can now cause more damage to enemys using "+this.getHeroInventory().getWeaponsList().get(weaponOption-1).getName());
				}
			}
			else if(option == 5) {
				this.getHeroInventory().displayArmorsDetails();
				if(this.getHeroInventory().getArmorsList().size()>0) {
					int armorOption = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,this.getHeroInventory().getArmorsList().size());
					scanner.nextLine();
					this.getHeroInventory().getArmorsList().get(armorOption-1).use(this);
					this.setHeroArmor(this.getHeroInventory().getArmorsList().get(armorOption-1));
					System.out.println(this.getName()+" is protected from monsters by "+this.getHeroInventory().getArmorsList().get(armorOption-1).getName());
				}
			}			
			monster.attack(this);
			this.setHealthPower(this.getHealthPower()+this.getHealthPower()*0.1);
			this.setMana(this.getMana()+this.getMana()*0.1);
			levelUP();
		}
		if(this.getHealthPower()>0) {
			System.out.println(this.getName()+" won!!");
			heroWin(monster);
		}
		else {
			System.out.println(this.getName()+" lost!!");
		}
		
	}
	
	//function used to display character statistics during a battle
	public void displayStatistics(Monster monster) {
		ArrayList<ArrayList<String>> characterStatistics = new ArrayList<ArrayList<String>>();
		DecimalFormat df = new DecimalFormat("#.##");
		ArrayList<String> statistics1 = new ArrayList<String>();
		statistics1.add("Hero: "+this.getName());
		statistics1.add("Monster: "+monster.getName());
		characterStatistics.add(statistics1);
		ArrayList<String> statistics2 = new ArrayList<String>();
		statistics2.add("HP: "+df.format(this.getHealthPower()));
		statistics2.add("HP: "+df.format(monster.getHealthPower()));
		characterStatistics.add(statistics2);
		ArrayList<String> statistics3 = new ArrayList<String>();
		statistics3.add("Level: "+this.getGameLevel());
		statistics3.add("Level: "+monster.getGameLevel());
		characterStatistics.add(statistics3);
		ArrayList<String> statistics4 = new ArrayList<String>();
		statistics4.add("Damage: "+this.getDamage());
		statistics4.add("Damage: "+monster.getDamage());
		characterStatistics.add(statistics4);
		ArrayList<String> statistics5 = new ArrayList<String>();
		statistics5.add("Mana: "+df.format(this.getMana()));
		statistics5.add("Dodge Chance: "+monster.getDodgeChance());
		characterStatistics.add(statistics5);

		GameDesigns.tableWithLines(characterStatistics, false);
	}

	
	public void setDamage() {
		if(this.getHeroWeapon()!= null)
			this.damage = (this.getStrength()+this.getHeroWeapon().getDamageInflict())*0.05;
		else
			this.damage = this.getStrength()*0.05;
	}
	
	public double getDamage() {
		return damage;
	}


	public Weapon getHeroWeapon() {
		return heroWeapon;
	}


	public void setHeroWeapon(Weapon heroWeapon) {
		this.heroWeapon = heroWeapon;
		this.setDamage();
	}


	public Armor getHeroArmor() {
		return heroArmor;
	}


	public void setHeroArmor(Armor heroArmor) {
		this.heroArmor = heroArmor;
	}


	public int getDamageReduction() {
		return damageReduction;
	}


	public void setDamageReduction(int damageReduction) {
		this.damageReduction = damageReduction;
	}

	
	//function used to diaplay hero's inventory on selection
	public void checkInventory(Scanner scanner) {
		boolean isDone = false;
		while(!isDone) {
			System.out.println(this.getName()+", Choose from the below\n1.Weapons\n2.Spells\n3.Potions\n4.Armors\n5.Done");
			int option = GameFunctions.safeScanIntWithLimit(scanner,"Input: ", 1,5);
			scanner.nextLine();
			if(option == 1){
				this.getHeroInventory().displayWeaponsDetails();
				if(this.getHeroInventory().getWeaponsList().size()>0) {
					int weaponOption = GameFunctions.safeScanIntWithLimit(scanner,"Select the weapon you would like to use: ", 1,this.getHeroInventory().getWeaponsList().size());
					scanner.nextLine();
					this.setHeroWeapon(this.getHeroInventory().getWeaponsList().get(weaponOption-1));
					System.out.println(this.getName()+" can now cause more damage to enemys using "+this.getHeroInventory().getWeaponsList().get(weaponOption-1).getName());
				}
			}
			else if(option == 2)
				this.getHeroInventory().displaySpellDetails();
			else if(option == 3) {
				this.getHeroInventory().displayPotionsDetails();
				if(this.getHeroInventory().getPotionsList().size()>0) {
					int potionOption = GameFunctions.safeScanIntWithLimit(scanner,"Select the potion you would like to use: ", 1,this.getHeroInventory().getPotionsList().size());
					scanner.nextLine();
					this.getHeroInventory().getPotionsList().get(potionOption-1).use(this);
				}
			}
			else if(option == 4){
				this.getHeroInventory().displayArmorsDetails();
				if(this.getHeroInventory().getArmorsList().size()>0) {
					int armorOption = GameFunctions.safeScanIntWithLimit(scanner,"Select the armor you would like to use: ", 1,this.getHeroInventory().getArmorsList().size());
					scanner.nextLine();
					this.getHeroInventory().getArmorsList().get(armorOption-1).use(this);
					this.setHeroArmor(this.getHeroInventory().getArmorsList().get(armorOption-1));
					System.out.println(this.getName()+" is protected from monsters by "+this.getHeroInventory().getArmorsList().get(armorOption-1).getName());
				}
			}
			else
				isDone = true;
		}
		
	}
	
	//Function used to chek if hero is eligible for level up and reset the skill and attributes
	public void levelUP() {
		if(this.getExperince() == this.getGameLevel()*10+this.getGameLevel()) {
			this.setMana(this.getMana()*1.1);
			if(this.getHeroType().getHeroTypeName() == "Paladin") {
				this.setDexterity((this.getDexterity()*1.1));
				this.setStrength((this.getStrength()*1.1));
				this.setAgility(this.getAgility()*1.05);				
			}
			if(this.getHeroType().getHeroTypeName() == "Sorcerer") {
				this.setDexterity((this.getDexterity()*1.1));
				this.setStrength((this.getStrength()*1.1));
				this.setAgility(this.getAgility()*1.05);				
			}
			if(this.getHeroType().getHeroTypeName() == "Warrior") {
				this.setDexterity((this.getDexterity()*1.1));
				this.setStrength((this.getStrength()*1.1));
				this.setAgility(this.getAgility()*1.05);				
			}
			System.out.println(this.getName()+" leveled up!!");
			this.displayInfo();
		}
		
	}
	
	//fuctions used to display hero's information about his attributes etc.,
	public void displayInfo() {
		DecimalFormat df = new DecimalFormat("#.##");
		ArrayList<ArrayList<String>> heroDetails = new ArrayList<ArrayList<String>>();
		ArrayList<String> heroDetails1 = new ArrayList<String>();
		heroDetails1.add("Name");
		heroDetails1.add(getName());
		ArrayList<String> heroDetails2 = new ArrayList<String>();
		heroDetails2.add("Level");
		heroDetails2.add(String.valueOf(this.getGameLevel()));
		ArrayList<String> heroDetails3 = new ArrayList<String>();
		heroDetails3.add("HP");
		df.format(this.getHealthPower());
		heroDetails3.add(String.valueOf(this.getHealthPower()));
		ArrayList<String> heroDetails4 = new ArrayList<String>();
		heroDetails4.add("MANA");
		df.format(this.getMana());
		heroDetails4.add(String.valueOf(this.getMana()));
		ArrayList<String> heroDetails5 = new ArrayList<String>();
		heroDetails5.add("Experience");
		df.format(this.getExperince());
		heroDetails5.add(String.valueOf(this.getExperince()));
		ArrayList<String> heroDetails6 = new ArrayList<String>();
		heroDetails6.add("Dexerity");
		heroDetails6.add(String.valueOf(this.getDexterity()));
		ArrayList<String> heroDetails7 = new ArrayList<String>();
		heroDetails7.add("Agiity");
		heroDetails7.add(String.valueOf(this.getAgility()));
		ArrayList<String> heroDetails8 = new ArrayList<String>();
		heroDetails8.add("Strength");
		heroDetails8.add(String.valueOf(this.getStrength()));
		heroDetails.add(heroDetails1);
		heroDetails.add(heroDetails2);
		heroDetails.add(heroDetails3);
		heroDetails.add(heroDetails4);
		heroDetails.add(heroDetails5);
		heroDetails.add(heroDetails6);
		heroDetails.add(heroDetails7);
		heroDetails.add(heroDetails8);
		GameDesigns.tableWithLines(heroDetails, false);		
	}
	
	public void heroPartyDesignTop() {
		System.out.print(GameConstants.ANSI_BLUE+"|_*_ |"+GameConstants.RESET_COLOR);
		
	}
	public void heroPartyDesignBottom() {
		System.out.print(GameConstants.ANSI_BLUE+"| |  |"+GameConstants.RESET_COLOR);
	}
	
	public void heroPartyDesign() {
		heroPartyDesignTop();
		System.out.println();
		heroPartyDesignBottom();
	}
	
	//set hero attributes on win
	public void heroWin(Monster monster) {
		this.setExperince(this.getExperince()+2);
		this.setMoney(this.getMoney()+(monster.getGameLevel()*100));
		this.displayInfo();
	}
	
	//set hero attributes on lose
	public void heroLost() {
		this.setMoney(this.getMoney()/2);		
	}
		
}
