package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;

public abstract class TargetedBattlecry implements Battlecry,Cloneable{
	protected Hero hero;
	public TargetedBattlecry(Hero hero) {
		this.hero=hero;
	}
	public abstract void battlecry(GameCharacter target);
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public TargetedBattlecry clone() {
		try {
			return (TargetedBattlecry)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}
	public abstract boolean validTarget(GameCharacter target);
}
