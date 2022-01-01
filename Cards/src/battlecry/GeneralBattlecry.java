package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;

public abstract class GeneralBattlecry implements Battlecry,Cloneable{
	protected Hero hero;
	protected GameCharacter target;
	public GeneralBattlecry(Hero hero) {
		this.hero=hero;
	}
	public abstract void battlecry();
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public GeneralBattlecry clone() {
		try {
			return (GeneralBattlecry)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}
	public GameCharacter getTarget() {
		return this.target;
	}
	
}
