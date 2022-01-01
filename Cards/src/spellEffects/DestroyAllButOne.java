package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;

public class DestroyAllButOne extends GeneralSpellEffect{
	public DestroyAllButOne(Hero hero) {
		super(hero);
	}
	public boolean play() {
		Minion m=hero.getRandomMinion();
		for(Minion m2:hero.getBoard()) {
			if(!m2.equals(m))
				m2.setToKill();
		}
		for(Minion m2:hero.getEnemy().getBoard()) {
			if(!m2.equals(m))
				m2.setToKill();
		}
		return true;
	}
}