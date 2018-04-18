import java.io.IOException;

public class EventTile extends Tile{
	
	private String effect;
	
	EventTile(TileType t) {
		super(t);
		effect = "";
	}
	
	public String getEffect() {
		return effect;
	}
	
	public void dialogue(String d) {
		//Print dialogue d
	}
	
	public void warp(int x, int y) {
		//Warp player to position x,y
	}
	
	public void damage(Player p) {
		//Deals damage to player
	}
	
	public void recover(Player p) {
		//Recovers player HP
	}

}
