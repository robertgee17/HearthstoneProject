package minions;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import battlecry.*;
import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public class Archer extends TargettedBattlecryMinion{
	public Archer(Hero hero) {
		super("Archer",1,1,1,hero,new DealDamage(1,hero),
				new ImageIcon("Archer.png").getImage(),
				new ImageIcon("ArcherM.png").getImage());
	}
}
