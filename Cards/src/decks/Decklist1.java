package decks;
import generalClasses.*;
import battlecry.*;
import deathrattle.*;
import heroes.Hero;
import minions.*;
import spells.*;
//all decklists are just different lists of cards to make a Deck out of
public class Decklist1{
	GameCharacter[] deck=new GameCharacter[30];
	public Decklist1(Hero hero) {
		deck[0]=new Archer(hero);
		deck[1]=new Gnome(hero);
		deck[2]=new Gnome(hero);
		deck[3]=new FlameJuggler(hero);
		deck[4]=new FlameJuggler(hero);
		deck[5]=new Hoarder(hero);
		deck[6]=new Hoarder(hero);
		deck[7]=new Toad(hero);
		deck[8]=new Toad(hero);
		deck[9]=new PotOfGreed(hero);
		deck[10]=new PotOfGreed(hero);
		deck[11]=new Oracle(hero);
		deck[12]=new Oracle(hero);
		deck[13]=new Spider(hero);
		deck[14]=new Spider(hero);
		deck[15]=new Yeti(hero);
		deck[16]=new Yeti(hero);
		deck[17]=new Peacekeeper(hero);
		deck[18]=new Fireball(hero);
		deck[19]=new Assassinate(hero);
		deck[20]=new Brawl(hero);
		deck[21]=new Yogg(hero);
		deck[22]=new Yogg(hero);
		deck[23]=new TidalWave(hero);
		deck[24]=new TidalWave(hero);
		deck[25]=new Marksman(hero);
		deck[26]=new Marksman(hero);
		deck[27]=new DrBoom(hero);
		deck[28]=new TheGeneral(hero);
		deck[29]=new Krush(hero);
		
	}
	public GameCharacter[] getDeck() {
		return this.deck;
	}
}
