import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Class to build and display the game 
public class LNMGameLayout extends GameLayout{
	
	private int[] gameCellsType1;
	private List<Integer> gameCellsType;
	
	//create the map in this constructor to mske it immutable
	public LNMGameLayout(int position, GameConfig gameConfig) {
		super(position, gameConfig.getGameSize());
		gameCellsType = new ArrayList<Integer>();
		int inacceCells = (int) (Math.round(this.getGameSize()*this.getGameSize())*0.2);
		int marketCells = (int) (Math.round(this.getGameSize()*this.getGameSize())*0.3);
		for(int i=0;i<inacceCells;i++) {
			gameCellsType.add(i, CellType.INACCESSIBLECELL.getCellTypeNumber());
		}
		
		for(int i=inacceCells;i<(inacceCells+marketCells);i++) {
			gameCellsType.add(i, CellType.MARKET.getCellTypeNumber());
		}
		for(int i=(inacceCells+marketCells);i<this.getGameSize()*this.getGameSize();i++) {
			gameCellsType.add(i, CellType.COMMONPLACE.getCellTypeNumber());
		}
		Collections.shuffle(gameCellsType);		
		gameCellsType.set(0, CellType.COMMONPLACE.getCellTypeNumber());
		gameCellsType.set(1, CellType.MARKET.getCellTypeNumber());
	}
	
	public List<Integer> getGameCells() {
		return gameCellsType;
	}
	
	
	public void drawLNMLayout(boolean isMarket, boolean isInventory) {
		drawMap();
		displayMapInfo();
		System.out.println("Below are the controls you could use to navigate");
		displayControlPanel(isMarket,isInventory);	
	}
	
	//Hero list is build and diaplyed since no storing is required for all the heroes
	public ArrayList<ArrayList<String>> displayHeroes(FileToList readFile) {
		ArrayList<ArrayList<String>> paladinsList = new ArrayList<ArrayList<String>>();
		paladinsList = readFile.readFile("Paladins.txt");
		paladinsList.get(0).add("Hero Type");
		for(int i=1; i<paladinsList.size();i++) {
			paladinsList.get(i).add("Paladin");
		}
		ArrayList<ArrayList<String>> sorcerersList = new ArrayList<ArrayList<String>>();
		sorcerersList = readFile.readFile("Sorcerers.txt");
		sorcerersList.remove(0);
		for(int i=0; i<sorcerersList.size();i++) {
			sorcerersList.get(i).add("Sorcerer");
		}
		ArrayList<ArrayList<String>> warriorsList = new ArrayList<ArrayList<String>>();
		warriorsList = readFile.readFile("Warriors.txt");
		warriorsList.remove(0);
		for(int i=0; i<warriorsList.size();i++) {
			warriorsList.get(i).add("Warrior");
		}
		ArrayList<ArrayList<String>> heroesList = new ArrayList<ArrayList<String>>();
		heroesList.addAll(paladinsList);
		heroesList.addAll(sorcerersList);
		heroesList.addAll(warriorsList);
		return heroesList;
	}
	
	//method to create the LNM game maze showing market, inaccessible and common place cells
	public void drawMap() {
		// TODO Auto-generated method stub
		//Create cells using Cell class with number for ease of user
		int cellWidth = 6;
		for(int z=0;z<this.getGameSize();z++){
			for(int i=1;i<=cellWidth*this.getGameSize();i++)
        	{
				System.out.print("-");
        	}
			System.out.println();
			for(int i=1;i<=this.getGameSize();i++)
        	{
				int cellNumber = GameFunctions.ij_to_int(z, i, this.getGameSize())-1;
				if(cellNumber == this.getGameStartPosition()-1) {
					Hero hero = new Hero();
					hero.heroPartyDesignTop();
				}
				else if(gameCellsType.get(cellNumber)== CellType.MARKET.getCellTypeNumber()){					
					Market cell = new Market();
					cell.marketDesignTop();
				}
				else if(gameCellsType.get(cellNumber) == CellType.INACCESSIBLECELL.getCellTypeNumber()){
					InaccessibleCell cell = new InaccessibleCell();
					cell.inaccessibleDesignTop();
				}
				else {
					CommonPlace cell = new CommonPlace();
					cell.commonPlaceDesignTop();
				}
				
        	}
			System.out.println();
			for(int i=1;i<=this.getGameSize();i++)
        	{
				int cellNumber = GameFunctions.ij_to_int(z, i, this.getGameSize())-1;
				if(cellNumber == this.getGameStartPosition()-1) {
					Hero hero = new Hero();
					hero.heroPartyDesignBottom();
				}
				else if(gameCellsType.get(cellNumber) == CellType.MARKET.getCellTypeNumber()){					
					Market cell = new Market();
					cell.marketDesignBottom();
				}
				else if(gameCellsType.get(cellNumber) == CellType.INACCESSIBLECELL.getCellTypeNumber()){
					InaccessibleCell cell = new InaccessibleCell();
					cell.inaccessibleDesignBottom();
				}
				else {
					CommonPlace cell = new CommonPlace();
					cell.commonPlaceDesignBottom();
				}
        	}
			System.out.println();
			
		}
		for(int i=1;i<=cellWidth*this.getGameSize();i++)
    	{
			System.out.print("-");
    	}
    System.out.println();	
	}
	
	//display controls info based on the cell position
	public void displayControlPanel(boolean ismarket, boolean isInventory) {
		ArrayList<ArrayList<String>> controls = new ArrayList<ArrayList<String>>();
		ArrayList<String> controlList2 = new ArrayList<>(Arrays.asList("w: move up", "s: move down"));
		controls.add(0,controlList2);
		ArrayList<String> controlList3 = new ArrayList<>(Arrays.asList("a: move left", "d: move right"));
		controls.add(1,controlList3);
		ArrayList<String> controlList4 = new ArrayList<>(Arrays.asList("q: quit game", "i: show hero info"));
		controls.add(2,controlList4);
		ArrayList<String> controlList5 = new ArrayList<>(Arrays.asList("m: Enter market", "h: Show Inventory"));
		controls.add(3,controlList5);
		if(ismarket) {
			controls.get(3).set(1, " ");
		}
		else if(isInventory) {
			controls.get(3).set(0, " ");
		}

		GameDesigns.tableWithLines(controls, false);	
	}
	
	//display the map info to understand the cells
	public void displayMapInfo() {
		Market market = new Market();
		market.cellDesign();
		System.out.println(" - Market");
		System.out.println();
		InaccessibleCell cell = new InaccessibleCell();
		cell.cellDesign();
		System.out.println(" - Inaccessible Place");
		System.out.println();
		CommonPlace place  = new CommonPlace();
		place.cellDesign();
		System.out.println(" - Common Place");
		System.out.println();
		Hero hero = new Hero();
		hero.heroPartyDesign();
		System.out.println(" - HeroParty");
		System.out.println();
	}
	
		

	@Override
	public void resetLayout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawLayout() {
		// TODO Auto-generated method stub
		
	}

}
