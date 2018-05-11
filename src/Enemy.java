import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends CombatCharacter{
	
	public int spawnRate;
	public ArrayList<Item> drops = new ArrayList<Item>();
	public static int[][] MonsterStats = { 
			/*Gloop*/ {5, 1, 1, 55},
			/*Big Gloop*/ {10, 2, 2, 10},
			/*Batz*/ {8, 3, 1, 15}
	};
	public int[] stats;
	
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
	
	public void specialMove(String tecName) {
		//Do special move
	}
	
	
	

}
