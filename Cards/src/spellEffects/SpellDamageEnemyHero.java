package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;

public class SpellDamageEnemyHero extends GeneralSpellEffect{
	private int dmg;
	public SpellDamageEnemyHero(int dmg, Hero hero) {
		super(hero);
		this.dmg=dmg;
	}
	public boolean play() {
		hero.getEnemy().takeDamage(dmg);
		hero.getProjectiles().add(newProjectile(hero.getEnemy()));
		return true;
	}
	
}
