package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;
import spellEffects.DestroyMinion;
import spellEffects.TargetedSpellCard;

public class Assassinate extends Spell implements TargetedSpellCard{
	public Assassinate(Hero hero) {
		super("Assassinate",5, hero,
				new DestroyMinion(hero),
				new ImageIcon("Assassinate.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return target instanceof Minion;
	}
}