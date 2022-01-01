package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;

public class TakeControlOfMinion extends TargetedSpellEffect{
	public TakeControlOfMinion(Hero hero) {
		super(hero);
	}
	public boolean play(GameCharacter target) {
		hero.getProjectiles().add(newProjectile(target));
		hero.getEnemy().getBoard().remove(target);
		target.setHero(hero);
		hero.getBoard().add((Minion)target);
		((Minion)target).setSummoningSickness(true);
		((Minion)target).resetAttacks();
		return true;
	}
	public boolean validTarget(GameCharacter target) {
		return target instanceof Minion&&hero.getEnemy().getBoard().contains(target);
	}
}