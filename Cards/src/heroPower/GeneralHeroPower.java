package heroPower;

import generalClasses.GameCharacter;
import heroes.Hero;

public interface GeneralHeroPower extends HPower{
	public boolean heroPower();
	public void addHero(Hero hero);
	public GameCharacter getTarget();
}
