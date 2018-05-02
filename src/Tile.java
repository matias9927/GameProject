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
		int chance = rand.nextInt(100);
		if(chance > 90) {
			return monsters.get(0);
		}
		else if(chance > 85) {
			return monsters.get(1);
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
		if(type == TileType.WATER) {
			return " ~ ";
		}
		else {
			return "None";
		}
	}
}
