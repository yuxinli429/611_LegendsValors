import java.util.Scanner;

//super class used to set the celltype using enum class
public abstract class Cell {
	private CellType cellType;

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	
	public abstract void cellDesign();
	
	public abstract void moveToCell(Scanner scanner,LNMGameLayout lnmgameLayout);


}
