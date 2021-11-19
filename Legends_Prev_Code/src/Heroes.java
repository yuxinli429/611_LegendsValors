import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Heroes {
	
	private List<Hero> selectedHeroes;
	private int heroPartySize;
	
	public Heroes(GameConfig gameConfig) {
		this.setHeroPartySize(gameConfig.getTeamSize());
		
	}
	
	public void readInputs(Scanner scanner,FileToList readFile) {
		selectedHeroes = new ArrayList<Hero>();
		ArrayList<ArrayList<String>> heroesList = new ArrayList<ArrayList<String>>();
		heroesList = displayHeroes(readFile);
		System.out.println("Game will be played by 3 heroes!!");
		for(int i=0;i<this.getHeroPartySize();i++){
			String heroSymbol = "H"+String.valueOf(i+1);
            int heroNumber = GameFunctions.safeScanIntWithLimit(scanner,"Please enter the number corresponding to the hero you would like to choose: ", 1,heroesList.size()-1);
            scanner.nextLine();
            if(heroesList.get(heroNumber).get(7) == "Paladin") {
            	Paladin gameHero = new Paladin();
            	gameHero.mapObject(heroesList.get(heroNumber),heroSymbol);//mapper for hero class
                selectedHeroes.add(gameHero); 
            }
    		else if(heroesList.get(heroNumber).get(7) == "Sorcerer") {
    			Sorcerer gameHero = new Sorcerer();
    			gameHero.mapObject(heroesList.get(heroNumber),heroSymbol);//mapper for hero class
                selectedHeroes.add(gameHero); 
    		}
    		else {
    			Warrior gameHero = new Warrior();
    			gameHero.mapObject(heroesList.get(heroNumber),heroSymbol);//mapper for hero class
                selectedHeroes.add(gameHero); 
    		}
		}
	}
	
	//Hero list is build and displayed since no storing is required for all the heroes
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
		GameDesigns.tableWithLines(heroesList, true);
		return heroesList;
	}

	public List<Hero> getSelectedHeroes() {
		return selectedHeroes;
	}


	public int getHeroPartySize() {
		return heroPartySize;
	}

	public void setHeroPartySize(int heroPartySize) {
		this.heroPartySize = heroPartySize;
	}

}
