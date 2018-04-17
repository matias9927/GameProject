
public class Consumable extends Item{
	
	Consumable(String name, String description, int effect){
		super(name, description);
	}
	
	public void heal(Player p) {
		//Health potion heals some HP by value of "effect"
	}
	
	public void attackUp(Player p) {
		//Increase attack by value "effect"
	}
	
	public void defesneUp(Player p) {
		//Increase defense by value "effect"
	}

}
