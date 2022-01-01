package generalClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import heroes.Hero;
import minions.Minion;
import spells.*;
public abstract class GameCharacter extends Card implements Cloneable{
	protected long timePlayed=0;
	protected int health;
	protected int maxHealth;
	protected int attack;
	protected int totalAttacksInTurn=1;
	protected int attacksThisTurn;
	protected boolean frozen;
	protected long playImageTime=2000;
	protected boolean attackingTowards;
	protected boolean attackingReturn;
	protected int xAttackAnimation;
	protected int yAttackAnimation;
	protected GameCharacter attackTarget;
	protected int recentHeal;
	protected ArrayList<Integer> recentDamageA=new ArrayList<Integer>();
	protected long recentHealTime;
	protected ArrayList<Long> recentDamageTimeA=new ArrayList<Long>();
	public GameCharacter(String name,int mana,int attack,int health,Image image) {
		super(name,mana,image);
		this.maxHealth=health;
		this.health=health;
		this.attack=attack;
		this.attacksThisTurn=totalAttacksInTurn;
	}
	//attack a target and take damage equal to the target's attack
	public boolean attack(GameCharacter target) {
		if(!canAttack())
			return false;
		attackingTowards=true;
		attackTarget=target;
		target.takeDamage(this);
		this.takeDamage(target);
		attacksThisTurn--;
		return true;
	}
	public void attackIncrement() {
		if(hero.isPlayer()) {
			if(attackingTowards) {
				xAttackAnimation+=(attackTarget.getX()-this.x)/8;
				yAttackAnimation+=(attackTarget.getY()+50-this.y)/8;
			}else if(attackingReturn){
				xAttackAnimation-=(attackTarget.getX()-this.x)/8;
				yAttackAnimation-=(attackTarget.getY()+50-this.y)/8;
			}
			if(y+yAttackAnimation<=attackTarget.getY()+50) {
				attackingTowards=false;
				attackingReturn=true;
			}else if(y+yAttackAnimation>=this.y) {
				attackingTowards=false;
				attackingReturn=false;
			}
		}else {
			if(attackingTowards) {
				xAttackAnimation+=(attackTarget.getX()-this.x)/8;
				yAttackAnimation+=(attackTarget.getY()-50-this.y)/8;
			}else if(attackingReturn){
				xAttackAnimation-=(attackTarget.getX()-this.x)/8;
				yAttackAnimation-=(attackTarget.getY()-50-this.y)/8;
			}
			if(y+yAttackAnimation>=attackTarget.getY()-50) {
				attackingTowards=false;
				attackingReturn=true;
			}else if(y+yAttackAnimation<=this.y) {
				attackingTowards=false;
				attackingReturn=false;
			}
		}
		if(!attackingTowards&&!attackingReturn) {
			xAttackAnimation=0;
			yAttackAnimation=0;
		}
			

	}
	//checks if the character can attack
	public boolean canAttack() {
		if(attack>0&&attacksThisTurn>0&&!frozen&&hero.isTurn()&&health>0)
			return true;
		return false;
	}
	//takes damage equal to attacker's attack
	public void takeDamage(GameCharacter attacker) {
		takeDamage(attacker.getAttack());
	}
	//take x amount of damage
	public void takeDamage(int damage) {
		this.health-=damage;
		if(damage>0) {
			this.recentDamageA.add(damage);
			this.recentDamageTimeA.add(System.currentTimeMillis());
		}
		if(this.health<=0)
			kill();
	}
	//heal for x amount
	public void heal(int heal) {
		this.health+=heal;
		this.recentHeal=checkHP(heal);
		this.recentHealTime=System.currentTimeMillis();
	}
	//makes sure visible heath isn't larger than max heath
	protected int checkHP(int heal) {
		int actualHeal=heal;
		if(health>maxHealth) {
			actualHeal=heal-(health-maxHealth);
			health=maxHealth;
		}
		return actualHeal;
	}
	//increase maxHP and visible health by x
	public void addHP(int increase) {
		health+=increase;
		maxHealth+=increase;
	}
	//increase attack by x
	public void addAtk(int increase) {
		attack+=increase;
	}
	//reduce maxHP by x amount and make sure current HP isn't bigger
	public void reduceHP(int decrease) {
		maxHealth-=decrease;
		checkHP(decrease);
	}
	//reduce attack by x amt, make sure it doesn't go under 0
	public void reduceAtk(int decrease) {
		attack-=decrease;
		if(attack<0)
			attack=0;
	}
	//increase attack and hp
	public void addStats(int attack,int health) {
		addAtk(attack);
		addHP(health);
	}
	public void setAttack(int attack) {
		this.attack=attack;
	}
	public void setHealth(int health) {
		this.health=health;
		this.maxHealth=health;
	}
	public void setStats(int attack,int health) {
		setAttack(attack);
		setHealth(health);
	}
	//is damaged if it's current hp is less than max hp
	public boolean isDamaged() {
		if(this.health<maxHealth)
			return true;
		return false;
	}
	public String getName() {
		return name;
	}
	public int getHealth() {
		if(health<0)
			return 0;
		return health;
	}
	public int getAttack() {
		return attack;
	}
	public abstract void kill();
	//reset number of attacks a character has at start of each turn
	public void resetAttacks() {
		this.attacksThisTurn=this.totalAttacksInTurn;
	}
	public boolean playable() {
		return true;
	}
	public void setFrozen(boolean frozen) {
		this.frozen=frozen;
	}
	public boolean isFrozen() {
		return this.frozen;
	}
	public int getAttacksThisTurn() {
		return this.attacksThisTurn;
	}
	public abstract boolean validTarget(GameCharacter target);
	
