import java.util.ArrayList;

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
	}
	
	public void specialMove(String tecName) {
		//Do special move
	}
	
	
	

}
