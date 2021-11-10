//Market inventory used to maintain the inventory of all the objects available for buy/sell

public abstract class MarketInventory implements isBuySellable{
	private String name;
	private float cost;
	private int requiredLevel;
	private MarketInventoryType inventoryType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float price) {
		this.cost = price;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(int minHeroLevel) {
		this.requiredLevel = minHeroLevel;
	}

	public MarketInventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(MarketInventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}


}
