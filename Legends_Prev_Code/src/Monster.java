import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//child class to game character class and contains mapper, attributes and functions available for monster like attack
public class Monster extends GameCharacter{
	
	private MonsterType monsterType;
	private double damage;
	private int defence;
	private int dodgeChance;
	//private int position;
	private boolean hasWon;

	public Monster(){
		this.damage = 0;
		this.defence = 0;
		this.dodgeChance = 0;
		this.setHasWon(false);
	}
	
	
	public MonsterType getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(MonsterType monsterType) {
		this.monsterType = monsterType;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double d) {
		this.damage = d;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(int dodgeChance) {
		this.dodgeChance = dodgeChance;
	}

	//mapper function for the Dragon class
	public void mapObject(ArrayList<String> monsterList) {
		this.setName(monsterList.get(0));
		this.setGameLevel(Integer.parseInt(monsterList.get(1)));
		this.setDamage(Integer.parseInt(monsterList.get(2)));
		this.setDefence(Integer.parseInt(monsterList.get(3)));
		this.setDodgeChance(Integer.parseInt(monsterList.get(4)));
		this.setMonsterType(monsterType);
		this.setHealthPower(Integer.parseInt(monsterList.get(1))*100);
	}		
	
	//function to facilitate monsters attack
	public void attack(Hero hero) {
		double damagecaused = (this.getDamage()*0.02)-hero.getDamageReduction();
		double newHealth = hero.getHealthPower()-damagecaused;
		hero.setHealthPower(newHealth);
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(damagecaused);
		System.out.println(this.getName()+" caused "+damagecaused+" to"+hero.getName());
	}
	//check monster.hasWon every time after move monster
	public void MoveMonster(LNMGameLayout lnmgameLayout){
		int next = 0;
		while(next == 0){
			next = MakeMove(lnmgameLayout);
		}
	}
	//Function to allow monster to move in this turn
	public int MakeMove(LNMGameLayout lnmgameLayout){
		int current = this.getCharacterPosition();
		int next = current;
		int length = lnmgameLayout.getGameSize();
		next = current + length;
		if(next<length*length && next>0 && CheckMonsterMove(next,lnmgameLayout) == true){
			return next;
		}
		else return 0;
		
	}
	
	//function to validate the move made
	public boolean CheckMonsterMove(int position, LNMGameLayout lnmgameLayout) {
		List<Integer> gameCells = lnmgameLayout.getGameCells();
		if (gameCells.get(position - 1) == CellType.INACCESSIBLECELL.getCellTypeNumber()) {
			//if the cell is inaccessible, go over again
			return false;
		}
		else if(gameCells.get(position - 1) == CellType.HERONEXUS.getCellTypeNumber()){
			this.setHasWon(true);
			this.setCharacterPosition(position);
			System.out.println(this.getCharacterSymbol()+", "+this.getName()+" reached Hero Nexus and won!!");
			System.out.println("Heroes lost");
			System.out.println("Thankyou for playing!!");
			System.exit(0);
			return true;
		}
		else{
			this.setCharacterPosition(position);
			return true;
		}
	}


	public boolean isHasWon() {
		return hasWon;
	}


	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

}
