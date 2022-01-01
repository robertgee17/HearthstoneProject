package spellEffects;

import java.awt.Color;

import battlecry.TargetedBattlecry;
import heroes.Hero;
import generalClasses.GameCharacter;

public abstract class TargetedSpellEffect implements SpellEffect,Cloneable{
	protected Hero hero;
	public TargetedSpellEffect(Hero hero) {
		this.hero=hero;
	}
	public abstract boolean play(GameCharacter target);
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public TargetedSpellEffect clone() {
		try {
			return (TargetedSpellEffect)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}
	public abstract boolean validTarget(GameCharacter target);
	
	public Projectile newProjectile(GameCharacter target) {
		return new Projectile(hero.getCenterX(),hero.getCenterY(),target.getCenterX(),target.getCenterY(),Color.ORANGE);
	}
}
