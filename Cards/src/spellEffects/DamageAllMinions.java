package spellEffects;

import generalClasses.GameCharacter;
import heroes.*;

public class DamageAllMinions extends GeneralSpellEffect{
	private int dmg;
	public DamageAllMinions(int dmg,Hero hero) {
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
		for(int i=0;i<hero.getBoard().size();i++) {
			GameCharacter save=hero.getBoard().get(i);
			save.takeDamage(dmg);
			hero.getProjectiles().add(this.newProjectile(save));
			if(!save.isAlive()) {
				i--;
			}
		}
		return true;
	}
}
