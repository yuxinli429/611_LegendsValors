//contains a list of all the available cells in the game

public enum CellType {
	
	//MARKET("HeroNexus",1),
	HERONEXUS("N",1),
	MONSTERNEXUS("N",2),
	INACCESSIBLECELL("I",3),
	//COMMONPLACE("CommonPlace",4),
	PLAIN("P",4),
	BUSH("B",5),
	CAVE("C",6),
	KOULOU("K",7);
	
	private String cellTypeDesign;
	private int cellTypeNumber;
	
	CellType(String cellTypeDesign, int cellTypeNumber) {
		this.setCellTypeDesign(cellTypeDesign);
		this.setCellTypeNumber(cellTypeNumber);
	}

	public String getCellTypeDesign() {
		return cellTypeDesign;
	}

	public void setCellTypeDesign(String cellTypeDesign) {
		this.cellTypeDesign = cellTypeDesign;
	}

	public int getCellTypeNumber() {
		return cellTypeNumber;
	}

	public void setCellTypeNumber(int cellTypeNumber) {
		this.cellTypeNumber = cellTypeNumber;
	}
	
}
