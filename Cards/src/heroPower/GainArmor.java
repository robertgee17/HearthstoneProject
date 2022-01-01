package heroPower;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;
import minions.Recruit;

public class GainArmor implements GeneralHeroPower{
	private Hero hero;
	int armor;
	public GainArmor(int armor) {
		this.armor=armor;
	}
	public void addHero(Hero hero) {
		this.hero=hero;
	}
	//deals dmg amount of damage to a target
	public boolean heroPower() {
		hero.addArmor(armor);
		return true;
	}
	@Override
	public GameCharacter getTarget() {
		// TODO Auto-generated method stub
		return null;
	}
}
