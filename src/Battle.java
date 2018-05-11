import java.util.concurrent.TimeUnit;

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
	
	private void wait(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//
		}
	}
	
	public void battle(Player player, Enemy enemy) {
		boolean battling = true;
		int turn = 0;
		
		Sound.soundPlay("src\\Sound\\GS_Rivial.mid");
		String command;
		ui.updateBattle(player, enemy);
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
					ui.displayDialogue(player.levelUp());
					battling = false;
					wait(3);
					break;
					//return;
				}
			}
			ui.displayDialogue(enemy.dealDamage(player));
			ui.updateBattle(player, enemy);
			wait(1);
			if(player.HP == 0) {
				ui.displayDialogue("Battle end. Game over...");
				battling = false;
			}
		}
		enemy.HP = enemy.maxHP;
		ui.endBattle();
	}

}
