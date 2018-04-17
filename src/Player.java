import java.util.ArrayList;

public class Player extends CombatCharacter{
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	private int xPos;
	private int yPos;
	
	Player(String CharName){
		super(CharName);
		maxHP += 5;
		//maxMP += 5;
		attack += 2;
		defense += 2;
		//intelligence += 1;

		HP = maxHP;
		//MP = maxMP;
	}
	
	public void levelUp() {
		if(experience >= level * 50) {
			experience -= (level * 50);
			level += 1;
			maxHP += 5;
			maxMP += 2;
			attack += 1;
			defense += 1;
			
			HP = maxHP;
			MP = maxMP;
			System.out.printf("%s leveled up to level %d!\n", name, level);
			System.out.println(getStats());
		}
	}
	
	public void move() {
		//Take keyboard input and move character
	}
	
	public void interact() {
		//Pressing z initiated interaction with npcs or objects
	}
	
	public void addItem(Item i) {
		//Add item to inventory
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}

}
