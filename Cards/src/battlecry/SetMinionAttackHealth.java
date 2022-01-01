package battlecry;

import generalClasses.GameCharacter;
import heroes.Hero;
import minions.Minion;

public class SetMinionAttackHealth extends TargetedBattlecry{
	private int attack,health;

	public SetMinionAttackHealth(int attack,int health,Hero hero) {
		super(hero);
		this.attack=attack;
		this.health=health;
	}
	//deals dmg amount of damage to a target
	public void battlecry(GameCharacter target) {
		target.setStats(attack, health);
	}
	@Override
	public boolean validTarget(GameCharacter target) {
		return target instanceof Minion;
	}
}