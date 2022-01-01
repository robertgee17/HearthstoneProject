package minions;

import java.awt.Image;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Krush extends ChargeMinion{

	public Krush(Hero hero) {
		super("Krush", 9, 8, 8, hero, 
				new ImageIcon("Krush.png").getImage(),
				new ImageIcon("KrushM.png").getImage());
		// TODO Auto-generated constructor stub
	}

}
