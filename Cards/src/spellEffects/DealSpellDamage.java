package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;

public class DealSpellDamage extends TargetedSpellEffect{
	private int dmg;
	public DealSpellDamage(int dmg,Hero hero) {
		super(hero);
		this.dmg=dmg;
	}
	public boolean play(GameCharacter target) {
		hero.getProjectiles().add(newProjectile(target));
		target.takeDamage(dmg+hero.getSpellDamage());
		return true;
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}
}
