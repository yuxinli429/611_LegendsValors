import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Implements use, buy, sell methods from interfaces. Also has list function to show details.


public class Potion extends MarketInventory implements isUsable{
	
	private int attributIncrease;
	private List<String>attributeAffected;
	private String attributeAffectedText;


	//Function called when hero uses a potion 
	@Override
	public void use(Hero hero) {
		// TODO Auto-generated method stub
		if(this.getName().equalsIgnoreCase(PotionTypes.HEALINGPOTION.getPotionTypeName()))
			hero.setHealthPower(hero.getHealthPower()+this.attributIncrease);
		else if(this.getName().equalsIgnoreCase(PotionTypes.STRENGTHPOTION.getPotionTypeName()))
			hero.setStrength(hero.getStrength()+this.attributIncrease);
		else if(this.getName().equalsIgnoreCase(PotionTypes.MAGICPOTION.getPotionTypeName()))
			hero.setMana(hero.getMana()+this.attributIncrease);
		else if(this.getName().equalsIgnoreCase(PotionTypes.LUCKELIXIR.getPotionTypeName()))
			hero.setAgility(hero.getAgility()+this.attributIncrease);
		else if(this.getName().equalsIgnoreCase(PotionTypes.MERMAIDTEARS.getPotionTypeName())) {
			hero.setHealthPower(hero.getHealthPower()+this.attributIncrease);
			hero.setStrength(hero.getStrength()+this.attributIncrease);
			hero.setMana(hero.getMana()+this.attributIncrease);
			hero.setAgility(hero.getAgility()+this.attributIncrease);
		}
		else if(this.getName().equalsIgnoreCase(PotionTypes.AMBROSIA.getPotionTypeName())) {
			hero.setHealthPower(hero.getHealthPower()+this.attributIncrease);
			hero.setStrength(hero.getStrength()+this.attributIncrease);
			hero.setMana(hero.getMana()+this.attributIncrease);
			hero.setAgility(hero.getAgility()+this.attributIncrease);
			hero.setDexterity(hero.getDexterity()+this.attributIncrease);
		}
		for(int i=0;i<hero.getHeroInventory().getPotionsList().size();i++) {
			if(this.getName().equalsIgnoreCase(hero.getHeroInventory().getPotionsList().get(i).getName()))
				hero.getHeroInventory().getPotionsList().remove(i);
		}
		
		System.out.println(hero.getName()+" consumed "+this.getName());
		hero.displayInfo();
		
	}
	
	//mapper function to create objects of the class
	public void mapObject(ArrayList<String> potionDetails) {
		// TODO Auto-generated method stub
		this.setName(potionDetails.get(0));
		this.setCost(Float.parseFloat(potionDetails.get(1)));
		this.setRequiredLevel(Integer.parseInt(potionDetails.get(2)));
		this.setAttributIncrease(Integer.parseInt(potionDetails.get(3)));
		this.setAttributeAffected(potionDetails.get(4));
		this.setAttributeAffectedText(potionDetails.get(4));
	}

	public int getAttributIncrease() {
		return attributIncrease;
	}

	public void setAttributIncrease(int attributIncrease) {
		this.attributIncrease = attributIncrease;
	}

	public List<String> getAttributeAffected() {
		return attributeAffected;
	}
	
	//Function to split the attributes afftected into list to be used later
	public void setAttributeAffected(String attributeAffected) {
		String[] attributes = attributeAffected.split("/");
		this.attributeAffected = new ArrayList<String>();
		for(int i=0; i<attributes.length;i++) {
			this.attributeAffected.add(attributes[i]);
		}

	}

	public String getAttributeAffectedText() {
		return attributeAffectedText;
	}

	public void setAttributeAffectedText(String attributeAffectedText) {
		this.attributeAffectedText = attributeAffectedText.replace('/', ',');
	}
	
	@Override
	public boolean buy(Hero hero) {
		// TODO Auto-generated method stub
		if(hero.getMoney()>this.getCost() && hero.getGameLevel()>=this.getRequiredLevel()) {
			hero.getHeroInventory().getPotionsList().add(this);
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
		System.out.println("You sold a "+hero.getHeroInventory().getPotionsList().get(selection).getName());
		return true;
	}

	public ArrayList<String> getDetails(){
		ArrayList<String> details = new ArrayList<String>();
		details.add(this.getName());
		DecimalFormat df = new DecimalFormat("#.##");
		df.format(this.getCost());
		details.add(String.valueOf(this.getCost()));
		details.add(String.valueOf(this.getRequiredLevel()));
		details.add(String.valueOf(this.getAttributIncrease()));
		details.add(this.getAttributeAffectedText());
		return details;
	}

}
