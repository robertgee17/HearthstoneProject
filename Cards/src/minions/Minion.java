package minions;
import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.ImageIcon;

import battlecry.*;
import deathrattle.*;

public abstract class Minion extends GameCharacter{
	protected boolean dead;
	protected boolean battlecry=false;
	protected TargetedBattlecry tb;
	protected GeneralBattlecry gb;
	//this is an arraylist in case i ever want to have minions with multiple deathrattles
	protected ArrayList<Deathrattle> d;
	protected boolean summoningSickness=true;
	protected Image boardImg;
	protected long killTime;
	protected boolean setToKill;
	protected boolean usesProj;
	//basic minion constructor
	public Minion(String name,int mana,int attack,int health,Hero hero,Image image,Image boardImg) {
		super(name,mana,attack,health,image);
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		this.boardImg=boardImg;
	}
	//TargettedBattlecry minion constructor
	public Minion(String name,int mana,int attack,int health,Hero hero,TargetedBattlecry tb,Image image,Image boardImg) {
		super(name,mana,attack,health,image);
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		battlecry=true;
		this.tb=tb;
		this.boardImg=boardImg;
	}
	//GeneralBattlecry minion constructor
	public Minion(String name,int mana,int attack,int health,Hero hero,GeneralBattlecry gb,Image image,Image boardImg,boolean proj) {
		super(name,mana,attack,health,image);
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		battlecry=true;
		this.gb=gb;
		this.boardImg=boardImg;
		this.usesProj=proj;
	}
	//Deathrattle minion constructor
	public Minion(String name,int mana,int attack,int health,Hero hero,Deathrattle d,Image image,Image boardImg,boolean proj) {
		super(name,mana,attack,health,image);
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		this.d=new ArrayList<Deathrattle>();
		this.d.add(d);
		this.boardImg=boardImg;
		this.usesProj=proj;
	}
	//check if the minion is able to attack
	public boolean canAttack() {
		if(summoningSickness==false&&!setToKill&&super.canAttack())
			return true;
		return false;
	}
	//play targettedbattlecry
	public boolean play(GameCharacter target) {
		return false;
	}
	public boolean playable() {
		return hero.getBoard().playable()&&this.mana<=hero.getMana()&&hero.isTurn();
	}

