package battlecry;

import heroes.Hero;

public class CastRandomSpell extends GeneralBattlecry{
	public CastRandomSpell(Hero hero) {
		super(hero);
	}
	//deals dmg amount of damage to a random enemy
	public void battlecry() {
		if(hero.getUsedSpells().size()>0) {
			int x=(int)(Math.random()*hero.getUsedSpells().size());
			hero.getUsedSpells().get(x).yoggPlay();
		}
	}
}
