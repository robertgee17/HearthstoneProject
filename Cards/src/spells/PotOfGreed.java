package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.SpellDrawCard;

public class PotOfGreed extends Spell{
	public PotOfGreed(Hero hero) {
		super("PotOfGreed",3,hero,
				new SpellDrawCard(2,hero),
				new ImageIcon("PotOfGreed.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return true;
	}
}