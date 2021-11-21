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
	public void moveToCell(Hero hero) {
		// TODO Auto-generated method stub	
	}


}
