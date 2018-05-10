import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
	
	private IUIEngine ui;
	public TileMap world;
	public boolean isPlaying;
	public boolean isMoving;
	
	public Game(IUIEngine ui) {
		this.ui = ui;
		world = new TileMap("Overworld");
		isPlaying = true;
		isMoving = false;
	}
	
	private void wait(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//
		}
	}
	
	public void startgame() {
		System.out.println("What is your name?");
		String name = ui.getUserInput();
		Player hero = new Player(name);
		ui.updateScreen(world, hero);
		while(isPlaying) {
			if(isMoving){
				ui.updateScreen(world, hero);
				wait(250);
			}
			movePlayer(ui.movePlayer(), world, hero);
			if(world.map[hero.getPosition().x][hero.getPosition().y].type == Tile.TileType.WARP) {
				world.warp(hero, 1, 1, "map2");
			}
		}
	}
	
	public boolean canMove(String direction, TileMap world, Player p) {
		if(direction.equals("right")) {
			return world.map[p.position.x + 1][(int)p.position.y].isOpen;
		}
		if(direction.equals("left")) {
			return world.map[(int)p.position.getX() - 1][(int)p.position.getY()].isOpen;
		}
		if(direction.equals("down")) {
			return world.map[(int)p.position.getX()][(int)p.position.getY() + 1].isOpen;
		}
		if(direction.equals("up")) {
			return world.map[(int)p.position.getX()][(int)p.position.getY() - 1].isOpen;
		}
		if(direction.equals("")) {
			isMoving = false;
			return false;
		}
		else {
			ui.displayDialogue("Invalid move");
			return false;
		}
	}
	
	public void movePlayer(String direction, TileMap world, Player p) {
		if(canMove(direction, world, p)) {
			world.map[(int)p.position.getX()][(int)p.position.getY()].isOpen = true;
			switch(direction) {
			case "right":
					world.map[(int)p.position.getX() + 1][(int)p.position.getY()].isOpen = false;
					p.position.move((int)p.position.getX()+1, (int)p.position.getY());
					p.sprite = p.right;
					isMoving = true;
					break;
			case "left":	
					world.map[(int)p.position.getX() - 1][(int)p.position.getY()].isOpen = false;
					p.position.move((int)p.position.getX() - 1, (int)p.position.getY());
					p.sprite = p.left;
					isMoving = true;
					break;
			case "down":	
					world.map[(int)p.position.getX()][(int)p.position.getY() + 1].isOpen = false;
					p.position.move((int)p.position.getX(), (int)p.position.getY() + 1);
					p.sprite = p.down;
					isMoving = true;
					break;
			case "up":	
				world.map[(int)p.position.getX()][(int)p.position.getY() - 1].isOpen = false;
				p.position.move((int)p.position.getX(), (int)p.position.getY() - 1);
				p.sprite = p.up;
				isMoving = true;
				break;
			case "":
				isMoving = false;
				break;
			}
			Enemy encounter = world.map[(int)p.position.getX()][(int)p.position.getY()].enemyEncounter(p);
			if(encounter != null) {
				engageBattle(p, encounter);
			}
		}
		else {
			//ui.displayDialogue("Wall!");
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
