package minions;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Recruit extends Minion{
	public Recruit(Hero hero) {
		super("Recruit",1,1,1,hero,
				new ImageIcon("Recruit.png").getImage(),
				new ImageIcon("RecruitM.png").getImage());
	}
}
