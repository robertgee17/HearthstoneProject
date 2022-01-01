package heroPower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import generalClasses.GameCharacter;
import heroes.Hero;
import spellEffects.Projectile;

public abstract class HeroPower extends GameCharacter{
	protected HPower h;
	public HeroPower(String name, HPower h, Image image) {
		super(name, 2, 0, 0, image);
		this.h=h;
		this.sizeMod=2.3;
		this.setInPlay();
	}
	public void draw(Graphics g) {
		if(playable()&&hero.isPlayer()) {
			g.setColor(Color.GREEN);
			g.fillRoundRect(x, y+15, (int)(width/sizeMod), (int)(height/sizeMod)-20, 50, 50);
		}
		g.drawImage(image,x,y,(int)(width/sizeMod),(int)(height/sizeMod),null);
		if(dragging&&playable()&&this.h instanceof TargetedHeroPower) {
			g.setColor(Color.RED);
			this.drawAttackTriangle(g);
		}
		if(mouseHover&&!dragging) {
			this.sizeMod=1;
			this.x=1500;
			this.y=5;
			drawCardInHand(g);
			this.sizeMod=2.3;
			this.x=hero.getX()+200;
			this.y=hero.getY()+1;
		}
		if(System.currentTimeMillis()<=timePlayed+playImageTime) {
			g.drawImage(image,0,0,null);
		}
	}
	@Override
	public void kill() {}
	@Override
	public boolean attack(GameCharacter target) {
		if(!playable())
			return false;
		else if(this.h instanceof GeneralHeroPower) {
			if(((GeneralHeroPower) h).getTarget()!=null)
				hero.getProjectiles().add(newProjectile(((GeneralHeroPower) h).getTarget()));
			heroPower();
		}
		else if(this.h instanceof TargetedHeroPower) {
			hero.getProjectiles().add(newProjectile(target));
			heroPower(target);
		}
		hero.spendMana(2);
		attacksThisTurn--;
		this.timePlayed=System.currentTimeMillis();
		return true;
	}
	@Override
	public boolean playable() {
		return mana<=hero.getMana()&&attacksThisTurn>0&&hero.isTurn();
	}

	public boolean heroPower() {
		return ((GeneralHeroPower)h).heroPower();
	}

	public boolean heroPower(GameCharacter target) {
		return ((TargetedHeroPower)h).heroPower(target);
	}
	public void addHero(Hero hero) {
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		h.addHero(hero);
		this.x=hero.getX()+200;
		this.y=hero.getY()+1;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
		this.enemyHero=hero.getEnemy();
		h.addHero(hero);
	}
	public HPower getPower() {
		return this.h;
	}
	@Override
	public boolean play() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean play(GameCharacter target) {
		// TODO Auto-generated method stub
		return false;
	}
	public Projectile newProjectile(GameCharacter target) {
		return new Projectile(getCenterX(),getCenterY(),target.getCenterX(),target.getCenterY(),Color.BLUE);
	}

}
