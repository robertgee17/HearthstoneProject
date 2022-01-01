package minions;

import javax.swing.ImageIcon;

import deathrattle.DrawCard;
import heroes.Hero;

public class Hoarder extends Minion{
	public Hoarder(Hero hero) {
		super("Hoarder",2,2,1,hero,new DrawCard(hero),
				new ImageIcon("Hoarder.png").getImage(),
				new ImageIcon("HoarderM.png").getImage(),
				false);
	}
}
