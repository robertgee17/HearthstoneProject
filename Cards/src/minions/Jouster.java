package minions;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Jouster extends ChargeMinion{
	public Jouster(Hero hero) {
		super("Jouster", 4, 4, 3, hero, 
				new ImageIcon("Jouster.png").getImage(),
				new ImageIcon("JousterM.png").getImage());
		// TODO Auto-generated constructor stub
	}
}
