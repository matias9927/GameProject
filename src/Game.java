import java.util.Scanner;

public class Game {
	
	private IUIEngine ui;
	public static int[][] MonsterStats = { 
			/*Jelli*/ {5, 1, 1, 55},
			/*Big Jelli*/ {10, 2, 2, 10},
			/*Batz*/ {8, 3, 1, 15}
	};

	
	public Game(IUIEngine ui) {
		this.ui = ui;
	}
	
	public void startgame() {
		System.out.println("poop");
		String name = ui.getUserInput();
		Player hero = new Player(name);
		Enemy baddie = new Enemy("Jelli", MonsterStats[0]);
		battle(hero, baddie);
	}
	
	public void battle(Player player, Enemy enemy) {
		boolean battling = true;
		int turn = 0;
		String command;
		ui.displayDialogue(String.format("Battle has bebun! %s has attacked!", enemy.getName()));
		
		while(battling) {
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
	}
	
	public static void main(String[] args) {
		Game game = new Game(new TextUIEngine()); 
		game.startgame();
	}

}
