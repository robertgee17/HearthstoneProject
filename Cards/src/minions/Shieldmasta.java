package minions;

import javax.swing.ImageIcon;

import battlecry.GainArmor;
import heroes.Hero;

public class Shieldmasta extends Minion{

	public Shieldmasta( Hero hero) {
		super("Shieldmasta", 5, 4, 4, hero, new GainArmor(4,hero),
				new ImageIcon("Shieldmasta.png").getImage(),
				new ImageIcon("ShieldmastaM.png").getImage(),
				false);
	}

}
