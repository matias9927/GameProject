/* Battle.java
 * Matias Saavedra Silva and Johnny Pabst
 * Details the battle system where the player and an enemy take turns dealing
 * damage until one is defeated
 */
import java.util.concurrent.TimeUnit;

public class Battle {
	
	Player player;
	Enemy enemy;
	IUIEngine ui;
	
	/* Takes a player, enemy, and instance if IUIEngine
	 * Shows the battle screen before the player inputs any actions 
	 */
	public Battle(Player p, Enemy e, IUIEngine ui) {
		player = p;
		enemy = e;
		this.ui = ui;
		ui.showBattleScreen();
	}
	
	//Takes the time in seconds and waits for the input time
	private void wait(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//
		}
	}
	
	/* Takes the player and an enemy
	 * Draws the battle screen and goes through the battle loop
	 */
	public void battle(Player player, Enemy enemy) {
		boolean battling = true;
		int turn = 0;
		
		String command;
		ui.updateBattle(player, enemy); //Draw battle screen before player input
		ui.displayDialogue(String.format("Battle has begun! %s has attacked!", enemy.getName()));
		wait(1);
		
		while(battling) {
			turn += 1;
			ui.displayDialogue(String.format("Turn: %d\n", turn));
			command = ui.getCombatInput();
			if(command.equals("attack")) {
				ui.displayDialogue(player.dealDamage(enemy));
				ui.updateBattle(player, enemy);
				wait(1);
				if(enemy.HP == 0) {
					ui.displayDialogue(String.format("Battle end. %s wins!\n", player.getName()));
					wait(1);
					ui.displayDialogue(String.format("%s gets %d experience!\n", player.getName(), enemy.experience));
					wait(1);
					player.experience += enemy.experience;
					ui.displayDialogue(player.levelUp()); //Level up if player has enough experience, if not continue normally
					battling = false;
					wait(3);
					break;
				}
			}
			ui.displayDialogue(enemy.dealDamage(player)); //Enemy attacks player
			ui.updateBattle(player, enemy);
			wait(1);
			if(player.HP == 0) {
				ui.displayDialogue("Battle end. Game over...");
				battling = false;
			}
		}
		enemy.HP = enemy.maxHP; //Restore enemy HP in case of another encounter
		ui.endBattle();
	}

}
