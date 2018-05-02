import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends CombatCharacter{
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	Point position = new Point(5,5);
	public enum orientation {RIGHT, LEFT, UP, DOWN};
	public orientation direction = orientation.RIGHT; 
	public Image right;
	public Image left;
	public Image up;
	public Image down;
	
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
			down = ImageIO.read(new File("src\\Sprites\\overworld_player_straight.png"));
			left =  ImageIO.read(new File("src\\Sprites\\overworld_player_left.png"));
			right = ImageIO.read(new File("src\\Sprites\\overworld_player_right.png"));
		} catch(IOException e) {
			System.out.println("Image not found!");
		}
		sprite = down;
	}
	
	public String levelUp() {
		if(experience >= level * 50) {
			experience -= (level * 50);
			level += 1;
			maxHP += 5;
			maxMP += 2;
			attack += 1;
			defense += 1;
			
			HP = maxHP;
			MP = maxMP;
			//System.out.println(getStats());
			return String.format("%s leveled up to level %d!\n", name, level) + getStats();
		}
		return null;
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
