/* CombatCharacter.java
 * Matias Saavedra Silva and Johnny Pabst
 * Details the properties associated with characters capable of
 * engaging in battle
 */
public class CombatCharacter extends Character{
	
	protected int maxHP;
	public int HP;
	protected int maxMP;
	protected int MP;
	protected int attack;
	protected int defense;
	protected int experience;
	protected int gold;
	protected int level;
	
	//Takes a name and sets default values to their stats
	CombatCharacter(String name){
		super(name);
		maxHP = 5;
		HP = maxHP;
		maxMP = 5;
		MP = maxMP;
		attack = 1;
		defense = 1;
		
		level = 1;
		gold = 50;
		experience = 0;
	}
	
	//Returns the character's stats as a formatted string 
	public String getStats() {
		return String.format("HP: %d/%d\nMP: %d/%d\nATK: %d\nDEF: %d\n", HP, maxHP, MP, maxMP, attack, defense);
	}
	
	//Returns the character's name and stats formatted together to be displayed in combat
	public String getCombatInfo() {
		return String.format("%s\n%s", getName(), getStats());
	}
	
	/* Takes a damage value and lowers the character's HP by that amount
	 * and returns a string displaying the dialogue for taking damage
	 */
	public String takeDamage(int damage) {
		HP -= damage;
		if(HP <= 0) {
			HP = 0;
			System.out.printf("%s is slain!\n", name);
		}
		return String.format("%s takes %d damage!\n",name, damage);
	}
	
	/* Takes a target that is another CombatCharacter and 
	 * Calculates the damage dealt to the target and returns a string
	 * with the associated dialogue
	 */
	public String dealDamage(CombatCharacter target) {
		int damage = 0;
		if(attack - target.defense < 0) {
			damage = 0;
		}
		else {
			damage = attack - target.defense;
		}
		return String.format("%s attacks %s!\n", name, target.getName()) + target.takeDamage(damage);
	}
	
	//Returns a formatted string with the dialogue of the character attacking the target
	public String attackMessage(CombatCharacter target) {
		return String.format("%s attacks %s!\n", name, target.getName());
	}
	
	//Returns string with dialogue for character receiving damage
	public String damageMessage(int damage) {
		return String.format("%s takes %d damage!\n",name, damage);
	}

}