	public ArrayList<GameCharacter> validTargets(){
		ArrayList<GameCharacter> tempA=new ArrayList<GameCharacter>();
		if(this instanceof Hero) {
			for(int i=0;i<((Hero)this).getEnemy().getBoard().size();i++){
				tempA.add(((Hero)this).getEnemy().getBoard().get(i));
			}
			tempA.add(((Hero)this).getEnemy());
		}else if(this instanceof Spell&&((Spell)this).getTs()!=null) {
			for(int i=0;i<hero.getBoard().size();i++) {
				if(validTarget(hero.getBoard().get(i)))
					tempA.add(hero.getBoard().get(i));
			}
			for(int i=0;i<hero.getEnemy().getBoard().size();i++) {
				if(validTarget(hero.getEnemy().getBoard().get(i)))
					tempA.add(hero.getEnemy().getBoard().get(i));
			}
			if(validTarget(hero.getEnemy()))
				tempA.add(hero.getEnemy());
			if(validTarget(hero))
				tempA.add(hero);
		}else if(this instanceof Minion) {
			for(int i=0;i<hero.getBoard().size();i++) {
				if(validTarget(hero.getBoard().get(i)))
					tempA.add(hero.getBoard().get(i));
			}
			for(int i=0;i<hero.getEnemy().getBoard().size();i++) {
				if(validTarget(hero.getEnemy().getBoard().get(i)))
					tempA.add(hero.getEnemy().getBoard().get(i));
			}
			if(validTarget(hero.getEnemy()))
				tempA.add(hero.getEnemy());
			if(validTarget(hero))
				tempA.add(hero);
		}
		return tempA;
	}

