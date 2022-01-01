package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;

public class DamageAllEnemies extends GeneralSpellEffect{
	private int dmg;
	public DamageAllEnemies(int dmg,Hero hero) {
		super(hero);
		this.dmg=dmg;
	}
	public boolean play() {
		for(int i=0;i<hero.getEnemy().getBoard().size();i++) {
			GameCharacter save=hero.getEnemy().getBoard().get(i);
			save.takeDamage(dmg);
			hero.getProjectiles().add(this.newProjectile(save));
			if(!save.isAlive()) {
				i--;
			}
		}
		hero.getEnemy().takeDamage(dmg);
		hero.getProjectiles().add(this.newProjectile(hero.getEnemy()));
		return true;
	}
}
