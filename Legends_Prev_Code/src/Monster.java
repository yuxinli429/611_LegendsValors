import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//child class to game character class and contains mapper, attributes and functions available for monster like attack
public class Monster extends GameCharacter{
	
	private MonsterType monsterType;
	private double damage;
	private int defence;
	private int dodgeChance;
	
	
	
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
	public void mapObject(ArrayList<String> monsterList, MonsterType monsterType) {
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

}