	public void drawCardInHand(Graphics g) {
		if(dragging) {
			whenDraggingInHand(g);
		}
		if(recentlyDrawn) {
			sizeMod=1.5;
		}
		if(playable()&&!mouseHover) {
			g.setColor(Color.GREEN);
			g.fillRoundRect(x+10+xDrawAnimation, y+10+yDrawAnimation, (int)(width/sizeMod)-15, (int)(height/sizeMod)-15, 25, 25);
		}
		g.drawImage(image,x+xDrawAnimation,y+yDrawAnimation,(int)(width/sizeMod),(int)(height/sizeMod),null);
	}
	public void incrementXYDrawAnimation() {
		xDrawAnimation-=30;
		if(xDrawAnimation<=0) {
			recentlyDrawn=false;
			yDrawAnimation=0;
			xDrawAnimation=0;
		}
	}
	public void whenDraggingInHand(Graphics g) {}
	public void setTimePlayed(long time) {
		this.timePlayed=time;
	}
	//draws a triangle that follows cursor
	//and line from minion to cursor
	public void drawAttackTriangle(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(15));
		g2.drawLine((int)(x+width/sizeMod/2),(int)(y+height/sizeMod/2), xDrag, yDrag);
		g2.setStroke(new BasicStroke(0));
		double x1=x+width/sizeMod/2;
		double y1=y+height/sizeMod/2;
		double x2=xDrag;
		double y2=yDrag;
		double slope=getSlope(x1,y1,x2,y2);
		//find coord of triangle tip, a little bit farther out from the mouse
		double triangleSize=15;
		int newX4=(int)getXLine(slope,xDrag,x2<x1,triangleSize);
		int newY4=(int)getYLine(slope,yDrag,x2<x1,triangleSize);
		//finds x/y coord of the point i want to draw the lines out from to get the bottom 2 points
		triangleSize=80;
		int newX=(int)getXLine(slope,newX4,x2>x1,triangleSize);
		int newY=(int)getYLine(slope,newY4,x2>x1,triangleSize);
		//finds x/y coord of two bottom points of triangle
		triangleSize=60;
		int newX2=(int)getXLine(1/slope,newX,false,triangleSize);
		int newY2=(int)getYLine(1/slope,newY,true,triangleSize);
		int newX3=(int)getXLine(1/slope,newX,true,triangleSize);
		int newY3=(int)getYLine(1/slope,newY,false,triangleSize);

		g2.fillPolygon(new int[] {newX4,newX2,newX3}, new int[] {newY4,newY2,newY3}, 3);
	}
	//determines slope of line from minion to mouse
	private double getSlope(double x1,double y1,double x2,double y2) {
		return (y2-y1)/(x2-x1);
	}
	//finds x coords of the two bottom points of triangle
	private double getXLine(double slope,double x,boolean rightSide,double triangleSize) {
		if(rightSide)
			return x-(triangleSize/(Math.sqrt(slope*slope+1)));
		return x+triangleSize/(Math.sqrt(slope*slope+1));
	}
	//finds y coords of the two bottom points of triangle
	private double getYLine(double slope,double y,boolean rightSide,double triangleSize) {
		if(rightSide)
			return y-slope*(triangleSize/(Math.sqrt(slope*slope+1)));
		return y+slope*(triangleSize/(Math.sqrt(slope*slope+1)));
	}
	//making this stuff cloneable so i can clone it for the ai calculating score thing
	public GameCharacter clone() {
		try {
			GameCharacter temp= (GameCharacter)super.clone();
			temp.setRecentDamageA((ArrayList<Integer>) temp.getRecentDamageA().clone());
			temp.setRecentDamageTimeA((ArrayList<Long>) temp.getRecentDamageTimeA().clone());
			return temp;
		}catch(CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
		}
		return null;
	}

	public Hero getHero() {
		return this.hero;
	}

	public String toString() {
		return this.name;
	}
	public boolean isAttackingTowards() {
		return attackingTowards;
	}
	public void setAttackingTowards(boolean attackingTowards) {
		this.attackingTowards = attackingTowards;
	}
	public boolean isAttackingReturn() {
		return attackingReturn;
	}
	public void setAttackingReturn(boolean attackingReturn) {
		this.attackingReturn = attackingReturn;
	}
	public ArrayList<Integer> getRecentDamageA() {
		return recentDamageA;
	}
	public void setRecentDamageA(ArrayList<Integer> recentDamageA) {
		this.recentDamageA = recentDamageA;
	}
	public ArrayList<Long> getRecentDamageTimeA() {
		return this.recentDamageTimeA;
	}
	public void setRecentDamageTimeA(ArrayList<Long> recentDamageTimeA) {
		this.recentDamageTimeA = recentDamageTimeA;
	}
	public void setEnemyHero(Hero enemyHero) {
		this.enemyHero=enemyHero;
	}
	
}

