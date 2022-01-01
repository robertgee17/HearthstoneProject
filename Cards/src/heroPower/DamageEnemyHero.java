package heroPower;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.*;

public class DamageEnemyHero implements GeneralHeroPower{
	private int dmg;
	private Hero hero;
	public DamageEnemyHero(int dmg) {
		this.dmg=dmg;
	}
	public void addHero(Hero hero) {
		this.hero=hero;
	}
	//deals dmg amount of damage to a target
	public boolean heroPower() {
		hero.getEnemy().takeDamage(dmg);
		return true;
	}
	@Override
	public GameCharacter getTarget() {
		return hero.getEnemy();
	}
}
