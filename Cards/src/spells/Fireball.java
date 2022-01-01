package spells;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class Fireball extends Spell implements TargetedSpellCard{
	public Fireball(Hero hero) {
		super("Fireball",4, hero,
				new DealSpellDamage(6,hero),
				new ImageIcon("Fireball.png").getImage());
	}
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}
}
