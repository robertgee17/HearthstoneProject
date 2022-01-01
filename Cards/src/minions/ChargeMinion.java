package minions;

import java.awt.Image;

import battlecry.TargetedBattlecry;
import heroes.Hero;

public class ChargeMinion extends Minion{
	public ChargeMinion(String name, int mana, int attack, int health, Hero hero,
			Image image, Image boardImg) {
		super(name, mana, attack, health, hero, image, boardImg);
		// TODO Auto-generated constructor stub
		this.summoningSickness=false;
	}
	@Override
	public void setSummoningSickness(boolean ss) {
		this.summoningSickness=false;
	}
}
