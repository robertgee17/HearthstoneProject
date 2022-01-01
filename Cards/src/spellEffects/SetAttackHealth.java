package spellEffects;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;
import spells.Spell;

public class SetAttackHealth extends TargetedSpellEffect{
	private int attack,health;
	public SetAttackHealth(int attack,int health,Hero hero) {
		super(hero);
		this.attack=attack;
		this.health=health;
	}
	public boolean play(GameCharacter target) {
		target.setStats(attack, health);
		return true;
	}
	public boolean validTarget(GameCharacter target) {
		return target instanceof Minion;
	}
}