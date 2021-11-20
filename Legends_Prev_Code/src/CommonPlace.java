import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//child class of cell class and has the logic to create monster or not depending on random chance
public class CommonPlace extends Cell{
	
	List<Monster> monsterList;
	List<Hero> heroList;

	@Override
	public void cellDesign() {
		// TODO Auto-generated method stub
		commonPlaceDesignTop();
		System.out.println();
		commonPlaceDesignBottom();
	}
	
	//Used to add design for board display
	public CommonPlace() {
		
	}
	
	public CommonPlace(List<Monster> monsterList,List<Hero> heroList) {
		this.monsterList = new ArrayList<Monster>();
		this.heroList = new ArrayList<Hero>();
		this.monsterList = monsterList;	
		this.heroList = heroList;
	}
	
	public void commonPlaceDesignTop() {
		System.out.print(GameConstants.ANSI_YELLOW+"|    |"+GameConstants.RESET_COLOR);
	}
	public void commonPlaceDesignBottom() {
		System.out.print(GameConstants.ANSI_YELLOW+"|^--^|"+GameConstants.RESET_COLOR);
	}
	
	//parent class method used to add game logic.
	//check the random possibility of monsters
	@Override
	public void moveToCell(Scanner scanner,LNMGameLayout lnmgameLayout) {
		// TODO Auto-generated method stub
		Random rn = new Random();
		int isMonsters = rn.nextInt(2)+1;
		if(isMonsters == 1) {
			System.out.println("Quick, monsters are here!!!");
			//fightMonsters(scanner);

		}
		else {
			System.out.println("It's your lucky day!!No monsters..");
		}		
	}
	
	//Creates monsters based on the propability above.
	//picks random monsters matching the same level as hero
	/*public List<Monster> createMonsters() {
		List<Monster> cellMonsters = new ArrayList<Monster>();
		for(int i=0;i<heroList.size();i++) {
			List<Integer> propableMonsters = new ArrayList<Integer>();
			for(int j=0;j<monsterList.size();j++) {
				if(heroList.get(i).getGameLevel() == monsterList.get(j).getGameLevel()) {
					propableMonsters.add(j);
				}				
			}
			Random rn = new Random();
			int randomMonster = rn.nextInt(propableMonsters.size());
			cellMonsters.add(monsterList.get(propableMonsters.get(randomMonster)));
		}
		return cellMonsters;
	}
	
	//heros fight 1-1 with the same number of monsters
	public void fightMonsters(Scanner scanner) {
		List<Monster >cellMonsters = new ArrayList<Monster>();
		cellMonsters = createMonsters();
		for(int i=0;i<heroList.size();i++) {
			heroList.get(i).fightMonster(cellMonsters.get(i), scanner);
		}
	}*/

}
