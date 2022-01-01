package spellEffects;

import generalClasses.GameCharacter;
import heroes.Hero;

public class SpellDrawCard extends GeneralSpellEffect{
	private int numCards;
	public SpellDrawCard(int numCards,Hero hero) {
		super(hero);
		this.numCards=numCards;
	}
	public boolean play() {
		hero.drawCard(numCards);
		return true;
	}
}