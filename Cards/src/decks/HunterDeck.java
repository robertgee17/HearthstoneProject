package decks;

import generalClasses.*;
import heroes.Hero;
import minions.*;
import spells.*;

public class HunterDeck {
	Card[] deck=new GameCharacter[30];
	public HunterDeck(Hero hero) {
		deck[0]=new Archer(hero);
		deck[1]=new Gnome(hero);
		deck[2]=new Gnome(hero);
		deck[3]=new FlameJuggler(hero);
		deck[4]=new FlameJuggler(hero);
		deck[5]=new Toad(hero);
		deck[6]=new Toad(hero);
		deck[7]=new Spider(hero);
		deck[8]=new Spider(hero);
		deck[9]=new Gargoyle(hero);
		deck[10]=new Gargoyle(hero);
		deck[11]=new Yeti(hero);
		deck[12]=new Jouster(hero);
		deck[13]=new Jouster(hero);
		deck[14]=new WindRider(hero);
		deck[15]=new Yogg(hero);
		deck[16]=new Marksman(hero);
		deck[17]=new Marksman(hero);
		deck[18]=new Ogre(hero);
		deck[19]=new Ogre(hero);
		deck[20]=new DrBoom(hero);
		deck[21]=new TheGeneral(hero);
		deck[22]=new TheGeneral(hero);
		deck[23]=new Krush(hero);
		deck[24]=new PotOfGreed(hero);
		deck[25]=new PotOfGreed(hero);
		deck[26]=new HolyLight(hero);
		deck[27]=new Fireball(hero);
		deck[28]=new Fireball(hero);
		deck[29]=new Assassinate(hero);
		
	}
	public Card[] getDeck() {
		return this.deck;
	}
}
