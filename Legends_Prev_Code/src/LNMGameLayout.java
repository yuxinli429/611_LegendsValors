import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Class to build and display the game 
public class LNMGameLayout extends GameLayout{
	
	//private int[] gameCellsType1;
	private List<Integer> gameCellsType;
	private List<Integer> heroNexus;
	private List<Integer> monsterNexus;
	
	//create the map in this constructor to make it immutable
	public LNMGameLayout(GameConfig gameConfig) {
		super(gameConfig.getGameSize());
		gameCellsType = new ArrayList<Integer>();
		int plainCells = (int) (Math.round(this.getGameSize()*this.getGameSize())*0.4);
		int bushCells = (int) (Math.round(this.getGameSize()*this.getGameSize())*0.2);
		int caveCells = (int) (Math.round(this.getGameSize()*this.getGameSize())*0.2);

		
		for(int i=0;i<plainCells;i++) {
			gameCellsType.add(i, CellType.PLAIN.getCellTypeNumber());
		}
		
		for(int i=plainCells;i<(plainCells+bushCells);i++) {
			gameCellsType.add(i, CellType.BUSH.getCellTypeNumber());
		}
		for(int i=(plainCells+bushCells);i<(plainCells+bushCells+caveCells);i++) {
			gameCellsType.add(i, CellType.CAVE.getCellTypeNumber());
		}
		
		for(int i=(plainCells+bushCells+caveCells);i<(this.getGameSize()*this.getGameSize());i++) {
			gameCellsType.add(i, CellType.KOULOU.getCellTypeNumber());
		}
		Collections.shuffle(gameCellsType);	
		monsterNexus = new ArrayList<Integer>();
		for(int i=0;i<this.getGameSize();i++) {
			gameCellsType.set(i, CellType.MONSTERNEXUS.getCellTypeNumber());
			monsterNexus.add(i);
		}
		gameConfig.setMonsterNexusList(monsterNexus);
		heroNexus = new ArrayList<Integer>();
		for(int i=(this.getGameSize()*this.getGameSize())-this.getGameSize();i<this.getGameSize()*this.getGameSize();i++) {
			gameCellsType.set(i, CellType.HERONEXUS.getCellTypeNumber());
			heroNexus.add(i);
		}
		gameConfig.setHeroNexusList(heroNexus);
		for(int i=2;i<this.getGameSize()*this.getGameSize();i=i+this.getGameSize()) {
			gameCellsType.set(i, CellType.INACCESSIBLECELL.getCellTypeNumber());
			gameCellsType.set(i+3, CellType.INACCESSIBLECELL.getCellTypeNumber());
		}
	}
	
	public List<Integer> getGameCells() {
		return gameCellsType;
	}
	
	public List<Integer> heroNexusLoc() {
		return heroNexus;
	}
	
	public List<Integer> monsterNexusLoc() {
		return monsterNexus;
	}
	
	
	public void drawLNMLayout(boolean isMarket, boolean isInventory,List<Hero> heroesList, List<Monster>  monsterList) {
		drawLayout(heroesList,monsterList);
		//displayMapInfo();
		System.out.println("Below are the controls you could use to navigate");
		displayControlPanel(isMarket,isInventory);	
	}
	
	
	//method to create the LNM game maze showing market, inaccessible and common place cells
	@Override
	public void drawLayout(List<Hero> heroesList, List<Monster>  monsterList) {
		// TODO Auto-generated method stub
		//Create cells using Cell class with number for ease of user
		int cellNum1=0,cellNum2=0,cellNum3 =0;
		List<Integer> herosLocation= new ArrayList<Integer>();
		for(Hero hero:heroesList) {
			herosLocation.add(hero.getCharacterPosition());			
		}
		List<Integer> monstersLocation= new ArrayList<Integer>();
		for(Monster monster:monsterList) {
			monstersLocation.add(monster.getCharacterPosition());			
		}
		for(int i=0; i<this.getGameSize();i++) {
			for(int j=0; j<this.getGameSize();j++) {
				cellTop(gameCellsType.get(cellNum1));
				cellNum1++;
			}
			System.out.println();
			for(int j=0; j<this.getGameSize();j++) {
				if(gameCellsType.get(cellNum2)==3)
					cellMid(String.valueOf(cellNum2+1),true);
				else {
					if(herosLocation.contains(cellNum2+1) == true && monstersLocation.contains(cellNum2+1)== true) {
						String value = heroesList.get(herosLocation.indexOf(cellNum2+1)).getCharacterSymbol().concat(monsterList.get(monstersLocation.indexOf(cellNum2+1)).getCharacterSymbol());
						cellMid(value,false);
					}
					else if(herosLocation.contains(cellNum2+1) == true && monstersLocation.contains(cellNum2+1)== false){
						String value = heroesList.get(herosLocation.indexOf(cellNum2+1)).getCharacterSymbol();						
						cellMid(value,false);
					}
					else if(herosLocation.contains(cellNum2+1) == false && monstersLocation.contains(cellNum2+1)== true){
						String value = monsterList.get(monstersLocation.indexOf(cellNum2+1)).getCharacterSymbol();						
						cellMid(value,false);
					}
					else
						cellMid(String.valueOf(cellNum2+1),false);
				}
				cellNum2++;
			}
			System.out.println();
			for(int j=0; j<this.getGameSize();j++) {
				cellTop(gameCellsType.get(cellNum3));
				cellNum3++;
			}
			System.out.println();
			System.out.println();
		}		
	}
	
