
public interface IUIEngine {
	
	String getUserInput();
	
	void updateScreen(TileMap world, Player p);
	
	void displayDialogue(String d);
	
	String movePlayer();
	
	void showBattleScreen();
	
	void updateBattle(Player p, Enemy e);
	

}
