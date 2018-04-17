import java.awt.Image;

public class Tile {
	
	public enum TileType {GRASS, MOUNTAIN, WATER};
	public TileType type;
	public Image sprite;

	Tile(TileType t){
		type = t;
	}
	
	public TileType getType() {
		return type;
	}
}
