package deathrattle;

import heroes.Hero;

public class DrawCard extends Deathrattle{
	public DrawCard(Hero hero) {
		super(hero);
	}
	//this minion makes it's hero draw a card on death
	public void deathrattle() {
		// TODO Auto-generated method stub
		hero.drawCard();
	}
	
}
