import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIEngine implements IUIEngine, KeyListener{
	
	Scanner input = new Scanner(System.in);
	private JFrame frame;
	private JPanel panel;
	
	public GUIEngine() {
		frame = new JFrame("Window");
		frame.setVisible(true);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setVisible(true);
		
		frame.add(panel);
	}
	
	public String getUserInput() {
		System.out.println("Enter and action");
		return input.nextLine();
	}
	
	public void updateScreen(TileMap world, Player p) {
		//draw(g, p);
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
	
	public void draw(Graphics g, Player p) {
		g.drawImage(p.sprite, p.getPosition().x, p.getPosition().y, 20, 20, null);
	}

}
