package deathrattle;

import heroes.Hero;

public class DeathDamage extends Deathrattle{
	private int min;
	private int max;
	public DeathDamage(Hero hero,int min,int max) {
		super(hero);
		this.min=min;
		this.max=max;
	}
	@Override
	public void deathrattle() {
		do {
			target=hero.getRandomEnemy();
		}while(target.getHealth()<=0);
		target.takeDamage((int)(Math.random()*(max-min+1)+min));
	}

}
