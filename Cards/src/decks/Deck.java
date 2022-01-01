package decks;
import java.util.ArrayList;
import java.util.Arrays;

import generalClasses.*;

public class Deck extends ArrayList<Card>{
	public Deck(Card[] decklist) {
		for(int i=0;i<30;i++) {
			this.add(decklist[i]);
		}
		shuffle();
	}
	//randomize card order
	public void shuffle() {
		int size=this.size();
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int i=0;i<size;i++) {
			int x=(int)(Math.random()*this.size());
			temp.add(this.remove(x));
		}
		for(int i=0;i<size;i++) {
			this.add(temp.get(i));
		}		
	}
	//add a card at random slot in deck
	public void shuffleIn(Card card) {
		if(this.size()==0)
			this.add(card);
		else {
			this.add((int)(Math.random()*(size())),card);
		}
	}
}
