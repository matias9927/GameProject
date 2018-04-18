import java.util.Scanner;

public class TextUIEngine implements IUIEngine{
	
	
	Scanner input = new Scanner(System.in);
	
	public void updateScreen(TileMap world, Player p) {
		world.checkPlayerTile(p);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(world.map[i][j]);
			}
			System.out.println();
		}
	}
	
	public void displayDialogue(String d) {
		System.out.println(d);
	}
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}

}
