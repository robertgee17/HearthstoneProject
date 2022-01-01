package howToPlay;

import java.awt.Graphics;

public class Rules extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Rules", 850, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Board:   Each player has their own board which you can play minions onto.",50,50);
		g.drawString("         Each board can hold up to 7 minions.",50,100);
		g.drawString("Hand:   Each player has a hand, holding a maximum of 10 cards.",50,150);
		g.drawString("        If a card is drawn when your hand is full, that card is discarded.",50,200);
		g.drawString("Deck:   Each player starts with a deck of 30 cards.",50,250);
		g.drawString("Fatigue:   Once you run out of cards in your deck, drawing a card will deal (1 + number of turns you’ve been fatigued) damage to your hero.",50,300);
		g.drawString("Mana:   All cards have a mana cost.", 50, 350);
		g.drawString("        You gain 1 maximum mana at the start of each turn.", 50, 400);
		g.drawString("        You cannot have more than 10 mana.", 50, 450);
		g.drawString("Minions:   A minion can only attack once per turn.", 50, 500);
		g.drawString("        A minion can not attack the turn it is put onto the board.", 50, 550);
		g.drawString("Healing:   A character cannot be healed over it's maximum health.", 50, 600);
	}
}
