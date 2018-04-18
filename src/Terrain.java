import java.io.IOException;

public class Terrain extends Tile{
	
	Terrain(TileType t) {
		super(t);
		isOpen = true;
	}
	
	public boolean isOpen() {
		return true;
	}
	
	public void initBattle() {
		//Based on number of steps, increases chance of initiating battle with preset enemies
	}
	
	public String toString() {
		if(!isOpen) {
			return " X ";
		}
		else {
			return super.toString();
		}
	}

}
