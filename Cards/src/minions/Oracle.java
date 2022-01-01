package minions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import battlecry.DealDamage;
import battlecry.HealTarget;
import battlecry.*;
import generalClasses.GameCharacter;
import heroes.Hero;

public class Oracle extends TargettedBattlecryMinion{
	public Oracle(Hero hero) {
		super("Oracle", 3, 3, 3, hero, new HealTarget(3,hero),
				new ImageIcon("Oracle.png").getImage(), 
				new ImageIcon("OracleM.png").getImage());
		// TODO Auto-generated constructor stub
	}
	
	
}
