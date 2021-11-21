import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//LegendsOfValor class has the logic for actual game start
public class LegendsOfValor extends RolePlayGame{	

	private List<Hero> gameHeroes;
	private List<Monster> gameMonsters;
	private List<Monster> allMonsters;


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Legends of Valor";
	}
	

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		System.out.println();
		GameDesigns.displayMonsterDesign();
		System.out.println();
		System.out.println("Let's play "+getName()+"!!\n");
		Scanner scanner = new Scanner(System.in);
		FileToList filereader = new FileToList();
		LNMGameLayout lnmgameLayout = initializegame(scanner);
		System.out.println("Welcome to the village!!");
		//play till the game is end or ended by user
		HeroNexus market = new HeroNexus(filereader);
		while(true) {
			for(int i=0;i<gameHeroes.size();i++) {
				if(lnmgameLayout.getGameCells().get(gameHeroes.get(i).getCharacterPosition()-1) == CellType.HERONEXUS.getCellTypeNumber())
					lnmgameLayout.drawLNMLayout(true,false,gameHeroes,gameMonsters);
				else
					lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
				boolean isValid = false;
				int nextPosition = 0;
				do {
					//nextPosition = gameHeroes.get(i).getCharacterPosition();
					//checkCell(scanner, lnmgameLayout, market, nextPosition,gameHeroes.get(i));
					System.out.println("H"+String.valueOf(i+1)+": " + gameHeroes.get(i).getName());
					//check for the input from user to move around the board
					String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
					nextPosition = moveHeroParty(lnmgameLayout,input, scanner,gameHeroes.get(i));
					if (nextPosition == 0)
						System.out.println("Invalid move");
					else if(nextPosition == -10)
						continue;
					else {
						checkCell(scanner, lnmgameLayout, market, nextPosition,gameHeroes.get(i));
						isValid =true;
					}
				} while ((!isValid));
				//HeroNexus market = new HeroNexus(filereader);
				//once the heroparty move is valid and moved, check for the type of the cell
				//checkCell(scanner, lnmgameLayout, market, nextPosition,gameHeroes.get(i));
				System.out.println(gameMonsters.get(i).getCharacterSymbol()+", "+gameMonsters.get(i).getName()+" made a move");
				gameMonsters.get(i).MoveMonster(lnmgameLayout);
			}
		}
			
	}
	
	//function to check for user inputs and validity of movement in the map
	public int moveHeroParty(LNMGameLayout lnmgameLayout,String nextPosition, Scanner scanner, Hero hero) {
		int currentPosition = hero.getCharacterPosition();
		int nextPostn = currentPosition;
		boolean isValidLane = false;
		boolean checkInfo = false;
		if(GameConstants.LNM_UP_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition-this.GameConfig.getGameSize();
			isValidLane = true;
		}
		else if(GameConstants.LNM_DOWN_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition+this.GameConfig.getGameSize();
			isValidLane = true;
		}
		else if(GameConstants.LNM_RIGHT_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition+1;
			int[] arr1 = new int[2];
			int[] arr2 = new int[2];
			arr1 = GameFunctions.int_to_ij(currentPosition, this.GameConfig.getGameSize());
			arr2 = GameFunctions.int_to_ij(nextPostn, this.GameConfig.getGameSize());
			if(arr1[0] == arr2[0])
				isValidLane = true;
		}
		else if(GameConstants.LNM_LEFT_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = currentPosition-1;
			int[] arr1 = new int[2];
			int[] arr2 = new int[2];
			arr1 = GameFunctions.int_to_ij(currentPosition, this.GameConfig.getGameSize());
			arr2 = GameFunctions.int_to_ij(nextPostn, this.GameConfig.getGameSize());
			if(arr1[0] == arr2[0])
				isValidLane = true;
		}
		else if(GameConstants.LNM_TELEPORT_KEY.equalsIgnoreCase(nextPosition)) {
			hero.teleport(lnmgameLayout, scanner, allMonsters, gameHeroes);
		}
		else if(GameConstants.LNM_MARKET_KEY.equalsIgnoreCase(nextPosition)) {
			nextPostn = -20;
		}
		else if(GameConstants.LNM_INFO_KEY.equalsIgnoreCase(nextPosition)) {
				hero.displayInfo();
				checkInfo = true;
			//return currentPosition;
		}
		else if(GameConstants.LNM_QUIT_KEY.equalsIgnoreCase(nextPosition)) {
			System.out.println("You chose to quit. Thankyou for playing!!");
			System.exit(0);
		}
		else if(GameConstants.LNM_INVENTORY_KEY.equalsIgnoreCase(nextPosition)) {
				hero.checkInventory(scanner);
				isValidLane = true;
		}		
		if((nextPostn>0 && nextPostn<=(this.GameConfig.getGameSize()*this.GameConfig.getGameSize()) && isValidLane == true) || nextPostn == -20)
			return nextPostn;
		else if(checkInfo == true)
			return -10;
		else
			return 0;		
	}
	
	public void checkCell(Scanner scanner,LNMGameLayout lnmgameLayout,HeroNexus market,int nextPosition,Hero hero) {
		List<Integer> gameCells = lnmgameLayout.getGameCells();
		//if cell is nexus ask user if he would like to sell/buy modified not to check whether he would like to enter since it would give Hero chance to move twice
		if(gameCells.get(nextPosition-1)== CellType.HERONEXUS.getCellTypeNumber()) {
			hero.setCharacterPosition(nextPosition);
			System.out.println(hero.getCharacterSymbol()+", You are in the nexus!!");
			boolean isDone = false;
			while(!isDone) {
				int marketOption = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the number you would like to perform at the nexus:\n1.Buy\n2.Sell\n3.Done\nInput: ", 1,3);
				scanner.nextLine();
				if(marketOption == 1) {
					market.sellInventory(hero, scanner);
				}
				else if(marketOption == 2){
					hero.sellHeroInventory(scanner);
				}
				else {
					isDone = true;
				}
			}
		}
		//Check for inaccessible cell
		else if(gameCells.get(nextPosition-1)== CellType.INACCESSIBLECELL.getCellTypeNumber()) {
			InaccessibleCell cell = new InaccessibleCell();
			cell.moveToCell(hero);
			//lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
			//String input = GameFunctions.safeScanChar(scanner, hero.getCharacterSymbol()+", Please enter the input: ");
			//nextPosition = moveHeroParty(input,scanner,hero);
			//checkCell(scanner,lnmgameLayout, market, nextPosition,hero);			
		}
		//check for common place. Hero gains the special abilities in each cell inside the each cell class
		else {
			hero.setCharacterPosition(nextPosition);
			//hero.attackInRange(gameMonsters, lnmgameLayout, scanner);
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