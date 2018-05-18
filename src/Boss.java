/* Boss.java
 * Matias Saavedra Silva and Johnny Pabst
 * Defines the sprite and behavior of the boss enemy
 */
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Boss extends Enemy{
	
	public Image overworldSprite;
	public Point position = new Point(8,7); 
	public int location = 1;
	
	/* Takes name and sets the overworld and battle sprites to a predefined value
	 */
	Boss(String CharName){
		super(CharName);
		maxHP = 20;
		attack = 6;
		defense = 4;
		experience = 120;
		HP = maxHP;
		try {
			overworldSprite = ImageIO.read(new File("src\\Sprites\\boss_overworld.png"));
			sprite = ImageIO.read(new File("src\\Sprites\\boss_inbattle.png"));
		}catch(IOException e) {
			//ree
		}
	}
	
	//Returns the position on the map of the boss
	public Point getPosition() {
		return position;
	}
	
	public String showDialogue() {
		return "I am the final boss!\nI am BadMan! Fear me!!";
	}

}
