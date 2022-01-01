package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;

public class DestroyMinion extends TargetedSpellEffect{
	public DestroyMinion(Hero hero) {
		super(hero);
	}
	public boolean play(GameCharacter target) {
		hero.getProjectiles().add(newProjectile(target));
		((Minion)target).setToKill();
		return true;
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target instanceof Minion;
	}
}
