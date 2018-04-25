import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tile {
	public enum TileType {GRASS, MOUNTAIN, WATER};
	public TileType type;
	public boolean isOpen;
	public Image sprite;
	public ArrayList<Enemy> monsters;
	public static int[][] MonsterStats = { 
			/*Jelli*/ {5, 1, 1, 55},
			/*Big Jelli*/ {10, 2, 2, 10},
			/*Batz*/ {8, 3, 1, 15}
	};

	Tile(TileType t) {
		type = t;
		monsters = new ArrayList<Enemy>();
		try {
		switch(type) {
		case GRASS:	sprite = ImageIO.read(new File("src\\Sprites\\grass.png"));
					isOpen = true;
					monsters.add(new Enemy("Jelli", MonsterStats[0]));
					break;
		case MOUNTAIN:	sprite = ImageIO.read(new File("src\\Sprites\\mountain.png"));
						isOpen = false;
						break;
		case WATER:	sprite = ImageIO.read(new File("src\\Sprites\\mountain.png"));
						break;
	}
		}catch(IOException e) {
			//pass
		}
	}
	
	public TileType getType() {
		return type;
	}
	
	public Enemy enemyEncounter(Player p) {
		Random rand = new Random();
		if(rand.nextInt(100) > 50) {
			return monsters.get(0);
		}
		else {
			return null;
		}
	}
	
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
		else {
			return "None";
		}
	}
}
