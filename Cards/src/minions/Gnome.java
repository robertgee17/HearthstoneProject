package minions;

import javax.swing.ImageIcon;

import deathrattle.DeathDamageEnemyHero;
import heroes.Hero;

public class Gnome extends Minion{

	public Gnome(Hero hero) {
		super("Gnome", 1, 2, 1, hero, 
				new DeathDamageEnemyHero(hero,2),
				new ImageIcon("Gnome.png").getImage(),
				new ImageIcon("GnomeM.png").getImage(),
				true);
		// TODO Auto-generated constructor stub
	}

}