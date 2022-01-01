package deathrattle;

import heroes.Hero;

public class DeathDamageEnemyHero extends Deathrattle{
	private int dmg;
	public DeathDamageEnemyHero(Hero hero,int dmg) {
		super(hero);
		this.dmg=dmg;
	}
	@Override
	public void deathrattle() {
		target=hero.getEnemy();
		target.takeDamage(dmg);
	}

}
