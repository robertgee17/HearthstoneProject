package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;

public class GiveHealth extends TargetedBattlecry{
	private int health;
	
	public GiveHealth(int health,Hero hero) {
		super(hero);
		this.health=health;
	}
	//deals dmg amount of damage to a target
	public void battlecry(GameCharacter target) {
		((Minion)target).addHP(health);
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay()&& target instanceof Minion&&hero.getBoard().contains(target);
	}
}