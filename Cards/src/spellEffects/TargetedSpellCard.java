package spellEffects;

import generalClasses.GameCharacter;

public interface TargetedSpellCard extends SpellEffect{
	public boolean play(GameCharacter target);
}
