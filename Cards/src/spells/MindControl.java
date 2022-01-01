package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class MindControl extends Spell implements TargetedSpellCard{
	public MindControl(Hero hero) {
		super("Mind Control",9, hero,
				new TakeControlOfMinion(hero),
				new ImageIcon("MindControl.png").getImage());
	}
	public boolean validTarget(GameCharacter target) {
		return ts.validTarget(target);
	}
}
