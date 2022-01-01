package minions;

import java.awt.Image;

import javax.swing.ImageIcon;

import battlecry.CastRandomSpell;
import heroes.Hero;

public class Yogg extends Minion{

	public Yogg(Hero hero) {
		super("Yogg", 5, 3, 5, hero, new CastRandomSpell(hero), 
				new ImageIcon("Yogg.png").getImage(),
				new ImageIcon("YoggM.png").getImage(),
				false);
		// TODO Auto-generated constructor stub
	}

}
