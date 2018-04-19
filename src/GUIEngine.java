import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;

public class GUIEngine implements IUIEngine, KeyListener{
	
	Scanner input = new Scanner(System.in);
	
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}
	
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
	
	public String movePlayer() {
		return "right";
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP:
				//up
				break;
			case KeyEvent.VK_DOWN:
				//down
				break;
			case KeyEvent.VK_LEFT:
				//left
				break;
			case KeyEvent.VK_RIGHT:
				movePlayer();
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
