//Class to contain all the types of potions and their respective naming conventions

public enum PotionTypes {
	HEALINGPOTION("Healing_Potion"),
	STRENGTHPOTION("Strength_Potion"),
	MAGICPOTION("Magic_Potion"),
	LUCKELIXIR("Luck_Elixir "),
	MERMAIDTEARS("Mermaid_Tears"),
	AMBROSIA("Ambrosia");
	
	private String potionTypeName;

	PotionTypes(String potionTypeName) {
		// TODO Auto-generated constructor stub
		this.setPotionTypeName(potionTypeName);
	}

	public String getPotionTypeName() {
		return potionTypeName;
	}

	public void setPotionTypeName(String potionTypeName) {
		this.potionTypeName = potionTypeName;
	}

}
