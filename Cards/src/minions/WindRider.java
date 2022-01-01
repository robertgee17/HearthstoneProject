package minions;

import javax.swing.ImageIcon;

import heroes.Hero;

public class WindRider extends WindfuryMinion{
	public WindRider(Hero hero) {
		super("Wind Rider",4,3,4,hero,
				new ImageIcon("WindRider.png").getImage(),
				new ImageIcon("WindRiderM.png").getImage());
	}
}