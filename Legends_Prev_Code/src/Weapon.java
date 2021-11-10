import java.text.DecimalFormat;
import java.util.ArrayList;

//created to store weapon object and functions that be perform using or on weapon

public class Weapon extends MarketInventory implements isAttackable{
	private int damageInflict;
	private int handsToUse;

	public int getDamageInflict() {
		return damageInflict;
	}

	public void setDamageInflict(int damageInflict) {
		this.damageInflict = damageInflict;
	}

	public int getHandsToUse() {
		return handsToUse;
	}

	public void setHandsToUse(int handsToUse) {
		this.handsToUse = handsToUse;
	}


	@Override
	public void hit(Hero hero, Monster monster) {
		// TODO Auto-generated method stub
		double damageHeroCaused = (hero.getStrength()+this.getDamageInflict())*0.05;
		double newHealth = monster.getHealthPower() - damageHeroCaused;
		monster.setHealthPower(newHealth);
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(damageHeroCaused);
		//System.out.println("Damage caused by Hero's Weapon: "+this.getDamageInflict());
		System.out.println(hero.getName()+" caused "+damageHeroCaused+" to"+monster.getName());
	}

	public void mapObject(ArrayList<String> weaponsList) {
		// TODO Auto-generated method stub
		this.setName(weaponsList.get(0));
		this.setCost(Float.parseFloat(weaponsList.get(1)));
		this.setRequiredLevel(Integer.parseInt(weaponsList.get(2)));
		this.setDamageInflict(Integer.parseInt(weaponsList.get(3)));
		this.setHandsToUse(Integer.parseInt(weaponsList.get(4)));
		this.setInventoryType(MarketInventoryType.WEAPON);		
	}

	@Override
	public boolean buy(Hero hero) {
		// TODO Auto-generated method stub
		if(hero.getMoney()>this.getCost() && hero.getGameLevel()>=this.getRequiredLevel()) {
			hero.getHeroInventory().getWeaponsList().add(this);
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
		System.out.println("You sold a "+hero.getHeroInventory().getWeaponsList().get(selection).getName());
		return true;
	}
	
	public ArrayList<String> getDetails(){
		ArrayList<String> details = new ArrayList<String>();
		details.add(this.getName());
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(this.getCost());
		details.add(String.valueOf(this.getCost()));
		details.add(String.valueOf(this.getRequiredLevel()));
		details.add(String.valueOf(this.getDamageInflict()));
		details.add(String.valueOf(this.getHandsToUse()));
		return details;
	}

}
