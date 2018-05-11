import java.util.ArrayList;

public interface IUIEngine {
	
	String getUserInput();
	
	String getCombatInput();
	
	void updateScreen(TileMap world, Player p, ArrayList<Boss> objects);
	
	void displayDialogue(String d);
	
	void displayPlayerStats(String d);
	
	void displayEnemyStats(String d);
	
	String movePlayer();
	
	void showBattleScreen();
	
	void updateBattle(Player p, Enemy e);
	
	void endBattle();
	

}
