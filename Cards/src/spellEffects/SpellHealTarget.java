package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;

public class SpellHealTarget extends TargetedSpellEffect{
	private int heal;
	public SpellHealTarget(int heal,Hero hero) {
		super(hero);
		this.heal=heal;
	}
	public boolean play(GameCharacter target) {
		target.heal(heal);
		hero.getProjectiles().add(newProjectile(target));
		return true;
	}
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}
}