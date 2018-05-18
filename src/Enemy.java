/* Enemy.java
 * Matias Saavedra Silva and Johhny Pabst
 * Defines the stats and sprites for various types of monsters
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Enemy extends CombatCharacter{
	
	//Stats are in order : HP, Attack, Defense, Experience
	public static int[][] MonsterStats = { 
			/*Gloop*/ {5, 1, 1, 55},
			/*Big Gloop*/ {10, 2, 2, 10},
			/*Batz*/ {8, 3, 1, 15}
	};
	public int[] stats;
	
	/* Takes the name of the enemy, depending on the name sets the stats and 
	 * sprite to predefined values
	 */
	Enemy(String CharName){
		super(CharName);
		try {
			switch(name) {
			case "Lion":
				sprite = ImageIO.read(new File("src\\Sprites\\lionmonster.png"));
				stats = MonsterStats[2];
				break;
			case "Gloop":
				sprite = ImageIO.read(new File("src\\Sprites\\gloopmonster.png"));
				stats = MonsterStats[0];
				break;
			default:
				stats = MonsterStats[0];
				break;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//pass
		}
		maxHP = stats[0];
		attack = stats[1];
		defense = stats[2];
		experience = stats[3];
		HP = maxHP;
	}
	
	//Return monster's dialogue before battle
	public String showDialogue() {
		return "";
	}
}
