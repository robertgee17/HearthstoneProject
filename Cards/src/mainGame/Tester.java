package mainGame;
import java.util.Arrays;

import decks.Decklist1;
import heroes.*;
import minions.*;
import spellEffects.*;

public class Tester {
	public static Mage a=new Mage("a",true);
	public static Mage b=new Mage("b",false);
	public static void main(String[] args) {
		initialize();
		a.drawCard(10);
		
			System.out.println(Arrays.toString((a.getHand().toArray())));
			//System.out.println(Arrays.toString((b.getHand().toArray())));
		
		
	}
	public static void initialize() {
		a.addEnemy(b);
		b.addEnemy(a);
		a.addDeck(new Decklist1(a).getDeck());
		b.addDeck(new Decklist1(b).getDeck());
	}

}
