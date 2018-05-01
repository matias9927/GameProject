import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIEngine implements IUIEngine, KeyListener{
	
	int SCALING = 75;
	int SIZE = 750; 
	int BATTLE_SCRN_SIZE = 600;
	Scanner input = new Scanner(System.in);
	JTextField textIn = new JTextField(10);
	private JFrame frame;
	private JLayeredPane panel;
	private JPanel bPanel;
	private JPanel bTextPanel;
	private JLabel statsText;
	
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
		//return textIn.getText();
		return JOptionPane.showInputDialog("Enter and action");
		/*System.out.println("Enter and action");
		return input.nextLine();*/
	}
	
	public void updateScreen(TileMap world, Player p) {
		world.checkPlayerTile(p);
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(world.map[i + p.getPosition().x][j + p.getPosition().y]);
			}
			System.out.println();
		}
		
		draw(panel.getGraphics(), world, p);
		//draw(panel.getGraphics(), p);
	}
	
	public void updateBattle(Player p, Enemy e) {
		draw(bPanel.getGraphics(), e);
	}
	
	public void displayDialogue(String d) {
		String e = "<html>" + d.replace("\n", "<br>") + "</html>";
		statsText.setText(e);
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
	
	public void draw(Graphics g, TileMap w, Player p) {
		for(int j = 0; j < 10; j++) {
			for(int i = 0; i < 10; i++) {
				g.drawImage(w.map[i + screenShift(p.getPosition().x)][j + screenShift(p.getPosition().y)].sprite, i * SCALING, j * SCALING, SCALING, SCALING, null);
			}
		}
		g.drawImage(p.sprite, p.getPosition().x * SCALING, p.getPosition().y * SCALING, SCALING, SCALING, null);
	}
	
	private int screenShift(int location) {
		if(location - 8 >= 0) {
			return 2;
		}
		else {
			return 0;
		}
	}
	
	public void draw(Graphics g, Enemy e) {
		g.drawImage(e.sprite, bPanel.getX(), bPanel.getY() - SCALING, SCALING * 5, SCALING * 5, null);
	}
	
	public void showBattleScreen() {
		String stats = String.format("<html>%s<br>HP: %d/%d<br>ATK: %d<br>DEF: %d</html>", "Matoos", 5,5,3,3);
		bPanel = new JPanel();
		bPanel.setSize(BATTLE_SCRN_SIZE, BATTLE_SCRN_SIZE);
		bPanel.setBackground(Color.GREEN);
		bPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2, frame.getHeight()/2 - BATTLE_SCRN_SIZE/2);
		bPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		panel.add(bPanel);
		panel.moveToFront(bPanel);
		
		bTextPanel = new JPanel();
		bTextPanel.setSize(BATTLE_SCRN_SIZE, BATTLE_SCRN_SIZE/4);
		bTextPanel.setBackground(Color.BLACK);
		bTextPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2, frame.getHeight()/2 + BATTLE_SCRN_SIZE/4);
		bTextPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		
		statsText = new JLabel(); //stats
		statsText.setFont(new Font("Consolas",1,20));
		statsText.setForeground(Color.WHITE);
		bTextPanel.add(statsText);
		//bTextPanel.add(textIn);
		
		panel.add(bTextPanel);
		panel.moveToFront(bTextPanel);
	}

}
