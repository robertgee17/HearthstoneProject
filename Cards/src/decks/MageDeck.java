package decks;

import generalClasses.*;
import heroes.Hero;
import minions.*;
import spells.*;

public class MageDeck {
	Card[] deck=new GameCharacter[30];
	public MageDeck(Hero hero) {
		deck[0]=new Archer(hero);
		deck[1]=new Archer(hero);
		deck[2]=new FlameJuggler(hero);
		deck[3]=new FlameJuggler(hero);
		deck[4]=new Hoarder(hero);
		deck[5]=new Hoarder(hero);
		deck[6]=new Oracle(hero);
		deck[7]=new Oracle(hero);
		deck[8]=new Gargoyle(hero);
		deck[9]=new Gargoyle(hero);
		deck[10]=new Peacekeeper(hero);
		deck[11]=new Yeti(hero);
		deck[12]=new Yeti(hero);
		deck[13]=new Yogg(hero);
		deck[14]=new Shieldmasta(hero);
		deck[15]=new Shieldmasta(hero);
		deck[16]=new Marksman(hero);
		deck[17]=new Marksman(hero);
		deck[18]=new DrBoom(hero);
		deck[19]=new Deathwing(hero);
		deck[20]=new PotOfGreed(hero);
		deck[21]=new PotOfGreed(hero);
		deck[22]=new HolyLight(hero);
		deck[23]=new Fireball(hero);
		deck[24]=new Fireball(hero);
		deck[25]=new TidalWave(hero);
		deck[26]=new TidalWave(hero);
		deck[27]=new MindControl(hero);
		deck[28]=new FirePotion(hero);
		deck[29]=new Brawl(hero);
		
	}
	public Card[] getDeck() {
		return this.deck;
	}
}
