package heroPower;

import javax.swing.ImageIcon;

import generalClasses.GameCharacter;

public class HunterPower extends HeroPower{
	public HunterPower() {
		super("Headshot",new DamageEnemyHero(2),new ImageIcon("HunterPower.png").getImage());
	}

	@Override
	public boolean validTarget(GameCharacter target) {
		return hero.getEnemy().equals(target);
	}
}
