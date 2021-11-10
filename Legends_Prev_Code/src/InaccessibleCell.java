import java.util.Scanner;

//Class to create a inaccessible cell

public class InaccessibleCell extends Cell{

	@Override
	public void cellDesign() {
		// TODO Auto-generated method stub
		inaccessibleDesignTop();
		System.out.println();
		inaccessibleDesignBottom();
		
	}
	
	//function to restrict movement to this cell
	@Override
	public void moveToCell(Scanner scanner,LNMGameLayout lnmgameLayout) {
		// TODO Auto-generated method stub
		System.out.println("Move not allowed. Please go to a different place.");
		
	}
	public void inaccessibleDesignTop() {
		System.out.print(GameConstants.ANSI_RED+"|xxxx|"+GameConstants.RESET_COLOR);
		
	}
	public void inaccessibleDesignBottom() {
		System.out.print(GameConstants.ANSI_RED+"|xxxx|"+GameConstants.RESET_COLOR);
	}

}