	public void cellTop(int cellTypeNumber) {
		if(cellTypeNumber == CellType.HERONEXUS.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_GREEN+CellType.HERONEXUS.getCellTypeDesign()+" - "+CellType.HERONEXUS.getCellTypeDesign()+" - "+CellType.HERONEXUS.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.MONSTERNEXUS.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_GREEN+CellType.MONSTERNEXUS.getCellTypeDesign()+" - "+CellType.MONSTERNEXUS.getCellTypeDesign()+" - "+CellType.MONSTERNEXUS.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.INACCESSIBLECELL.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_RED+CellType.INACCESSIBLECELL.getCellTypeDesign()+" - "+CellType.INACCESSIBLECELL.getCellTypeDesign()+" - "+CellType.INACCESSIBLECELL.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.PLAIN.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_BLUE+CellType.PLAIN.getCellTypeDesign()+" - "+CellType.PLAIN.getCellTypeDesign()+" - "+CellType.PLAIN.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.BUSH.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_BLUE+CellType.BUSH.getCellTypeDesign()+" - "+CellType.BUSH.getCellTypeDesign()+" - "+CellType.BUSH.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.CAVE.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_BLUE+CellType.CAVE.getCellTypeDesign()+" - "+CellType.CAVE.getCellTypeDesign()+" - "+CellType.CAVE.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);
		else if(cellTypeNumber == CellType.KOULOU.getCellTypeNumber())
			System.out.print(GameConstants.ANSI_BLUE+CellType.KOULOU.getCellTypeDesign()+" - "+CellType.KOULOU.getCellTypeDesign()+" - "+CellType.KOULOU.getCellTypeDesign()+"  "+GameConstants.RESET_COLOR);		
	}
	
	public void cellMid(String cellvalue, boolean isInaccessibleCell) {
		if(isInaccessibleCell)
			System.out.print("| "+"X X X"+" |  ");
		else {
			System.out.print("|  "+cellvalue);
			int currLength = ("  "+cellvalue).length();
			while(currLength<7) {
				System.out.print(" ");
				currLength++;
			}
			System.out.print("|  ");
		}
		
	} 
	
	//display controls info based on the cell position
	public void displayControlPanel(boolean ismarket, boolean isInventory) {
		ArrayList<ArrayList<String>> controls = new ArrayList<ArrayList<String>>();
		ArrayList<String> controlList2 = new ArrayList<>(Arrays.asList("w: move up", "s: move down"));
		controls.add(0,controlList2);
		ArrayList<String> controlList3 = new ArrayList<>(Arrays.asList("a: move left", "d: move right"));
		controls.add(1,controlList3);
		ArrayList<String> controlList4 = new ArrayList<>(Arrays.asList("t: teleport", "b: back"));
		controls.add(2,controlList4);
		ArrayList<String> controlList5 = new ArrayList<>(Arrays.asList("q: quit game", "i: show hero info"));
		controls.add(3,controlList5);
		ArrayList<String> controlList6 = new ArrayList<>(Arrays.asList("m: Enter nexus", "h: Show Inventory"));
		controls.add(4,controlList6);
		if(ismarket) {
			controls.get(4).set(1, " ");
			controls.get(4).set(0, " ");
		}
		else if(isInventory) {
			controls.get(4).set(0, " ");
		}

		GameDesigns.tableWithLines(controls, false);	
	}
	
		

	@Override
	public void resetLayout() {
		// TODO Auto-generated method stub
		
	}

}
