//contains the types of spells available

public enum SpellTypes {
	
	ICESPELL("Ice Spell"),
	FIRESPELL("Fire Spell"),
	LIGHTNINGSPELL("Lightning Spell");
	
	private String spellTypeName;

	SpellTypes(String spellTypeName) {
		// TODO Auto-generated constructor stub
		this.setSpellTypeName(spellTypeName);
	}

	public String getSpellTypeName() {
		return spellTypeName;
	}

	public void setSpellTypeName(String spellTypeName) {
		this.spellTypeName = spellTypeName;
	}
}
