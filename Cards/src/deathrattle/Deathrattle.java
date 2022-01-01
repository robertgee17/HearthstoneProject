package deathrattle;

import battlecry.TargetedBattlecry;
import generalClasses.GameCharacter;
import heroes.Hero;

public abstract class Deathrattle implements Cloneable{
	protected Hero hero;
	protected GameCharacter target;
	public Deathrattle(Hero hero) {
		this.hero=hero;
	}
	public abstract void deathrattle();
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public Deathrattle clone() {
		try {
			return (Deathrattle)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}
	public GameCharacter getTarget() {
		return this.target;
	}
}
