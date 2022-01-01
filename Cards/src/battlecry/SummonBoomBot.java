package battlecry;

import heroes.Hero;
import minions.*;

public class SummonBoomBot extends GeneralBattlecry{
	public SummonBoomBot(Hero hero) {
		super(hero);//superhero haha
	}
	public void battlecry() {
		Minion m1=new DrBoomBot(hero);
		Minion m2=new DrBoomBot(hero);
		hero.getBoard().add(hero.getBoard().size()-1,m1);
		hero.getBoard().add(m2);
		m1.setInPlay();
		m2.setInPlay();
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}

}
