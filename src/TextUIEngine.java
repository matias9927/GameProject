/* TextUIEngine.java
 * Matias Saavedra Silva and Johnny Pabst
 * used for early testing, handles engine in-text only
 * prints out tileset and player location by matrix of characters
 */

import java.util.ArrayList;
import java.util.Scanner;

public class TextUIEngine implements IUIEngine{
	
	
	Scanner input = new Scanner(System.in);
	
	//updates and prints the tilemap and the player location as the user moves them around the map
	public void updateScreen(TileMap world, Player p, ArrayList<Boss> objects) {
		world.checkPlayerTile(p);
		for(int i = 0; i < world.map.length; i++) {
			for(int j = 0; j < world.map.length; j++) {
				System.out.print(world.map[i][j]);
			}
			System.out.println();
		}
	}
	
	public void updateBattle(Player p, Enemy e) {
		//
	}
	
	public void endBattle() {
		
	}
	
	public void displayDialogue(String d) {
		System.out.println(d);
	}
	
	public void displayPlayerStats(String d) {
		System.out.println(d);
	}
	
	public void displayEnemyStats(String d) {
		System.out.println(d);
	}
	
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}
	
	public String getCombatInput() {
		return getUserInput();
	}
	
	public String movePlayer() {
		return getUserInput();
	}
	
	public void showBattleScreen() {
		//pass
	}

}
