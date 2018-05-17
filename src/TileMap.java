/* TileMap.java
 * Matias Saavedra Silva and Johnny Pabst
 * Draws all of the in-game graphics including the overworld and battle screen
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TileMap extends Location{
	
	public Tile[][] map = new Tile[16][16]; //might use a map
	//window variable
	
	TileMap(String name) {
		super(name);
		generateMap("map1");
	}
	
	public String getMonsterList(){
		//
		return "";
	}
	
	public void setMonsterList(ArrayList<Enemy> monsters) {
		//
	}
	
	
	public void checkPlayerTile(Player p) {
		for(int j = 0; j < map.length; j++) {
			for(int i = 0; i < map[0].length; i++) {
				if(i == (int)p.getPosition().getX() && j == (int)p.getPosition().getY()) {
					map[i][j].isOpen = false;
				}
			}
		}
	}
	
	//moves the player to a different map
	public void warp(Player p, int x, int y, String mapName) {
		p.getPosition().x = x;
		p.getPosition().y = y;
		generateMap(mapName);
	}
	
	/*public void generateMap() {
		for(int j = 0; j < map.length; j++) {
			for(int i = 0; i < map.length ; i++) {
				map[i][0] = new Tile(Tile.TileType.MOUNTAIN);
				map[i][map.length - 1] = new Tile(Tile.TileType.MOUNTAIN);
				map[0][j] = new Tile(Tile.TileType.MOUNTAIN);
				map[map.length - 1][j] = new Tile(Tile.TileType.MOUNTAIN);
				
			}
		}	
		for(int i = 1; i < map.length - 1 ; i++) {
			for(int j = 1; j < map.length - 1 ; j++) {
				map[i][j] = new Tile(Tile.TileType.GRASS);
				
			}
		}
		map[8][8] = new Tile(Tile.TileType.WATER);
		map[9][8] = new Tile(Tile.TileType.WATER);
		map[10][8] = new Tile(Tile.TileType.WATER);
	}*/
	
	/*this function allows for the creation of various tilemaps via basic text files and generates the map 
	based upon different characters associated with different types of tiles*/
	public void generateMap(String mapFile) {
		String path = String.format("src\\Maps\\%s.txt", mapFile);
		String[] line;
		try {
			File f = new File(path);
			Scanner m = new Scanner(f);
			for(int j = 0; j < map.length; j++) {
				line = m.nextLine().split(",");
				for(int i = 0; i < line.length; i++) {
					map[i][j] = Tile.fromString(line[i]);
				}
			}
		}
		catch(Exception e) {
			//
		}
		//System.out.println(map[0].length);
	}
	

}
