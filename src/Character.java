import java.awt.Image;

public class Character {
	
	protected String name;
	public Image sprite;
	
	
	public Character(String CharName) {
		name = CharName;
	}
	
	public String getName() {
		return name;
	}

}
