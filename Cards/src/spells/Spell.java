package spells;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import generalClasses.Card;
import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

public abstract class Spell extends GameCharacter{
	protected TargetedSpellEffect ts;
	protected GeneralSpellEffect gs;
	private long yoggPlayed;
	//constructor for targetted spell
	public Spell(String name,int mana,Hero hero,TargetedSpellEffect ts,Image image) {
		super(name,mana,0,0,image);
		this.hero=hero;
		this.ts=ts;
	}
	//constructor for untargetted spell
	public Spell(String name,int mana,Hero hero,GeneralSpellEffect gs,Image image) {
		super(name,mana,0,0,image);
		this.hero=hero;
		this.gs=gs;
	}
	public Spell(String name,int mana,Hero hero,TargetedSpellEffect ts,GeneralSpellEffect gs,Image image) {
		super(name,mana,0,0,image);
		this.hero=hero;
		this.ts=ts;
		this.gs=gs;
	}
	@Override
	//using attack(target) instead of play(target) to help facilitate how this works with the listener
	public boolean attack(GameCharacter target) {
		if(!playable()||!validTarget(target))
			return false;
		else if(this.ts!=null) {
			play(target);
			if(gs!=null)
				gs.play();
		}
		hero.spendMana(this.mana);
		hero.getHand().remove(this);
		removeFromPlay();
		hero.getUsedSpells().add(this);
		this.timePlayed=System.currentTimeMillis();
		return true;
	}
	public boolean play() {
		if(!playable())
			return false;
		hero.spendMana(this.mana);
		hero.getHand().remove(this);
		if(ts!=null) {
			ts.play(hero.getRandomCharacter());
		}
		if(gs!=null) {
			gs.play();
		}
		removeFromPlay();
		hero.getUsedSpells().add(this);
		this.timePlayed=System.currentTimeMillis();
		return true;
	}
	public boolean play(GameCharacter target) {
		return ts.play(target);
	}
	public boolean yoggPlay() {
		if(ts!=null) {
			GameCharacter target;
			for(int i=0;i<1000;i++) {
				target=hero.getRandomCharacter();
				if(validTarget(target)) {
					ts.play(target);
					break;
				}
			}

		}
		if(gs!=null) {
			gs.play();
		}
		removeFromPlay();
		hero.getUsedSpells().add(this);
		this.yoggPlayed=System.currentTimeMillis();
		return true;
	}
	public void kill() {
		removeFromPlay();
	}

	public boolean playable() {
		return this.mana<=hero.getMana()&&hero.isTurn();
	}
	public void draw(Graphics g) {
		//draw card in hand when it's playable
		if(inHand&&mouseHover&&!recentlyDrawn) {
			this.sizeMod=1;
			this.y=1040-height-30;
			if((!dragging)&&playable())
				g.fillRoundRect(x+25, y+25, (int)(width/sizeMod)-37, (int)(height/sizeMod)-40, 50, 50);
			drawCardInHand(g);
			//draw card in hand
		}else if(inHand) {
			this.sizeMod=2.5;
			mouseHover=false;
			this.x=130*hero.getHand().indexOf(this);
			this.y=840;
			drawCardInHand(g);
			//draw card in top left corner after played
		}else if(System.currentTimeMillis()<=timePlayed+playImageTime) {
			g.drawImage(image,0,0,null);
			//draw spell next to yogg after he plays the spell
		}else if(System.currentTimeMillis()<=yoggPlayed+playImageTime) {
			g.drawImage(image,width,0,width/2,height/2,null);
		}
	}
	public void drawCardInHand(Graphics g) {
		if(dragging) {
			whenDraggingInHand(g);
			if(ts==null) {
				if(playable()&&!mouseHover) {
					g.setColor(Color.GREEN);
					g.fillRoundRect(x+10, y+10, (int)(width/sizeMod)-15, (int)(height/sizeMod)-15, 25, 25);
				}
				g.drawImage(image,x,y,(int)(width/sizeMod),(int)(height/sizeMod),null);
			}
		}else {
			super.drawCardInHand(g);
		}

	}
	public void whenDraggingInHand(Graphics g) {
		//if this is a targetted spell
		if(dragging) {
			if(ts!=null) {
				g.setColor(Color.BLUE);
				hero.setXDrag(xDrag);
				hero.setYDrag(yDrag);
				hero.drawAttackTriangle(g);
			}else {
				//when dragging card
				//set size back to size of card in hand
				this.sizeMod=2.5;
				//set x&y to dragging coords
				this.x=xDrag-80;
				this.y=yDrag-100;
			}
		}
	}
	@Override
	public void setHero(Hero hero) {
		super.setHero(hero);
		if(ts!=null) {
			ts=ts.clone();
			ts.setHero(hero);
		}
		if(gs!=null) {
			gs=gs.clone();
			gs.setHero(hero);
		}
	}
	public TargetedSpellEffect getTs() {
		return this.ts;
	}
	public Projectile newProjectile(GameCharacter target) {
		return new Projectile(hero.getCenterX(),hero.getCenterY(),target.getCenterX(),target.getCenterY(),Color.ORANGE);
	}
}
