package minions;

import javax.swing.ImageIcon;

import battlecry.DealDamage;
import generalClasses.GameCharacter;
import heroes.Hero;

public class Marksman extends TargettedBattlecryMinion{
	public Marksman(Hero hero) {
		super("Marksman",6,4,5,hero,new DealDamage(4,hero),
				new ImageIcon("Marksman.png").getImage(),
				new ImageIcon("MarksmanM.png").getImage());
	}
	@Override
	public boolean battlecryTarget(GameCharacter target) {
		return target instanceof Minion&&super.battlecryTarget(target);
	}
}
