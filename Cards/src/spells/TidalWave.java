package spells;

import java.awt.Image;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class TidalWave extends Spell{

	public TidalWave(Hero hero) {
		super("TidalWave", 6, hero, new DamageAllEnemies(3,hero), 
				new ImageIcon("TidalWave.png").getImage());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
