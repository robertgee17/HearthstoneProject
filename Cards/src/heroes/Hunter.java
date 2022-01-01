package heroes;

import javax.swing.ImageIcon;

import heroPower.*;

public class Hunter extends Hero{
	public Hunter(String name,boolean isPlayer) {
		super(name,new HunterPower(),isPlayer,new ImageIcon("Hunter.png").getImage(),
				new ImageIcon("Cardback1.png").getImage());
	}
}
