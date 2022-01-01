package minions;

import javax.swing.ImageIcon;

import battlecry.GiveHealth;
import heroes.Hero;

public class Gargoyle extends TargettedBattlecryMinion{
	public Gargoyle(Hero hero) {
		super("Gargoyle",3,2,4,hero,new GiveHealth(3,hero),
				new ImageIcon("Gargoyle.png").getImage(),
				new ImageIcon("GargoyleM.png").getImage());
	}
}