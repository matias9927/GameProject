import java.io.IOException;
import java.util.ArrayList;

public class TileMap extends Location{
	
	public Tile[][] map = new Tile[10][10]; //might use a map
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
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length ; j++) {
				if(i == (int)p.getPosition().getY() && j == (int)p.getPosition().getX()) {
					map[i][j].isOpen = false;
				}
			}
		}
	}
	
	public void generateMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length ; j++) {
				map[i][0] = new Tile(Tile.TileType.MOUNTAIN);
				map[i][9] = new Tile(Tile.TileType.MOUNTAIN);
				map[0][j] = new Tile(Tile.TileType.MOUNTAIN);
				map[9][j] = new Tile(Tile.TileType.MOUNTAIN);
				
			}
		}	
		for(int i = 1; i < map.length - 1 ; i++) {
			for(int j = 1; j < map.length - 1 ; j++) {
				map[i][j] = new Tile(Tile.TileType.GRASS);
				
			}
		}	
	}
	

}
