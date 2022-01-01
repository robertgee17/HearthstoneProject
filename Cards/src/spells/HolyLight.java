package spells;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class HolyLight extends Spell implements TargetedSpellCard{

	public HolyLight(Hero hero) {
		super("HolyLight", 3, hero, 
				new SpellHealTarget(6,hero),
				new SpellDrawCard(1,hero),
				new ImageIcon("HolyLight.png").getImage());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return ts.validTarget(target);
	}

}