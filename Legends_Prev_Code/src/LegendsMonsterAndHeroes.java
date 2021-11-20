import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//LegendsMonsterAndHeroes class has the logic for actual game start
public class LegendsMonsterAndHeroes extends RolePlayGame{	

	private List<Hero> gameHeroes;
	private List<Monster> gameMonsters;
	private List<Monster> allMonsters;


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
			lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
			for(int i=0;i<gameHeroes.size();i++) {
				boolean isValid = false;
				int nextPosition = 0;
				do {
					System.out.println("H"+String.valueOf(i+1)+": " + gameHeroes.get(i).getName());
					//check for the input from user to move around the board
					String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
					nextPosition = moveHeroParty(lnmgameLayout, input, scanner);
					if (nextPosition == 0)
						System.out.println("Invalid move");
					else
						isValid = true;
				} while ((!isValid));
				HeroNexus market = new HeroNexus(filereader);
				//once the heroparty move is valid and moved, check for the type of the cell
				checkCell(scanner, lnmgameLayout, market, nextPosition);
			}
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
		else if(GameConstants.LNM_TELEPORT_KEY.equalsIgnoreCase(nextPosition)) {
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
	
	public void checkCell(Scanner scanner,LNMGameLayout lnmgameLayout,HeroNexus market,int nextPosition) {
		List<Integer> gameCells = lnmgameLayout.getGameCells();
		//if cell is market ask user if he would like to enter or continue game
		if(gameCells.get(nextPosition-1)== CellType.HERONEXUS.getCellTypeNumber()) {
			lnmgameLayout.setGameStartPosition(nextPosition);
			System.out.println("You are in the market place!!");
			lnmgameLayout.drawLNMLayout(true,false,gameHeroes,gameMonsters);
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
			lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
			String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
			nextPosition = moveHeroParty(lnmgameLayout,input,scanner);
			checkCell(scanner,lnmgameLayout, market, nextPosition);			
		}
		//check for common place. All the game logic is inside class since monsters are not created at the beginning
		else if(gameCells.get(nextPosition-1)== CellType.PLAIN.getCellTypeNumber()) {
			lnmgameLayout.setGameStartPosition(nextPosition);
			CommonPlace cell = new CommonPlace(gameMonsters,gameHeroes);
			cell.moveToCell(scanner,lnmgameLayout);
		}
	}
	
	
	//function to initialize the game with 
	public LNMGameLayout initializegame(Scanner scanner) {
		gameHeroes = new ArrayList<Hero>();
		gameMonsters = new ArrayList<Monster>();
		allMonsters = new ArrayList<Monster>();
		this.GameConfig.setTeamSize(GameConstants.LNM_PLAYER_COUNT_MAX);
        this.GameConfig.setGameSize(GameConstants.LNM_LAYOUT_SIZE);
        FileToList readFile = new FileToList();//Parser created and used for all classes
        LNMGameLayout lnmgameLayout = new LNMGameLayout(GameConfig);//Game layout created at the beginning of the game
        Heroes heroTeam = new Heroes(GameConfig);
        heroTeam.readInputs(scanner, readFile,GameConfig);
        gameHeroes = heroTeam.getSelectedHeroes();
		//Creation of all the monsters from reading to mapping is taken care in Monsters class
		Monsters monsters = new Monsters();
		allMonsters = monsters.createMonsterList(readFile);
		MonsterNexus monsterNexus = new MonsterNexus(allMonsters,gameHeroes);
		gameMonsters = monsterNexus.createMonsters(GameConfig);
		return lnmgameLayout;		
	}

}
