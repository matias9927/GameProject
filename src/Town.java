import java.awt.Image;
import java.util.ArrayList;

public class Town extends Location{
	
	public Image sprite;
	public ArrayList<PassiveCharacter> npcs = new ArrayList<PassiveCharacter>();
	
	Town(String name, IUIEngine ui){
		super(name);
	}
	
	public void action() {
		//Takes input from player to interact with objects in the town
	}
	

}
