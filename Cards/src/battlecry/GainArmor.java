package battlecry;

import heroes.Hero;

public class GainArmor extends GeneralBattlecry{
	private int amt;
	public GainArmor(int amt,Hero hero) {
		super(hero);
		this.amt=amt;
	}
	//deals dmg amount of damage to a random enemy
	public void battlecry() {
		hero.addArmor(amt);
	}
}
