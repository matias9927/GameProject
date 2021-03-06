/* Tile.java
 * Matias Saavedra Silva and Johnny Pabst
 * establishes the different tile properties and assigns the sprites
 */

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tile {
	public enum TileType {GRASS, MOUNTAIN, WATER, WARP};
	public TileType type;
	public boolean isOpen;
	public Image sprite;
	public ArrayList<Enemy> monsters;

	Tile(TileType t) {
		type = t;
		monsters = new ArrayList<Enemy>();
		try {
		switch(type) {
		case GRASS:	sprite = ImageIO.read(new File("src\\Sprites\\grass.png"));
					isOpen = true;
					monsters.add(new Enemy("Gloop"));
					monsters.add(new Enemy("Lion"));
					break;
		case MOUNTAIN:	sprite = ImageIO.read(new File("src\\Sprites\\mountain.png"));
						isOpen = false;
						break;
		case WATER:	sprite = ImageIO.read(new File("src\\Sprites\\watertile.png"));
						break;
		case WARP:	sprite = ImageIO.read(new File("src\\Sprites\\grass.png"));
					isOpen = true;
					monsters.add(new Enemy("Gloop"));
					monsters.add(new Enemy("Lion"));
					break;
	}
		}catch(IOException e) {
			//pass
		}
	}
	
	public TileType getType() {
		return type;
	}
	
	//assigns random encounter rates to traversable tiles
	public Enemy enemyEncounter(Player p) {
		Random rand = new Random();
		int chance = rand.nextInt(100);
		if(chance > 95) {
			return monsters.get(0);
		}
		else if(chance > 92) {
			return monsters.get(1);
		}
		else {
			return null;
		}
	}
	
	/* these two blocks assign tile types to characters, allowing for the conversion 
	of the text files to graphical tile maps and vice versa*/
	public String toString() {
		if(type == TileType.MOUNTAIN) {
			return " 1 ";
		}
		if(type == TileType.GRASS) {
			if(!isOpen) {
				return " X ";
			}
			else {
				return " 0 ";
			}
		}
		if(type == TileType.WATER) {
			return " ~ ";
		}
		if(type == TileType.WARP) {
			return " @ ";
		}
		else {
			return "None";
		}
	}
	
	public static Tile fromString(String s) {
		if(s.equals("0")) {
			return new Tile(TileType.GRASS);
		}
		else if(s.equals("1")) {
			return new Tile(TileType.MOUNTAIN);
		}
		else if(s.equals("@")) {
			return new Tile(TileType.WARP);
		}
		else if(s.equals("~")) {
			return new Tile(TileType.WATER);
		}
		else {
			return null;
		}
	}
}
