//all types are heroes are enum 

public enum HeroType {
	
	PALADINS("Paladin"),
	SORCERERS("Sorcerer"),
	WARRIORS("Warrior");
	
	private String heroTypeName;

	HeroType(String heroTypeName) {
		// TODO Auto-generated constructor stub
		this.setHeroTypeName(heroTypeName);
	}

	public String getHeroTypeName() {
		return heroTypeName;
	}

	public void setHeroTypeName(String heroTypeName) {
		this.heroTypeName = heroTypeName;
	}
	

}
