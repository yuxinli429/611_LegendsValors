//contains a list of all the available cells in the game

public enum CellType {
	
	MARKET("Market",1),
	INACCESSIBLECELL("InaccesssibleCell",2),
	COMMONPLACE("CommonPlace",3);
	
	private String cellTypeClass;
	private int cellTypeNumber;
	
	CellType(String cellTypeClass, int cellTypeNumber) {
		this.setCellTypeClass(cellTypeClass);
		this.setCellTypeNumber(cellTypeNumber);
	}

	public String getCellTypeClass() {
		return cellTypeClass;
	}

	public void setCellTypeClass(String cellTypeClass) {
		this.cellTypeClass = cellTypeClass;
	}

	public int getCellTypeNumber() {
		return cellTypeNumber;
	}

	public void setCellTypeNumber(int cellTypeNumber) {
		this.cellTypeNumber = cellTypeNumber;
	}
	
}
