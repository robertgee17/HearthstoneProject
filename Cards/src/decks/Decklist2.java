package decks;

import generalClasses.*;
import generalClasses.GameCharacter;
import heroes.Hero;
import spells.*;
import minions.*;

public class Decklist2 {
	GameCharacter[] deck=new GameCharacter[30];
	public Decklist2(Hero hero) {
		for(int i=0;i<30;i++) {
			deck[i]=new FlameJuggler(hero);
		}
	}
	public GameCharacter[] getDeck() {
		return this.deck;
	}
}
