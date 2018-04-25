
public class Battle {
	
	Player player;
	Enemy enemy;
	IUIEngine ui;
	
	public Battle(Player p, Enemy e, IUIEngine ui) {
		player = p;
		enemy = e;
		this.ui = ui;
		ui.showBattleScreen();
	}
	
	public void battle(Player player, Enemy enemy) {
		boolean battling = true;
		int turn = 0;
		String command;
		ui.displayDialogue(String.format("Battle has begun! %s has attacked!", enemy.getName()));
		
		while(battling) {
			ui.updateBattle(player, enemy);
			turn += 1;
			ui.displayDialogue(String.format("\nTurn: %d\n", turn));
			ui.displayDialogue(player.getCombatInfo());
			ui.displayDialogue(enemy.getCombatInfo());
			command = ui.getUserInput();
			if(command.equals("attack")) {
				player.dealDamage(enemy);
				if(enemy.HP == 0) {
					ui.displayDialogue(String.format("Battle end. %s wins!\n", player.getName()));
					ui.displayDialogue(String.format("%s gets %d experience!\n", player.getName(), enemy.experience));
					player.experience += enemy.experience;
					player.levelUp();
					battling = false;
					return;
				}
			}
			enemy.dealDamage(player);
			if(player.HP == 0) {
				ui.displayDialogue("Battle end. Game over...");
				battling = false;
			}
		}
		enemy.HP = enemy.maxHP;
	}

}
