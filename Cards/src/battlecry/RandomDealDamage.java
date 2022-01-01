package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;

public class RandomDealDamage extends GeneralBattlecry{
	private int dmg;
	
	public RandomDealDamage(int dmg,Hero hero) {
		super(hero);
		this.dmg=dmg;
		
	}
	//deals dmg amount of damage to a random enemy
	public void battlecry() {
		target=hero.getRandomEnemy();
		target.takeDamage(dmg);
	}
}