	//put minion in play and activate battlecry if there is one
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
		if(gb!=null) {
			gb.battlecry();
			if(usesProj) {
				hero.getProjectiles().add(newProjectile(gb.getTarget()));
			}
		}
		hero.spendMana(this.mana);
		this.timePlayed=System.currentTimeMillis();
		return true;
	}
	public boolean validTarget(GameCharacter target) {
		if(inPlay) {
			//System.out.println("TARGET: "+enemyHero);
			//System.out.println("BOARD: "+Arrays.toString(enemyHero.getBoard().toArray()));
			return(enemyHero.equals(target))||
					enemyHero.getBoard().contains(target);
		}
		return false;
	}
	//destroy minion and activate deathrattle if there is one
	public void destroy() {
		hero.getBoard().remove(this);
		this.inPlay=false;
		if(!(d==null))
			for(Deathrattle death:d) {
				death.deathrattle();
				if(usesProj) {
					hero.getProjectiles().add(newProjectile(death.getTarget()));
				}
			}
	}
	public void kill() {
		setToKill();
	}
	public void setSummoningSickness(boolean ss) {
		this.summoningSickness=ss;
	}
	public TargetedBattlecry getTb() {
		return this.tb;
	}
	public void updateXY() {
		if(inPlay) {
			if(hero.isPlayer())
				y=500;
			else
				y=300;
			this.x=430+150*hero.getBoard().indexOf(this);
		}
	}
	public void draw(Graphics g) {
		//when minion is in play
		if(inPlay) {
			width=boardImg.getWidth(null);
			height=boardImg.getHeight(null);
			//draw green circle around if it can attack
			if(canAttack()&&hero.isPlayer()) {
				g.setColor(Color.GREEN);
				g.fillOval(x+xAttackAnimation, y+yAttackAnimation, (int)(width/sizeMod), (int)(height/sizeMod));
			}
			if(frozen) {
				g.setColor(Color.BLUE);
				g.fillOval(x, y, (int)(width/sizeMod), (int)(height/sizeMod));
			}
			//change portrait to be correct size
			this.sizeMod=3;
			//make sure ai's board and player's board don't overlap
			if(hero.isPlayer())
				y=500;
			else
				y=300;
			//set x coord to make each minion be in a different spot
			this.x=430+150*hero.getBoard().indexOf(this);
			drawCardInPlay(g);
			if(mouseHover&&!dragging) {
				this.sizeMod=1;
				this.x=1495;
				this.y=5;
				drawCardInHand(g);
				this.sizeMod=3;
				//make sure ai's board and player's board don't overlap
				if(hero.isPlayer())
					y=500;
				else
					y=300;
				//set x coord to make each minion be in a different spot
				this.x=430+150*hero.getBoard().indexOf(this);
			}
		}else if(inHand&&mouseHover&&!recentlyDrawn) {
			//if hovering, increase size of card&move y coord to keep bottom of card visible
			this.sizeMod=1;
			this.x=130*hero.getHand().indexOf(this);
			this.y=1040-height-30;
			g.setColor(Color.GREEN);
			if((!dragging)&&playable())
				g.fillRoundRect(x+25, y+25, (int)(width/sizeMod)-37, (int)(height/sizeMod)-40, 50, 50);
			drawCardInHand(g);
		}else if(inHand) {
			//if in hand set to correct size and coords
			this.sizeMod=2.5;
			mouseHover=false;
			this.x=130*hero.getHand().indexOf(this);
			this.y=840;
			drawCardInHand(g);
		}
	}
	public void whenDraggingInHand(Graphics g) {
		//when dragging card
		//set size back to size of card in hand
		this.sizeMod=2.5;
		//set x&y to dragging coords
		this.x=xDrag-80;
		this.y=yDrag-100;
	}
	private void drawCardInPlay(Graphics g) {
		//draw portrait of minion in play
		g.drawImage(boardImg,x+xAttackAnimation,y+yAttackAnimation,(int)(width/sizeMod),(int)(height/sizeMod),null);
		//draw health icon
		g.setFont(g.getFont().deriveFont( 20.0f ));
		Image i =new ImageIcon("health.png").getImage();
		int imageMod=5;
		g.drawImage(i, x+100+xAttackAnimation,y+90+yAttackAnimation,i.getWidth(null)/imageMod,i.getHeight(null)/imageMod, null);
		//draw health amt
		if(isDamaged())
			g.setColor(Color.PINK);
		else
			g.setColor(Color.WHITE);
		if(Integer.toString(health).length()==1)
			g.drawString(Integer.toString(health), x+114+xAttackAnimation,y+130+yAttackAnimation);
		else
			g.drawString(Integer.toString(health), x+108+xAttackAnimation,y+130+yAttackAnimation);
		//draw attack image
		g.setColor(Color.BLACK);
		i =new ImageIcon("attack.png").getImage();
		g.drawImage(i, x-5+xAttackAnimation,y+95+yAttackAnimation,i.getWidth(null)/imageMod,i.getHeight(null)/imageMod, null);
		//draw attack amt
		if(Integer.toString(attack).length()==1)
			g.drawString(Integer.toString(attack), x+13+xAttackAnimation,y+131+yAttackAnimation);
		else
			g.drawString(Integer.toString(attack), x+8+xAttackAnimation,y+131+yAttackAnimation);
		if(dragging&&canAttack()) {
			g.setColor(Color.RED);
			drawAttackTriangle(g);
		}
		//show the card in top left corner if it is played
		if(System.currentTimeMillis()<=timePlayed+playImageTime) {
			g.drawImage(image,0,0,null);
		}
		//show skull if the unit dies
		i=new ImageIcon("skull.png").getImage();
		if(this.setToKill==true) {
			g.drawImage(i,x+xAttackAnimation-75,y+yAttackAnimation,/*1,1,*/ null);
		}
		//shows the damage it takes if the unit takes damage
		for(int id=0;id<recentDamageA.size();id++) {
			if(System.currentTimeMillis()<this.recentDamageTimeA.get(id)+2000&&System.currentTimeMillis()>this.recentDamageTimeA.get(id)+250) {
				g.setColor(Color.BLACK);
				g.setFont(g.getFont().deriveFont( 50.0f ));
				FontMetrics metric=g.getFontMetrics(g.getFont());
				String damage="-"+Integer.toString(recentDamageA.get(id));
				g.fillRect((int)(x+width/sizeMod/2)-20+xAttackAnimation, (int)(y+height/sizeMod/2)-metric.getHeight()+24+yAttackAnimation, metric.stringWidth(damage), 46);
				g.setColor(Color.WHITE);
				g.drawString(damage,(int)(x+width/sizeMod/2)-20+xAttackAnimation,(int)(y+height/sizeMod/2)+yAttackAnimation);
			}else if(System.currentTimeMillis()>this.recentDamageTimeA.get(id)+2250) {
				recentDamageA.remove(id);
				recentDamageTimeA.remove(id);
				id--;
			}
		}
		//show the heal amount if this unit is healed
		if(recentHeal>0&&System.currentTimeMillis()<this.recentHealTime+2000&&System.currentTimeMillis()>this.recentHealTime+250) {
			g.setColor(Color.BLACK);
			g.setFont(g.getFont().deriveFont( 50.0f ));
			FontMetrics metric=g.getFontMetrics(g.getFont());
			String damage="+"+Integer.toString(recentHeal);
			g.fillRect((int)(x+width/sizeMod/2)-20+xAttackAnimation, (int)(y+height/sizeMod/2)-metric.getHeight()+24+yAttackAnimation, metric.stringWidth(damage), 46);
			g.setColor(Color.WHITE);
			g.drawString(damage,(int)(x+width/sizeMod/2)-20+xAttackAnimation,(int)(y+height/sizeMod/2)+yAttackAnimation);
		}
	}
	//change the minion's hero that it belongs to
	public void setHero(Hero hero) {
		super.setHero(hero);
		//make sure minion's effects point to correct new hero
		if(tb!=null) {
			tb=tb.clone();
			tb.setHero(hero);
		}
		if(gb!=null) {
			gb=gb.clone();
			gb.setHero(hero);
		}
		if(d!=null)
			for(int i=0;i<d.size();i++) {
				d.get(i).setHero(hero);
			}
	}
	public Hero getHero() {
		return this.hero;
	}
	public void setToKill() {
		setToKill=true;
		this.killTime=System.currentTimeMillis();
	}
	public boolean timeToKill() {
		return(setToKill==true&&System.currentTimeMillis()>killTime+1000);
	}
	public Projectile newProjectile(GameCharacter target) {
		return new Projectile(getCenterX(),getCenterY(),target.getCenterX(),target.getCenterY(),Color.RED);
	}
	public boolean setToDie() {
		return this.setToKill;
	}
}
