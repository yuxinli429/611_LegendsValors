import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Armor class has mapper function to create armor object called from armors class. Implements the use, sell, buy and displayaslist details methods
public class Armor extends MarketInventory implements isUsable{
	private int damageProtection;

	public int getDamageProtection() {
		return damageProtection;
	}

	public void setDamageProtection(int damageProtection) {
		this.damageProtection = damageProtection;
	}



	@Override
	public void use(Hero hero) {
		// TODO Auto-generated method stub
		hero.setDamageReduction(this.getDamageProtection());
	}
	
	//Object mapper used to create object instance using a list prepared from file
	public void mapObject(ArrayList<String> armorDetails) {
		// TODO Auto-generated method stub
		this.setName(armorDetails.get(0));
		this.setCost(Float.parseFloat(armorDetails.get(1)));
		this.setRequiredLevel(Integer.parseInt(armorDetails.get(2)));
		this.setDamageProtection(Integer.parseInt(armorDetails.get(3)));
		this.setInventoryType(MarketInventoryType.ARMOR);
	}
	
	//interface methods implemented to buy
	@Override
	public boolean buy(Hero hero) {
		// TODO Auto-generated method stub
		if(hero.getMoney()>this.getCost() && hero.getGameLevel()>=this.getRequiredLevel()) {
			hero.getHeroInventory().getArmorsList().add(this);
			float money = hero.getMoney() - this.getCost();
			hero.setMoney(money);
			return true;
		}
		else
			return false;
		
	}
	
	//interface method implemented to sell
	@Override
	public boolean sell(Hero hero, int selection) {
		// TODO Auto-generated method stub
		float money = hero.getMoney() + (this.getCost()/2);
		hero.setMoney(money);
		System.out.println("You sold a "+hero.getHeroInventory().getArmorsList().get(selection).getName());
		return true;
	}
	
	//gives the details of armor object as a list
	public ArrayList<String> getDetails(){
		ArrayList<String> details = new ArrayList<String>();
		details.add(this.getName());
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(this.getCost());
		details.add(String.valueOf(this.getCost()));
		details.add(String.valueOf(this.getRequiredLevel()));
		details.add(String.valueOf(this.getDamageProtection()));
		return details;
	}

}
