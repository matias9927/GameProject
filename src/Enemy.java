import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends CombatCharacter{
	
	public int spawnRate;
	public ArrayList<Item> drops = new ArrayList<Item>();
	
	Enemy(String CharName, int[] stats){
		super(CharName);
		maxHP = stats[0];
		attack = stats[1];
		defense = stats[2];
		experience = stats[3];
		
		HP = maxHP;
		try {
			sprite = ImageIO.read(new File("src\\Sprites\\lionmonster.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//pass
		}
	}
	
	public void specialMove(String tecName) {
		//Do special move
	}
	
	
	

}
