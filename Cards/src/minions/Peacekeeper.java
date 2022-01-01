package minions;

import javax.swing.ImageIcon;

import battlecry.*;
import heroes.Hero;

public class Peacekeeper extends TargettedBattlecryMinion{
	public Peacekeeper(Hero hero) {
		super("Peacekeeper",4,1,1,hero,new SetMinionAttackHealth(1,1,hero),
				new ImageIcon("Peacekeeper.png").getImage(),
				new ImageIcon("PeacekeeperM.png").getImage());
	}
}