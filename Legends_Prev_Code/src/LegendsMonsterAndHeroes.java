import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//LegendsMonsterAndHeroes class has the logic for actual game start
public class LegendsMonsterAndHeroes extends RolePlayGame{	

	private List<Hero> gameHeroes;
	private List<Monster> gameMonsters;


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Legends: Monster And Heroes";
	}
	

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		System.out.println();
		GameDesigns.displayMonsterDesign();
		System.out.println();
		System.out.println("Let's play "+getName()+"!!");
		Scanner scanner = new Scanner(System.in);
		FileToList filereader = new FileToList();
		LNMGameLayout lnmgameLayout = initializegame(scanner);
		System.out.println("Welcome to the village!!");
		//play till the game is end or ended by user
		while(true) {
			lnmgameLayout.drawLNMLayout(false,true);
			boolean isValid = false;
			int nextPosition = 0;
			do {
				//check for the input from user to move around the board
				String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
				nextPosition = moveHeroParty(lnmgameLayout,input,scanner);
				if(nextPosition == 0)
					System.out.println("Invalid move");
				else
					isValid = true;
			}while((!isValid));
			Market market = new Market(filereader);
			//once the heroparty move is valid and moved, check for the type of the cell
			checkCell(scanner,lnmgameLayout, market, nextPosition);
		}
			
	}
	
	//function to check for user inputs and validity of movement in the map
	public int moveHeroParty(LNMGameLayout lnmgameLayout, String nextPosition, Scanner scanner) {
		int currentPosition = lnmgameLayout.getGameStartPosition();
		int nextPostn = currentPosition;
		if(GameConstants.LNM_UP_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition-this.GameConfig.getGameSize();
		}
		else if(GameConstants.LNM_DOWN_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition+this.GameConfig.getGameSize();
		}
		else if(GameConstants.LNM_RIGHT_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition+1;
		}
		else if(GameConstants.LNM_LEFT_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition-1;
		}
		else if(GameConstants.LNM_MARKET_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = -20;
		}
		else if(GameConstants.LNM_INFO_KEY.equalsIgnoreCase(nextPosition)) {
			for(Hero hero:gameHeroes) {
				hero.displayInfo();
			}
			//return currentPosition;
		}
		else if(GameConstants.LNM_QUIT_KEY.equalsIgnoreCase(nextPosition)) {
			System.out.println("You chose to quit. Thankyou for playing!!");
			System.exit(0);
		}
		else if(GameConstants.LNM_INVENTORY_KEY.equalsIgnoreCase(nextPosition)) {
			for(Hero hero:gameHeroes) {
				hero.checkInventory(scanner);
			}
		}		
		if((nextPostn>0 && nextPostn<=(this.GameConfig.getGameSize()*this.GameConfig.getGameSize())) || nextPostn == -20)
			return nextPostn;
		else
			return 0;		
	}
	
	public void checkCell(Scanner scanner,LNMGameLayout lnmgameLayout,Market market,int nextPosition) {
		List<Integer> gameCells = lnmgameLayout.getGameCells();
		//if cell is market ask user if he would like to enter or continue game
		if(gameCells.get(nextPosition-1)== CellType.MARKET.getCellTypeNumber()) {
			lnmgameLayout.setGameStartPosition(nextPosition);
			System.out.println("You are in the market place!!");
			lnmgameLayout.drawLNMLayout(true,false);
			String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
			nextPosition = moveHeroParty(lnmgameLayout,input,scanner);
			if(nextPosition == -20) {
				int marketOption = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the number you would like to perform at the maket:\n1.Buy\n2.Sell\nInput: ", 1,2);
				scanner.nextLine();
				if(marketOption == 1) {
					market.sellInventory(gameHeroes, scanner);
				}
				else {
					for(int i=0;i<gameHeroes.size();i++) {
						gameHeroes.get(i).sellHeroInventory(scanner);
					}
				}				
			}
			else
				checkCell(scanner,lnmgameLayout, market, nextPosition);
		}
		//Check for inaccessible cell
		else if(gameCells.get(nextPosition-1)== CellType.INACCESSIBLECELL.getCellTypeNumber()) {
			InaccessibleCell cell = new InaccessibleCell();
			cell.moveToCell(scanner,lnmgameLayout);
			lnmgameLayout.drawLNMLayout(false,true);
			String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
			nextPosition = moveHeroParty(lnmgameLayout,input,scanner);
			checkCell(scanner,lnmgameLayout, market, nextPosition);			
		}
		//check for common place. All the game logic is inside class since monsters are not created at the beginning
		else if(gameCells.get(nextPosition-1)== CellType.COMMONPLACE.getCellTypeNumber()) {
			lnmgameLayout.setGameStartPosition(nextPosition);
			CommonPlace cell = new CommonPlace(gameMonsters,gameHeroes);
			cell.moveToCell(scanner,lnmgameLayout);
		}
	}
	
	
	//function to initialize the game with 
	public LNMGameLayout initializegame(Scanner scanner) {
		gameHeroes = new ArrayList<>();
		gameMonsters = new ArrayList<>();
		this.GameConfig.setTeamSize(GameFunctions.safeScanIntWithLimit(scanner,"Please enter the number of heroes you would like to play with: ", GameConstants.LNM_TEAM_COUNT_MIN,GameConstants.LNM_TEAM_COUNT_MAX));
        scanner.nextLine();
        this.GameConfig.setGameSize(GameConstants.LNM_LAYOUT_SIZE);
        FileToList readFile = new FileToList();//Parser created and used for all classes
        LNMGameLayout lnmgameLayout = new LNMGameLayout(1,GameConfig);//Game layout created at the beginning of the game
        ArrayList<ArrayList<String>> heroesList = new ArrayList<ArrayList<String>>();
        heroesList = lnmgameLayout.displayHeroes(readFile);
        GameDesigns.tableWithLines(heroesList, true);
		for(int i=0;i<this.GameConfig.getTeamSize();i++){
            int heroNumber = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the number corresponding to the hero you would like to choose: ", 1,heroesList.size()-1);
            scanner.nextLine();
            Hero gameHero = new Hero();
            gameHero.mapObject(heroesList.get(heroNumber));//mapper for hero class
            gameHeroes.add(gameHero);
		}
		//Creation of all the monsters from reading to mapping is taken care in Monsters class
		Monsters monsters = new Monsters();
		gameMonsters = monsters.createMonsterList(readFile);
		return lnmgameLayout;		
	}

}
