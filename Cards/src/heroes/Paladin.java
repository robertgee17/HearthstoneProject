package heroes;

import javax.swing.ImageIcon;

import heroPower.DamageEnemyHero;
import heroPower.HunterPower;
import heroPower.PaladinPower;
import heroPower.SummonMinion;

public class Paladin extends Hero{
	public Paladin(String name,boolean isPlayer) {
		super(name,new PaladinPower(),isPlayer,new ImageIcon("Paladin.png").getImage(),
				new ImageIcon("Cardback1.png").getImage());
	}
}
