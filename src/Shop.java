import java.util.ArrayList;

public class Shop extends Town{
	
	ArrayList<Item> items = new ArrayList<Item>();
	
	Shop(String name, IUIEngine ui){
		super(name, ui);
	}
	
	public String greeting() {
		//Display greeting when entering shop
		return "";
	}
	
	public void buyItem(Player p) {
		//Buy item from shop and put it in player inventory
	}

}
