package heroPower;

import generalClasses.GameCharacter;
import heroes.Hero;

public class DealDamage implements TargetedHeroPower{
	private int dmg;
	private Hero hero;
	public DealDamage(int dmg) {
		this.dmg=dmg;
	}
	public void addHero(Hero hero) {
		this.hero=hero;
	}
	//deals dmg amount of damage to a target
	public boolean heroPower(GameCharacter target) {
		target.takeDamage(dmg);
		return true;
	}
	
}
