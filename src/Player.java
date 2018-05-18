/* Player.java
 * Matias Saavedra Silva and Johnny Pabst
 * Outlines the properties of the player character such as name, stats, sprite,
 * and associated functions such as raising the player's stats during a level up
 */
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player extends CombatCharacter{
	
	//public ArrayList<Item> inventory = new ArrayList<Item>();
	Point position = new Point(1,1); //Starts on row 1 column 1
	public enum orientation {RIGHT, LEFT, UP, DOWN};
	public orientation direction = orientation.RIGHT; 
	public Image right;
	public Image left;
	public Image up;
	public Image down;
	
	
	/* Constructor takes a String for the player's name and defines their base stats
	 * Sets sprites for each direction and sets default position to facing down
	 */
	Player(String CharName){
		super(CharName);
		maxHP += 5;
		attack += 2;
		defense += 2;
		HP = maxHP;
		
		try {
			down = ImageIO.read(new File("src\\Sprites\\overworld_player_straight.png"));
			left =  ImageIO.read(new File("src\\Sprites\\overworld_player_left.png"));
			right = ImageIO.read(new File("src\\Sprites\\overworld_player_right.png"));
			up = ImageIO.read(new File("src\\Sprites\\overworld_player_up.png"));
		} catch(IOException e) {
			System.out.println("Image not found!");
		}
		sprite = down;
	}
	
	/* Raises player stats if their experience is over a certain threshold
	 * Experience rolls over after it is reset during a level up
	 * Returns a string with level up dialogue
	 */
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
			return String.format("%s leveled up to level %d!\n", name, level) + getStats();
		}
		return "";
	}
	
	//Returns position of character on the map
	public Point getPosition() {
		return position;
	}
	
	/* Overrides CombatCharacter's method
	 * returns a string with the player's name, stats, and level
	 */
	public String getCombatInfo() {
		return String.format("%s\nLevel: %s\n%s", getName(), level, getStats());
	}

}
