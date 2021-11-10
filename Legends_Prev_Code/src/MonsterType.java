//all the monster types will be categorized here

public enum MonsterType {
	
	SPIRITS("Spirit"),
	DRAGONS("Dragon"),
	EXOSKELETONS("Exoskeleton");
	
	private String monterTypeName;

	MonsterType(String monterTypeName) {
		// TODO Auto-generated constructor stub
		this.setMonterTypeName(monterTypeName);
	}

	public String getMonterTypeName() {
		return monterTypeName;
	}

	public void setMonterTypeName(String monterTypeName) {
		this.monterTypeName = monterTypeName;
	}

}
