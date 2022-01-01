package minions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import battlecry.*;
import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.Projectile;

public abstract class TargettedBattlecryMinion extends Minion{
	protected GameCharacter target;
	protected Color battlecryColor;
	public TargettedBattlecryMinion(String name, int mana, int attack, int health, Hero hero, TargetedBattlecry tb,
			Image image, Image boardImg) {
		super(name, mana, attack, health, hero, tb, image, boardImg);
		this.tb=tb;
		// TODO Auto-generated constructor stub
	}
	public boolean validTarget(GameCharacter target) {
		if(inPlay)
			return super.validTarget(target);
		else if(inHand)
			return this.battlecryTarget(target);
		else
			return false;
	}
	//returns if the target is a valid target for battlecry
	public boolean battlecryTarget(GameCharacter target) {
		return tb.validTarget(target);
	}
	//plays battlecry
	public void battlecry(GameCharacter target) {
		tb.battlecry(target);
	}
	public boolean play() {
		//can't play if board is full or mana cost is greater than hero's current mana
		if(!playable())
			return false;
		else {
			hero.getHand().remove(this);
			hero.getBoard().add(this);
			setInPlay();
			this.updateXY();
		}
		if(tb!=null) {
			if(target!=null) {
				hero.getProjectiles().add(newProjectile(target));
				tb.battlecry(target);
			}else {
				GameCharacter target=hero.getRandomCharacter();
				hero.getProjectiles().add(newProjectile(target));
				tb.battlecry(target);
			}
		}
		hero.spendMana(this.mana);
		this.timePlayed=System.currentTimeMillis();
		return true;
	}
	@Override
	public boolean play(GameCharacter target) {
		this.target=target;
		return play();
	}
	public void whenDraggingInHand(Graphics g) {
		//when dragging card
		//set size back to size of card in hand
		if(playable()) {
			this.sizeMod=2.5;
			this.x=130*hero.getHand().indexOf(this);
			this.y=840;
			g.setColor(Color.RED);
			drawAttackTriangle(g);
		}
		else {
			//when dragging card
			//set size back to size of card in hand
			this.sizeMod=2.5;
			//set x&y to dragging coords
			this.x=xDrag-80;
			this.y=yDrag-100;
		}
	}	
	public void setTarget(GameCharacter target) {
		if(target==null)
			this.target=null;
		else if(battlecryTarget(target))
			this.target=target;
	}
	public GameCharacter getTarget() {
		return this.target;
	}

}
