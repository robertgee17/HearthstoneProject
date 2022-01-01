package spellEffects;

import java.awt.Color;

import battlecry.TargetedBattlecry;
import generalClasses.GameCharacter;
import heroes.Hero;

public abstract class GeneralSpellEffect implements SpellEffect,Cloneable{
	protected Hero hero;
	public GeneralSpellEffect(Hero hero) {
		this.hero=hero;
	}
	public abstract boolean play();
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public GeneralSpellEffect clone() {
		try {
			return (GeneralSpellEffect)super.clone();
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}
	public Projectile newProjectile(GameCharacter target) {
		return new Projectile(hero.getCenterX(),hero.getCenterY(),target.getCenterX(),target.getCenterY(),Color.ORANGE);
	}
}
