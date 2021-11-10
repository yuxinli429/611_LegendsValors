//Types of buyable/sellable products available

public enum MarketInventoryType {
	
	POTION("Potion"),
	ICESPELL("Ice Spell"),
	FIRESPELL("Fire Spell"),
	LIGTHNINGSPELL("Lightning Spell"),
	ARMOR("Armor"),
	WEAPON("Weapon");
	
	private String inventoryTypeName;

	MarketInventoryType(String inventoryTypeName) {
		// TODO Auto-generated constructor stub
		this.setInventoryTypeName(inventoryTypeName);
	}

	public String getInventoryTypeName() {
		return inventoryTypeName;
	}

	public void setInventoryTypeName(String inventoryTypeName) {
		this.inventoryTypeName = inventoryTypeName;
	}
	

}
