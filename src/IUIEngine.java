
public interface IUIEngine {
	
	String getUserInput();
	
	void updateScreen(TileMap world, Player p);
	
	void displayDialogue(String d);
	
	void displayPlayerStats(String d);
	
	void displayEnemyStats(String d);
	
	String movePlayer();
	
	void showBattleScreen();
	
	void updateBattle(Player p, Enemy e);
	
	void endBattle();
	

}
