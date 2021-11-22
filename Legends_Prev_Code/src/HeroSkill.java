//Enum to save the list of all Hero's special skills like Dexterity, Agility and Strength

public enum HeroSkill {
	
	DEXTERITY("Dexterity"),
	AGILITY("Agility"),
	STRENGTH("Strength");
	
	private String heroSkillName;

	HeroSkill(String heroSkillName) {
		// TODO Auto-generated constructor stub
		this.setHeroSkillName(heroSkillName);
	}

	public String getHeroSkillName() {
		return heroSkillName;
	}

	public void setHeroSkillName(String heroSkillsName) {
		this.heroSkillName = heroSkillsName;
	}
}
