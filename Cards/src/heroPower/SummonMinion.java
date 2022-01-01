package heroPower;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.*;

public class SummonMinion implements GeneralHeroPower{
	private Hero hero;
	public void addHero(Hero hero) {
		this.hero=hero;
	}
	//deals dmg amount of damage to a target
	public boolean heroPower() {
		Minion m=new Recruit(hero);
		m.setInPlay();
		hero.getBoard().add(m);
		return true;
	}
	@Override
	public GameCharacter getTarget() {
		// TODO Auto-generated method stub
		return null;
	}
}
