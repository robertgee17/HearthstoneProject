package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class FirePotion extends Spell{
	public FirePotion(Hero hero) {
		super("FirePotion",3,hero,
				new DamageAllMinions(2,hero),
				new ImageIcon("FirePotion.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return true;
	}
}
