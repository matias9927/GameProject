/* Game.java
 * Matias Saavedra Silva and Johnny Pabst
 * Main class that runs the game, it takes in a UI 
 * Defines the in-game events and methods that allow the player character to move
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
	
	private IUIEngine ui;
	public TileMap world;
	public boolean isPlaying;
	public boolean isMoving;
	public ArrayList<Boss> objects;
	public int location;
	public Sound music;
	
	/* Takes an IUIEngine as a parameter 
	 * Creates a tile map, sets the default location, creates the list of objects,
	 * and defines sets the isPlaying boolean to true
	 */
	public Game(IUIEngine ui) {
		this.ui = ui;
		world = new TileMap("Overworld");
		isPlaying = true;
		isMoving = false;
		location = 0;
		objects = new ArrayList<Boss>();
		music = new Sound();
	}
	
	/* Takes the time in milliseconds and pauses the game for 
	 * the input time
	 */
	private void wait(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//
		}
	}
	
	/* Contains the main events of the game, prompts the user for their name
	 * and starts the game loop. 
	 */
	public void startgame() {
		System.out.println("What is your name?");
		String name = ui.getCombatInput();
		Player hero = new Player(name);
		ui.updateScreen(world, hero, objects); //Draw the screen before the player has done anything
		music.soundPlay(music.overworldMusic);
		while(isPlaying) {
			//Redraws the screen only if the player moves
			if(isMoving){
				ui.updateScreen(world, hero, objects);
				wait(250);
			}
			movePlayer(ui.movePlayer(), world, hero);
			//Move player to the next map if they step on a warp tile and add boss to the objects list
			if(world.map[hero.getPosition().x][hero.getPosition().y].type == Tile.TileType.WARP) {
				location = 1;
				world.warp(hero, 1, 1, "map2");
				Boss villain = new Boss("BadMan");
				objects.add(villain);
			}
			//Initiate battle with boss if the player steps on their tile
			if(location == 1) {
				if(hero.getPosition().x == objects.get(0).getPosition().x && hero.getPosition().y == objects.get(0).getPosition().y) {
					music.soundStop();
					music.soundPlay(music.bossMusic);
					engageBattle(hero, objects.get(0));
					music.soundStop();
					objects.remove(0);
					isPlaying = false;
				}
			}
		}
		System.exit(0);
	}
	
	/* Takes the direction to move as a string, the current tile map, and the player
	 * Checks if the tile ahead of the player in the specified direction is open
	 */
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
	
	/* Takes the direction to move as a string, the current tile map, and the player
	 * Tests if the player can move in the specified direction and if possible
	 * moves them to that tile. 
	 */
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
			//Calls an enemy encounter every time the player moves
			Enemy encounter = world.map[(int)p.position.getX()][(int)p.position.getY()].enemyEncounter(p);
			if(encounter != null) {
				music.soundStop();
				music.soundPlay(music.battleMusic);
				engageBattle(p, encounter);
				music.soundStop();
				music.soundPlay(music.overworldMusic);
			}
		}
		else {
			//ui.displayDialogue("Wall!");
		}
		
	}
	
	/* Takes a player and enemy object
	 * Creates an instance of battle with the player and enemy
	 */
	public void engageBattle(Player p, Enemy e) {
		Battle b = new Battle(p, e, ui); 
		b.battle(p, e);
	}
	
	//Creates an instance of the game and starts the game
	public static void main(String[] args) {
		Game game = new Game(new GUIEngine()); 
		game.startgame();
	}

}
