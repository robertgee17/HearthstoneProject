package heroPower;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;

public class WarriorPower extends HeroPower{
	public WarriorPower() {
		super("Armor Up",new GainArmor(2),new ImageIcon("WarriorPower.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return true;
	}
}
