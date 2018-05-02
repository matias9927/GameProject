import java.io.IOException;
import java.util.ArrayList;

public class TileMap extends Location{
	
	public Tile[][] map = new Tile[16][16]; //might use a map
	//window variable
	
	TileMap(String name) {
		super(name);
		generateMap();
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
			for(int i = 0; i < map.length ; i++) {
				if(i == (int)p.getPosition().getX() && j == (int)p.getPosition().getY()) {
					map[i][j].isOpen = false;
				}
			}
		}
	}
	
	public void generateMap() {
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
	}
	

}
