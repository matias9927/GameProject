
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
	
	public String getStats() {
		return String.format("HP: %d/%d\nMP: %d/%d\nATK: %d\nDEF: %d\n", HP, maxHP, MP, maxMP, attack, defense);
	}
	
	public String getCombatInfo() {
		return String.format("%s\n%s", getName(), getStats());
	}
	
	public void takeDamage(int damage) {
		System.out.printf("%s takes %d damage!\n",name, damage);
		HP -= damage;
		if(HP <= 0) {
			HP = 0;
			System.out.printf("%s is slain!\n", name);
		}
	}
	
	public void dealDamage(CombatCharacter target) {
		int damage = 0;
		System.out.printf("%s attacks %s!\n", name, target.getName());
		if(attack - target.defense < 0) {
			damage = 0;
		}
		else {
			damage = attack - target.defense;
		}
		target.takeDamage(damage);
	}

}
