package heroes;

import javax.swing.ImageIcon;

import heroPower.*;

public class Warrior extends Hero{
	public Warrior(String name,boolean isPlayer) {
		super(name,new WarriorPower(),isPlayer,new ImageIcon("Warrior.png").getImage(),
				new ImageIcon("Cardback1.png").getImage());
	}
}
