package generalClasses;

import java.awt.Graphics;
import java.awt.Image;

import heroPower.HeroPower;
import heroes.Hero;
//Any object in this mode
public abstract class Card {
	protected boolean inPlay;
	protected boolean inDeck=true;
	protected boolean inHand;
	protected boolean mouseHover;
	protected int mana;
	protected Image image;
	protected String name;
	protected Hero hero;
	protected Hero enemyHero;
	protected int x;
	protected int y;
	protected int xDrag;
	protected int yDrag;
	protected int width;
	protected int height;
	protected boolean dragging;
	protected double sizeMod=1;
	protected boolean recentlyDrawn;
	protected int xDrawAnimation;
	protected int yDrawAnimation;
	public Card(String name,int mana,Image image){
		this.mana=mana;
		this.name=name;
		this.image=image;
		this.width=image.getWidth(null);
		this.height=image.getHeight(null);
	}
	//everything needs to be drawn
	public abstract void draw(Graphics g);
	public void drawAfterPlay(Graphics g) {
		double x=1.1;
		g.drawImage(image,0,0,(int)(image.getWidth(null)/x),(int)(image.getHeight(null)/x),null);
	}
	//set booleans so that I can check if card is in play
	public void setInPlay() {
		inPlay=true;
		inHand=false;
		inDeck=false;
	}
	//set booleans so that I can check if card is in hand
	public void setInHand() {
		inPlay=false;
		inHand=true;
		inDeck=false;
	}
	//when the card dies
	public void removeFromPlay() {
		inPlay=false;
		inHand=false;
		inDeck=false;
	}
	public void setRecentlyDrawn(boolean recentlyDrawn) {
		this.recentlyDrawn = recentlyDrawn;
		if(recentlyDrawn=true) {
			xDrawAnimation=1630;
			if(this.hero.isPlayer()) 
				yDrawAnimation=(-250);
		}
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getY() {
		return y;
	}
	
	public double getSizeMod() {
		return sizeMod;
	}
	public void setSizeMod(double sizeMod) {
		this.sizeMod = sizeMod;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean inCoords(int xCoord,int yCoord) {
		if(this instanceof HeroPower) {
			
		}
		return ((inHand||inPlay)&& 
				xCoord<=x+width/sizeMod && xCoord>=x&& 
				yCoord<=y+height/sizeMod && yCoord>=y);
	}
	public void setMouseHover(boolean hover) {
		this.mouseHover=hover;
	}
	public boolean isMouseHover() {
		return this.mouseHover;
	}
	public String toString() {
		return this.name;
	}
	public Image getImage() {
		return this.image;
	}
	public void setDragging(boolean drag) {
		this.dragging=drag;
	}
	public boolean isDragging() {
		return this.dragging;
	}
	public void setXDrag(int x) {
		this.xDrag=x;
	}
	public void setYDrag(int y) {
		this.yDrag=y;
	}
	
	public int getxDrawAnimation() {
		return xDrawAnimation;
	}
	public void setxDrawAnimation(int xDrawAnimation) {
		this.xDrawAnimation = xDrawAnimation;
	}
	public int getyDrawAnimation() {
		return yDrawAnimation;
	}
	public void setyDrawAnimation(int yDrawAnimation) {
		this.yDrawAnimation = yDrawAnimation;
	}
	public boolean isInPlay() {
		return this.inPlay;
	}
	
	public boolean isInHand() {
		return this.inHand;
	}
	public void setName(String name) {
		this.name=name;
	}
	public abstract boolean play();
	public abstract boolean play(GameCharacter target);
	public boolean isInDeck() {
		return inDeck;
	}
	public void setInDeck(boolean inDeck) {
		this.inDeck = inDeck;
	}
	public boolean isAlive() {
		return inPlay||inDeck||inHand;
	}
	public boolean isRecentlyDrawn() {
		return recentlyDrawn;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
	}
	public int getCenterX() {
		return (int)(x+((width/sizeMod)/2));
	}
	public int getCenterY() {
		return (int)(y+((height/sizeMod)/2));
	}
	public int getMana() {
		return this.mana;
	}
}
