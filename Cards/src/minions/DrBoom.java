package minions;

import java.awt.Image;

import javax.swing.ImageIcon;

import battlecry.*;
import heroes.Hero;

public class DrBoom extends Minion{

	public DrBoom(Hero hero) {
		super("Dr. Boom", 7, 7, 7, hero, 
				new SummonBoomBot(hero),
				new ImageIcon("DrBoom.png").getImage(),
				new ImageIcon("DrBoomM.png").getImage(),
				false);
	}
	public void setHero(Hero hero) {
		super.setHero(hero);
	}
	public Hero getHero() {
		return this.hero;
	}
}
