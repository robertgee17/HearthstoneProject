package minions;

import java.awt.Image;

import javax.swing.ImageIcon;

import battlecry.*;
import heroes.Hero;

public class TheGeneral extends Minion{

	public TheGeneral(Hero hero) {
		super("The General", 8, 4, 7, hero, new BuffAllyMinions(1,2,hero),
				new ImageIcon("TheGeneral.png").getImage(), 
				new ImageIcon("TheGeneralM.png").getImage(), 
				false);
		// TODO Auto-generated constructor stub
	}

}
