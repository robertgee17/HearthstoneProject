package spells;

import java.awt.Image;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class MindBlast extends Spell{

	public MindBlast(Hero hero) {
		super("Mind Blast", 2, hero, 
				new SpellDamageEnemyHero(5,hero),
				new ImageIcon("MindBlast.png").getImage());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return target.equals(hero.getEnemy());
	}

}
