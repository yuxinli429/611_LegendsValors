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
		return lnmgameLayout;		
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
		//play till the game is ended by user
		HeroNexus market = new HeroNexus(filereader);
		int gameRounds = 0;
		int remHeroes = this.GameConfig.getTeamSize();
		while(remHeroes != 0) {
			//After every 8 rounds spawn new monsters
			if(gameRounds%8 == 0) {
				MonsterNexus monsterNexus = new MonsterNexus(allMonsters,gameHeroes);
				List<Monster> newMonsters = new ArrayList<Monster>();
				newMonsters = monsterNexus.createMonsters(GameConfig);
				for(int i=0;i<newMonsters.size();i++) {
					if(!gameHeroes.get(i).isHeroWonGame())//check if Hero in that lane already won to atop monster spawn
						gameMonsters.add(newMonsters.get(i));
				}
			}
			for(int i=0;i<gameHeroes.size();i++) {
				if(!gameHeroes.get(i).isHeroWonGame()) {
					validateCharacterMoves(scanner, lnmgameLayout, market, i);
					gameHeroes.get(i).heroWonGame(lnmgameLayout);
				}
			}
			makeMonstersMove(scanner, lnmgameLayout);
			gameRounds++;//keeping the count of game rounds
			//check the remaining heroes who haven't already won
			Heroes playingHeroes = new Heroes(GameConfig);
			remHeroes = playingHeroes.checkRemHeroes(gameHeroes);
		}
		System.out.println("All the heroes won the game!!!");
			
	}
	
	//Each monster moves after once heroes are done moving and check if they move into vicinity of the attack
	private void makeMonstersMove(Scanner scanner, LNMGameLayout lnmgameLayout) {
		for(int i=0;i<gameHeroes.size();i++) {
			for(int j=i;j<gameMonsters.size();j=j+3) {
				if(gameMonsters.get(j).getCharacterPosition()>0 && gameMonsters.get(j).getCharacterPosition()<this.GameConfig.getGameSize()*this.GameConfig.getGameSize()) {
					System.out.println(gameMonsters.get(j).getCharacterSymbol()+", "+gameMonsters.get(j).getName()+" made a move");
					gameMonsters.get(j).MoveMonster(lnmgameLayout);
					lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
					gameHeroes.get(i).attackInRange(gameMonsters, lnmgameLayout, scanner);
				}	
			}
		}
	}
	
	//Function to check hero still in game and ask for inputs
	private void validateCharacterMoves(Scanner scanner, LNMGameLayout lnmgameLayout, HeroNexus market, int i) {
		if(lnmgameLayout.getGameCells().get(gameHeroes.get(i).getCharacterPosition()-1) == CellType.HERONEXUS.getCellTypeNumber())
			lnmgameLayout.drawLNMLayout(true,false,gameHeroes,gameMonsters);
		else
			lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
		boolean isValid = false;
		int nextPosition = 0;
		do {
			System.out.println("H"+String.valueOf(i+1)+": " + gameHeroes.get(i).getName());
			//check for the input from user to move around the board
			String input = GameFunctions.safeScanChar(scanner, "Please enter the input: ");
			nextPosition = moveHeroParty(lnmgameLayout,input, scanner,gameHeroes.get(i));
			if (nextPosition == 0)
				System.out.println("Invalid move");
			else if(nextPosition == -10)
				continue;
			else {
				gameHeroes.get(i).resetSkills();//reset skill whenever hero moves to a different cell
				checkCell(scanner, lnmgameLayout, market, nextPosition,gameHeroes.get(i));
				isValid =true;
			}
		} while ((!isValid));
		//at the end of each round hero gain 10% of HP and Mana
		gameHeroes.get(i).setHealthPower(gameHeroes.get(i).getHealthPower()*1.1);
		gameHeroes.get(i).setMana(gameHeroes.get(i).getMana()*1.1);
		//all the monsters in the lane move after hero's move
		/*for(int j=i;j<gameMonsters.size();j=j+3) {
			if(gameMonsters.get(j).getCharacterPosition()>0 && gameMonsters.get(j).getCharacterPosition()<this.GameConfig.getGameSize()*this.GameConfig.getGameSize()) {
				System.out.println(gameMonsters.get(j).getCharacterSymbol()+", "+gameMonsters.get(i).getName()+" made a move");
				gameMonsters.get(j).MoveMonster(lnmgameLayout);
				lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
				gameHeroes.get(i).attackInRange(gameMonsters, lnmgameLayout, scanner);
			}					
		}*/
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
		else if(GameConstants.LNM_BACK_KEY.equalsIgnoreCase(nextPosition)) {
			boolean back_succeed = hero.back(lnmgameLayout, gameHeroes);
			if(back_succeed) {
				nextPostn = hero.getCharacterPosition();
				isValidLane = true;
			} else {
				return -10;
			}
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
			nextPostn = hero.teleport(lnmgameLayout, scanner, allMonsters, gameHeroes);
			if(nextPostn == hero.getCharacterPosition()) {
				return -10;
			} else {
				isValidLane = true;
			}
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
	
	//Function to check each cell and do respective actions
	public void checkCell(Scanner scanner,LNMGameLayout lnmgameLayout,HeroNexus market,int nextPosition,Hero hero) {
		List<Integer> gameCells = lnmgameLayout.getGameCells();
		//if cell is nexus ask user if he would like to sell/buy modified not to check whether he would like to enter since it would give Hero chance to move twice
		if(gameCells.get(nextPosition-1)== CellType.HERONEXUS.getCellTypeNumber()) {
			hero.setCharacterPosition(nextPosition);
			hero.updateFarthestRow();
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
		}
		//check for common place. Hero gains the special abilities in each cell inside the each cell class
		else {
			checkCommonCells(nextPosition, hero, gameCells);
			lnmgameLayout.drawLNMLayout(false,true,gameHeroes,gameMonsters);
			hero.attackInRange(gameMonsters, lnmgameLayout, scanner);
		}
	}

	//Function to check if cells provide any special abilities
	private void checkCommonCells(int nextPosition, Hero hero, List<Integer> gameCells) {
		if(gameCells.get(nextPosition-1)== CellType.BUSH.getCellTypeNumber()) {
			BushCell bushCell = new BushCell();
			bushCell.addSpecialAbility(hero);
		}
		else if(gameCells.get(nextPosition-1)== CellType.CAVE.getCellTypeNumber()) {
			CaveCell caveCell = new CaveCell();
			caveCell.addSpecialAbility(hero);
		}
		else if(gameCells.get(nextPosition-1)== CellType.KOULOU.getCellTypeNumber()) {
			KoulouCell koulouCell = new KoulouCell();
			koulouCell.addSpecialAbility(hero);
		}
		hero.setCharacterPosition(nextPosition);
		hero.updateFarthestRow();
	}
	
	

}
