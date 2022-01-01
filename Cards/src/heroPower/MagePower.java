package heroPower;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;

public class MagePower extends HeroPower{
	public MagePower() {
		super("Fireblast",new DealDamage(1),new ImageIcon("MagePower.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return target.isInPlay();
	}
}
