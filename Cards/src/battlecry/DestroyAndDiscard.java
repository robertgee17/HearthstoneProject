package battlecry;

import heroes.Hero;

public class DestroyAndDiscard extends GeneralBattlecry{


	public DestroyAndDiscard(Hero hero) {
		super(hero);
	}
	//deals dmg amount of damage to a random enemy
	public void battlecry() {
		for(int i=0;i<hero.getEnemy().getBoard().size();i++) {
			hero.getEnemy().getBoard().get(i).setToKill();
		}
		for(int i=0;i<hero.getBoard().size()-1;i++) {
			hero.getBoard().get(i).setToKill();
		}
		for(int i=0;i<hero.getHand().size();i++) {
			hero.getHand().remove(i);
			i--;
		}
	}
}

