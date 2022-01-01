package decks;

import generalClasses.*;
import heroes.Hero;
import spells.*;
import minions.*;

public class Decklist3 {
	GameCharacter[] deck=new GameCharacter[30];
	public Decklist3(Hero hero) {
		for(int i=0;i<30;i++) {
			deck[i]=new Jouster(hero);
		}
	}
	public GameCharacter[] getDeck() {
		return this.deck;
	}
}
