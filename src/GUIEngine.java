/* GUIEngine.java
 * Matias Saavedra Silva and Johnny Pabst
 * Draws all of the in-game graphics including the overworld and battle screen
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIEngine implements IUIEngine{
	
	int SCALING = 75;
	int SIZE = 750; //750
	int BATTLE_SCRN_SIZE = 450;
	Scanner input = new Scanner(System.in);
	JTextField textIn = new JTextField(10);
	private JFrame frame;
	private JLayeredPane panel;
	private JPanel bPanel;
	private JPanel bTextPanel;
	private JLabel playerStats;
	private JLabel enemyStats;
	private JPanel playerStatPanel;
	private JPanel enemyStatPanel;
	private JLabel dialogue;
	private JPanel dialogueBox;
	private JLabel text;
	public Image backg;
	public String lastKey = "";
	public JTextField command;
	public String combatCommand = "";
	public Image titleScreen;
	
	//this class establishes all frames and panels
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
		try {
			titleScreen = ImageIO.read(new File("src\\Sprites\\simple_title.png"));
		}catch(IOException e) {
			System.out.println("No Title Screen");
		}
		panel.getGraphics().drawImage(titleScreen, 0, 0, SIZE, SIZE, null);
		
		bPanel = new JPanel();
		bPanel.setSize(BATTLE_SCRN_SIZE, BATTLE_SCRN_SIZE);
		bPanel.setBackground(Color.LIGHT_GRAY);
		bPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2, frame.getHeight()/2 - BATTLE_SCRN_SIZE/2);
		bPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		
		
		
		bTextPanel = new JPanel();
		bTextPanel.setSize(BATTLE_SCRN_SIZE + SCALING, BATTLE_SCRN_SIZE/3);
		bTextPanel.setBackground(Color.BLACK);
		bTextPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2 - SCALING/2, frame.getHeight()/2 + BATTLE_SCRN_SIZE/4);
		bTextPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		
		playerStatPanel = new JPanel();
		playerStatPanel.setSize(BATTLE_SCRN_SIZE/3, BATTLE_SCRN_SIZE/2);
		playerStatPanel.setBackground(Color.BLACK);
		playerStatPanel.setLocation(bPanel.getLocation().x - bPanel.WIDTH - SCALING, bPanel.getLocation().y - bPanel.HEIGHT);
		playerStatPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		enemyStatPanel = new JPanel();
		enemyStatPanel.setSize(BATTLE_SCRN_SIZE/4, BATTLE_SCRN_SIZE/3);
		enemyStatPanel.setBackground(Color.BLACK);
		enemyStatPanel.setLocation(bPanel.getLocation().x + bPanel.getWidth(), bPanel.getLocation().y - bPanel.HEIGHT);
		enemyStatPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		playerStats = new JLabel(); 
		playerStats.setFont(new Font("Consolas",1,20));
		playerStats.setForeground(Color.WHITE);
		playerStatPanel.add(playerStats);
		
		enemyStats = new JLabel();
		enemyStats.setFont(new Font("Consolas",1,20));
		enemyStats.setForeground(Color.WHITE);
		enemyStatPanel.add(enemyStats);
		
		dialogue = new JLabel();
		dialogue.setFont(new Font("Consolas",1,20));
		dialogue.setForeground(Color.WHITE);
		bTextPanel.add(dialogue);
		
		dialogueBox = new JPanel();
		dialogueBox.setSize(BATTLE_SCRN_SIZE + SCALING, BATTLE_SCRN_SIZE/3);
		dialogueBox.setBackground(Color.BLACK);
		dialogueBox.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2 - SCALING/2, frame.getHeight()/2 + BATTLE_SCRN_SIZE/4);
		dialogueBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		//key listener which allows arrow key movement in the overworld
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch(key) {
					case KeyEvent.VK_DOWN:
						lastKey = "down";
						break;
					case KeyEvent.VK_UP:
						lastKey = "up";
						break;
					case KeyEvent.VK_LEFT:
						lastKey = "left";
						break;
					case KeyEvent.VK_RIGHT:
						lastKey = "right";
						break;
					case KeyEvent.VK_CONTROL:
						lastKey = "attack";
					default:
						lastKey = "";
						break;
				}
			}
			public void keyReleased(KeyEvent e) {
				lastKey = "";
			}
		});
	}
	
	public String getUserInput() {
		//return textIn.getText();
		//return JOptionPane.showInputDialog("Enter and action");
		return lastKey;
		/*System.out.println("Enter and action");
		return input.nextLine();*/
	}
	
	public String getCombatInput() {
		return JOptionPane.showInputDialog("Enter and action");
	}
	
	//updates player location
	public void updateScreen(TileMap world, Player p, ArrayList<Boss> objects) {
		world.checkPlayerTile(p);
		for(int j = 0; j < world.map.length; j++) {
			for(int i = 0; i < world.map[0].length; i++) {
				System.out.print(world.map[i][j]);
			}
			System.out.println();
		}
		draw(panel.getGraphics(), world, p, objects);
	}
	
	//draws battle screen
	public void updateBattle(Player p, Enemy e) {
		draw(bPanel.getGraphics(), e);
		displayPlayerStats(p.getCombatInfo());
		displayEnemyStats(e.getCombatInfo());
	}
	
	//displays NPC dialogue
	public void displayDialogue(String d) {
		//panel.add(bTextPanel);
		String e = "<html>" + d.replace("\n", "<br>") + "</html>";
		dialogue.setText(e);
		System.out.println(d);
		//panel.remove(bTextPanel);
	}
	
	//displays player attributes in battle screen
	public void displayPlayerStats(String d) {
		String e = "<html>" + d.replace("\n", "<br>") + "</html>";
		playerStats.setText(e);
		System.out.println(d);
	}
	
	//displays enemy attributes in battle screen
	public void displayEnemyStats(String d) {
		String e = "<html>" + d.replace("\n", "<br>") + "</html>";
		//statsText.setText(e); 
		enemyStats.setText(e);
		System.out.println(d);
	}
	
	public String movePlayer() {
		return getUserInput();
	}
	
	public void draw(Graphics g, Player p) {
		g.drawImage(p.sprite, p.getPosition().x * SCALING, p.getPosition().y * SCALING, SCALING, SCALING, null);
	}
	
	/*public void draw(Graphics g, TileMap w, Player p) {
		for(int j = 0; j < 10; j++) {
			for(int i = 0; i < 10; i++) {
				g.drawImage(w.map[i + screenShift(p.getPosition().x, w)][j + screenShift(p.getPosition().y, w)].sprite, i * SCALING, j * SCALING, SCALING, SCALING, null);
			}
		}
		g.drawImage(p.sprite, (p.getPosition().x - screenShift(p.getPosition().x, w)) * SCALING, (p.getPosition().y - screenShift(p.getPosition().y, w)) * SCALING, SCALING, SCALING, null);
	}*/
	
	public void draw(Graphics g, TileMap w, Player p, ArrayList<Boss> objects) {
		for(int j = 0; j < 10; j++) { //Number of tiles on screen
			for(int i = 0; i < 10; i++) {
				g.drawImage(w.map[i + screenShift(p.getPosition().x, w)][j + screenShift(p.getPosition().y, w)].sprite, i * SCALING, j * SCALING, SCALING, SCALING, null);
			}
		}
		for(Boss b : objects) {
			if(b.position.x - screenShift(p.position.x, w) > 0 && b.position.y - screenShift(p.position.y, w) > 0) {
				//g.drawImage(b.sprite, (b.getPosition().x)* SCALING, (b.getPosition().y) * SCALING, SCALING, SCALING, null);
				g.drawImage(b.overworldSprite, (b.getPosition().x - screenShift(p.getPosition().x, w))* SCALING, (b.getPosition().y - screenShift(p.getPosition().y, w)) * SCALING, SCALING, SCALING, null);
			}
		}
		g.drawImage(p.sprite, (p.getPosition().x - screenShift(p.getPosition().x, w)) * SCALING, (p.getPosition().y - screenShift(p.getPosition().y, w)) * SCALING, SCALING, SCALING, null);
	}
	
	// shifts map according to movement to keep player centered
	private int screenShift(int location, TileMap w) {
		if(location > 5) { //not on left edge
			if(location < w.map.length-5) {  //in middle
				return location - 5;
			}
			else { //right of map
				return 6; 
			}
		}
		else {
			return 0;
		}
	}
	
	public void draw(Graphics g, Enemy e) {
		g.drawImage(e.sprite, bPanel.getX() - SCALING/2, bPanel.getY() - SCALING, SCALING * 3, SCALING * 3, null);
	}
	
	public void showBattleScreen() {
		String stats = String.format("<html>%s<br>HP: %d/%d<br>ATK: %d<br>DEF: %d</html>", "Matoos", 5,5,3,3);
		/*bPanel = new JPanel();
		bPanel.setSize(BATTLE_SCRN_SIZE, BATTLE_SCRN_SIZE);
		bPanel.setBackground(Color.GREEN);
		bPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2, frame.getHeight()/2 - BATTLE_SCRN_SIZE/2);
		bPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		panel.add(bPanel);
		panel.moveToFront(bPanel);
		
		bTextPanel = new JPanel();
		bTextPanel.setSize(BATTLE_SCRN_SIZE + SCALING, BATTLE_SCRN_SIZE/3);
		//bTextPanel.setLayout(new GridLayout(0,2));
		bTextPanel.setBackground(Color.BLACK);
		bTextPanel.setLocation(frame.getWidth()/2 - BATTLE_SCRN_SIZE/2 - SCALING/2, frame.getHeight()/2 + BATTLE_SCRN_SIZE/4);
		bTextPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		
		playerStatPanel = new JPanel();
		playerStatPanel.setSize(BATTLE_SCRN_SIZE/3, BATTLE_SCRN_SIZE/2);
		playerStatPanel.setBackground(Color.BLACK);
		playerStatPanel.setLocation(bPanel.getLocation().x - bPanel.WIDTH - SCALING, bPanel.getLocation().y - bPanel.HEIGHT);
		playerStatPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		enemyStatPanel = new JPanel();
		enemyStatPanel.setSize(BATTLE_SCRN_SIZE/4, BATTLE_SCRN_SIZE/3);
		enemyStatPanel.setBackground(Color.BLACK);
		enemyStatPanel.setLocation(bPanel.getLocation().x + bPanel.getWidth(), bPanel.getLocation().y - bPanel.HEIGHT);
		enemyStatPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		playerStats = new JLabel(); 
		playerStats.setFont(new Font("Consolas",1,20));
		playerStats.setForeground(Color.WHITE);
		playerStatPanel.add(playerStats);
		
		enemyStats = new JLabel();
		enemyStats.setFont(new Font("Consolas",1,20));
		enemyStats.setForeground(Color.WHITE);
		enemyStatPanel.add(enemyStats);
		
		dialogue = new JLabel();
		dialogue.setFont(new Font("Consolas",1,20));
		dialogue.setForeground(Color.WHITE);
		bTextPanel.add(dialogue);*/
		
		panel.add(bPanel);
		panel.add(bTextPanel);
		panel.add(playerStatPanel);
		panel.add(enemyStatPanel);
	}
	
	//removes battle screen upon completion of battle
	public void endBattle() {
		panel.remove(bPanel);
		panel.remove(bTextPanel);
		panel.remove(playerStatPanel);
		panel.remove(enemyStatPanel);
	}

}
