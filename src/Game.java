import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	private IUIEngine ui;
	public TileMap world;
	public boolean isPlaying;
	
	public Game(IUIEngine ui) {
		this.ui = ui;
		world = new TileMap("Overworld");
		isPlaying = true;
	}
	
	public void startgame() {
		System.out.println("What is your name?");
		String name = ui.getUserInput();
		Player hero = new Player(name);
		while(isPlaying) {
			ui.updateScreen(world, hero);
			//movePlayer(ui.getUserInput(), world, hero);
			movePlayer(ui.movePlayer(), world, hero);
		}
	}
	
	public boolean canMove(String direction, TileMap world, Player p) {
		if(direction.equals("right")) {
			return world.map[(int)p.position.getY()][(int)p.position.getX() + 1].isOpen;
		}
		if(direction.equals("left")) {
			return world.map[(int)p.position.getY()][(int)p.position.getX() - 1].isOpen;
		}
		if(direction.equals("down")) {
			return world.map[(int)p.position.getY() + 1][(int)p.position.getX()].isOpen;
		}
		if(direction.equals("up")) {
			return world.map[(int)p.position.getY() - 1][(int)p.position.getX()].isOpen;
		}
		else {
			ui.displayDialogue("Invalid move");
			return false;
		}
	}
	
	public void movePlayer(String direction, TileMap world, Player p) {
		if(canMove(direction, world, p)) {
			world.map[(int)p.position.getY()][(int)p.position.getX()].isOpen = true;
			switch(direction) {
			case "right":
					world.map[(int)p.position.getY()][(int)p.position.getX() + 1].isOpen = false;
					p.position.move((int)p.position.getX()+1, (int)p.position.getY());
					break;
			case "left":	
					world.map[(int)p.position.getY()][(int)p.position.getX() - 1].isOpen = false;
					p.position.move((int)p.position.getX() - 1, (int)p.position.getY());
					break;
			case "down":	
					world.map[(int)p.position.getY() + 1][(int)p.position.getX()].isOpen = false;
					p.position.move((int)p.position.getX(), (int)p.position.getY() + 1);
					break;
			case "up":	
				world.map[(int)p.position.getY() - 1][(int)p.position.getX()].isOpen = false;
				p.position.move((int)p.position.getX(), (int)p.position.getY() - 1);
				break;
			default:
				break;
			}
			Enemy encounter = world.map[(int)p.position.getY()][(int)p.position.getX()].enemyEncounter(p);
			if(encounter != null) {
				engageBattle(p, encounter);
			}
		}
		else {
			ui.displayDialogue("Wall!");
		}
		
	}
	
	public void engageBattle(Player p, Enemy e) {
		Battle b = new Battle(p, e, ui);
		b.battle(p, e);
	}
	
	public static void main(String[] args) {
		Game game = new Game(new GUIEngine()); 
		game.startgame();
	}

}
