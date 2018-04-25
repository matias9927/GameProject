import java.util.Scanner;

public class TextUIEngine implements IUIEngine{
	
	
	Scanner input = new Scanner(System.in);
	
	public void updateScreen(TileMap world, Player p) {
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
	
	public void displayDialogue(String d) {
		System.out.println(d);
	}
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}
	
	public String movePlayer() {
		return getUserInput();
	}
	
	public void showBattleScreen() {
		//pass
	}

}
