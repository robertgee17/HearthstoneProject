package minions;

import javax.swing.ImageIcon;

import battlecry.*;
import heroes.Hero;

public class FlameJuggler extends Minion{

	public FlameJuggler( Hero hero) {
		super("Flame Juggler", 2, 2, 3, hero, new RandomDealDamage(1,hero),
				new ImageIcon("FlameJuggler.png").getImage(),
				new ImageIcon("FlameJugglerM.png").getImage(),
				true);
	}

}
