package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;

public class DealDamage extends TargetedBattlecry{
	private int dmg;
	
	public DealDamage(int dmg,Hero hero) {
		super(hero);
		this.dmg=dmg;
	}
	//deals dmg amount of damage to a target
	public void battlecry(GameCharacter target) {
		target.takeDamage(dmg);
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}
}
