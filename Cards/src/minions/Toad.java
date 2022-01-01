package minions;

import javax.swing.ImageIcon;

import deathrattle.DeathDamage;
import heroes.Hero;

public class Toad extends Minion{

	public Toad(Hero hero) {
		super("Toad", 2, 3, 2, hero, 
				new DeathDamage(hero,1,1),
				new ImageIcon("Toad.png").getImage(),
				new ImageIcon("ToadM.png").getImage(),
				true);
		// TODO Auto-generated constructor stub
	}

}