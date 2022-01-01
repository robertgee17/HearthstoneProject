package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;

public class HealTarget extends TargetedBattlecry{
	private int heal;
	public HealTarget(int amt,Hero hero) {
		super(hero);
		heal=amt;
	}
	@Override
	public void battlecry(GameCharacter target) {
		target.heal(heal);
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}

}
