import java.util.ArrayList;

public class TileMap extends Location{
	
	public Tile[][] map = new Tile[10][10];
	
	TileMap(String name) {
		super(name);
	}
	
	public String getMonsterList(){
		//
		return "";
	}
	
	public void setMonsterList(ArrayList<Enemy> monsters) {
		//
	}
	
	

}
