package minions;

import java.awt.Image;

import javax.swing.ImageIcon;

import deathrattle.DeathDamage;
import heroes.Hero;

public class DrBoomBot extends Minion{

	public DrBoomBot(Hero hero) {
		super("Boom Bot", 1, 1, 1, hero, 
				new DeathDamage(hero,1,4),
				new ImageIcon("DrBoomBot.png").getImage(),
				new ImageIcon("DrBoomBotM.png").getImage(),
				true);
		// TODO Auto-generated constructor stub
	}

}
