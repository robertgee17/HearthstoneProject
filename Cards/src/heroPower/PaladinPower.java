package heroPower;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;

public class PaladinPower extends HeroPower{
	public PaladinPower() {
		super("Recruit",new SummonMinion(),new ImageIcon("PaladinPower.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return true;
	}
}
