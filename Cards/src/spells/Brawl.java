package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.DestroyAllButOne;

public class Brawl extends Spell{
	public Brawl(Hero hero) {
		super("Brawl",5,hero,
				new DestroyAllButOne(hero),
				new ImageIcon("Brawl.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return true;
	}
}