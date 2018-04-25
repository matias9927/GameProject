import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GUIEngine implements IUIEngine, KeyListener{
	
	int SCALING = 75;
	int SIZE = 750;
	int BATTLE_SCRN_SIZE = 550;
	Scanner input = new Scanner(System.in);
	private JFrame frame;
	private JLayeredPane panel;
	private JPanel bPanel;
	
	public GUIEngine() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setSize(SIZE, SIZE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JLayeredPane();
		panel.setBackground(Color.BLUE);
		panel.setMaximumSize(new Dimension(SIZE, SIZE));
		panel.setVisible(true);
		
		frame.add(panel);
	}
	
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
		
		draw(panel.getGraphics(), world);
		draw(panel.getGraphics(), p);
	}
	
	public void updateBattle(Player p, Enemy e) {
		draw(bPanel.getGraphics(), e);
	}
	
	public void displayDialogue(String d) {
		System.out.println(d);
	}
	
	public String movePlayer() {
		return getUserInput();
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
		g.drawImage(p.sprite, p.getPosition().x * SCALING, p.getPosition().y * SCALING, SCALING, SCALING, null);
	}
	
	public void draw(Graphics g, TileMap w) {
		for(int j = 0; j < 10; j++) {
			for(int i = 0; i < 10; i++) {
				g.drawImage(w.map[i][j].sprite, i * SCALING, j * SCALING, SCALING, SCALING, null);
			}
		}
	}
	
	public void draw(Graphics g, Enemy e) {
		g.drawImage(e.sprite, bPanel.getX(), bPanel.getY() - SCALING, SCALING * 5, SCALING * 5, null);
	}
	
	public void showBattleScreen() {
		bPanel = new JPanel();
		bPanel.setSize(BATTLE_SCRN_SIZE, BATTLE_SCRN_SIZE);
		bPanel.setBackground(Color.BLUE);
		bPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2, frame.getHeight()/2 - BATTLE_SCRN_SIZE/2);
		panel.add(bPanel);
		panel.moveToFront(bPanel);
	}

}
