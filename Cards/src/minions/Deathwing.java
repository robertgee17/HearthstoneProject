package minions;

import javax.swing.ImageIcon;

import battlecry.*;
import heroes.Hero;

public class Deathwing extends Minion{

	public Deathwing( Hero hero) {
		super("Deathwing", 10, 12, 12, hero, new DestroyAndDiscard(hero),
				new ImageIcon("Deathwing.png").getImage(),
				new ImageIcon("DeathwingM.png").getImage(),
				false);
	}

}
