import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends CombatCharacter{
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	Point position = new Point(2,2);
	
	Player(String CharName){
		super(CharName);
		maxHP += 5;
		//maxMP += 5;
		attack += 2;
		defense += 2;
		//intelligence += 1;

		HP = maxHP;
		//MP = maxMP;
		
		try {
			sprite = ImageIO.read(new File("player.png"));
		} catch(IOException e) {
			System.out.println("Image not found!");
		}
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
	
	public void interact() {
		//Pressing z initiated interaction with npcs or objects
	}
	
	public void addItem(Item i) {
		//Add item to inventory
	}
	
	public Point getPosition() {
		return position;
	}

}
