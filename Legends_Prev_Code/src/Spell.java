import java.text.DecimalFormat;
import java.util.ArrayList;

//Spell class function that can be done on or using spell like cast, buy from interfaces. Also has mapper functio to create spell object
public class Spell extends MarketInventory implements isCastable{
	private int damageRange;
	private float manaRequired;
	private int damageInflicted;
	private SpellTypes spellType;
	

	public int getDamageRange() {
		return damageRange;
	}

	public void setDamageRange(int damageRange) {
		this.damageRange = damageRange;
	}

	public float getManaRequired() {
		return manaRequired;
	}

	public void setManaRequired(float manaRequired) {
		this.manaRequired = manaRequired;
	}

	public int getDamageInflicted() {
		return damageInflicted;
	}

	public void setDamageInflicted(int damageInflicted) {
		this.damageInflicted = damageInflicted;
	}


	@Override
	public void castSpell(Hero hero,Monster monster) {
		// TODO Auto-generated method stub
		if(hero.getMana()>=this.getManaRequired()) {
			double damage = this.getDamageInflicted()+(hero.getDexterity()/10000)*this.getDamageInflicted();
			double newHealth =monster.getHealthPower()- damage;
			monster.setHealthPower(newHealth);
			DecimalFormat df = new DecimalFormat("#.##");
			df.format(damage);
			System.out.println(hero.getName()+" caused "+damage+" to "+monster.getName());
			double manaRem = hero.getMana()-this.getManaRequired();
			hero.setMana(manaRem);
			for(int i=0;i<hero.getHeroInventory().getSpellsList().size();i++) {
				if(this.getName().equalsIgnoreCase(hero.getHeroInventory().getSpellsList().get(i).getName()))
					hero.getHeroInventory().getSpellsList().remove(i);
			}
		}
		else
			System.out.println("No suuficient mana");
	}

	public SpellTypes getSpellType() {
		return spellType;
	}

	public void setSpellType(SpellTypes spellType) {
		this.spellType = spellType;
	}

	public void mapObject(ArrayList<String> spellList, SpellTypes spellType) {
		// TODO Auto-generated method stub
		this.setName(spellList.get(0));
		this.setCost(Float.parseFloat(spellList.get(1)));
		this.setRequiredLevel(Integer.parseInt(spellList.get(2)));
		this.setDamageInflicted(Integer.parseInt(spellList.get(3)));
		this.setManaRequired(Float.parseFloat(spellList.get(4)));		
	}
	
	@Override
	public boolean buy(Hero hero) {
		// TODO Auto-generated method stub
		if(hero.getMoney()>this.getCost() && hero.getGameLevel()>=this.getRequiredLevel()) {
			hero.getHeroInventory().getSpellsList().add(this);
			float money = hero.getMoney() - this.getCost();
			hero.setMoney(money);
			return true;
		}
		else
			return false;
		
	}

	@Override
	public boolean sell(Hero hero, int selection) {
		// TODO Auto-generated method stub
		float money = hero.getMoney() + (this.getCost()/2);
		hero.setMoney(money);
		System.out.println("You sold a "+hero.getHeroInventory().getSpellsList().get(selection).getName());
		return true;
	}
	
	public ArrayList<String> getDetails(){
		ArrayList<String> details = new ArrayList<String>();
		details.add(this.getName());
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(this.getCost());
		details.add(String.valueOf(this.getCost()));
		details.add(String.valueOf(this.getRequiredLevel()));
		details.add(String.valueOf(this.getDamageInflicted()));
		df.format(this.getManaRequired());
		details.add(String.valueOf(this.getManaRequired()));
		return details;
	}


}
