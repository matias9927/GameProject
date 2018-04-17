
public class Terrain extends Tile{
	
	Terrain(TileType t){
		super(t);
	}
	
	public boolean isOpen() {
		return true;
	}
	
	public void initBattle() {
		//Based on number of steps, increases chance of initiating battle with preset enemies
	}

}
