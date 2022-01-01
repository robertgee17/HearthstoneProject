package heroes;
import java.awt.Graphics;
import heroPower.*;

import javax.swing.ImageIcon;

import heroPower.TargetedHeroPower;

public class Mage extends Hero{
	//hero power is deal 1 damage to target
	public Mage(String name,boolean isPlayer) {
		super(name,new MagePower(),isPlayer,new ImageIcon("Mage.png").getImage(),
				new ImageIcon("Cardback1.png").getImage());
	}
}
