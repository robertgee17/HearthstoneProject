package minions;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Spider extends Minion{
	public Spider(Hero hero) {
		super("Spider",3,3,4,hero,
				new ImageIcon("Spider.png").getImage(),
				new ImageIcon("SpiderM.png").getImage());
	}
}