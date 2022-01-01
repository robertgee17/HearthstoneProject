package decks;

import generalClasses.*;
import heroes.Hero;
import minions.*;
import spells.*;

public class WarriorDeck {
	Card[] deck=new GameCharacter[30];
	public WarriorDeck(Hero hero) {
		deck[0]=new Archer(hero);
		deck[1]=new Archer(hero);
		deck[2]=new DrBoomBot(hero);
		deck[3]=new FlameJuggler(hero);
		deck[4]=new FlameJuggler(hero);
		deck[5]=new Hoarder(hero);
		deck[6]=new Hoarder(hero);
		deck[7]=new Oracle(hero);
		deck[8]=new Oracle(hero);
		deck[9]=new Gargoyle(hero);
		deck[10]=new Gargoyle(hero);
		deck[11]=new Peacekeeper(hero);
		deck[12]=new Peacekeeper(hero);
		deck[13]=new Yeti(hero);
		deck[14]=new Yeti(hero);
		deck[15]=new Yogg(hero);
		deck[16]=new Shieldmasta(hero);
		deck[17]=new Shieldmasta(hero);
		deck[18]=new Marksman(hero);
		deck[19]=new Marksman(hero);
		deck[20]=new DrBoom(hero);
		deck[21]=new Deathwing(hero);
		deck[22]=new PotOfGreed(hero);
		deck[23]=new PotOfGreed(hero);
		deck[24]=new HolyLight(hero);
		deck[25]=new Assassinate(hero);
		deck[26]=new Brawl(hero);
		deck[27]=new Brawl(hero);
		deck[28]=new TidalWave(hero);
		deck[29]=new MindControl(hero);
		
	}
	public Card[] getDeck() {
		return this.deck;
	}
}
