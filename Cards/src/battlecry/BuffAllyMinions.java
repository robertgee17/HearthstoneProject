package battlecry;

import heroes.Hero;

public class BuffAllyMinions extends GeneralBattlecry{
	private int attack;
	private int health;

	public BuffAllyMinions(int attack,int health,Hero hero) {
		super(hero);
		this.attack=attack;
		this.health=health;
	}
	//deals dmg amount of damage to a random enemy
	public void battlecry() {
		for(int i=0;i<hero.getBoard().size()-1;i++) {
			hero.getBoard().get(i).addStats(attack, health);
		}
	}
}
