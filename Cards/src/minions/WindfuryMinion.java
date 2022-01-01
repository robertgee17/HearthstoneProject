package minions;

import java.awt.Image;

import heroes.Hero;

public class WindfuryMinion extends Minion{
	public WindfuryMinion(String name, int mana, int attack, int health, Hero hero,
			Image image, Image boardImg) {
		super(name, mana, attack, health, hero, image, boardImg);
		// TODO Auto-generated constructor stubS
		this.totalAttacksInTurn=2;
	}
}
