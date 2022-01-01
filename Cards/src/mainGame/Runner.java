package mainGame;

import java.awt.*;
import java.util.Arrays;
import java.awt.geom.Line2D;
import javax.swing.*;

import decks.*;
import generalClasses.GameCharacter;
import heroes.*;
import minions.*;
import spellEffects.Projectile;

public class Runner extends JPanel{
	private Hero Player;
	private Hero AiHero=new Mage("Opponent",false);
	private AI AI;
	private boolean started;
	private boolean end;
	private boolean playerWin;
	private long startTurn;
	private GamePanel gp;
	//TODO NEEDED IMAGES: TheGeneral
	public Runner(GamePanel gp) {
		this.gp=gp;
		//initialize();
		//Player.startTurn();
	}
	public boolean isStarted() {
		return this.started;
	}
	public Hero getHeroA() {
		return Player;
	}
	public Hero getHeroB() {
		return AiHero;
	}
	public AI getAI() {
		return AI;
	}
	public void setPlayerHero(Hero hero) {
		this.Player=hero;
		//System.out.println(Player);
		if(Player instanceof Mage)
			Player.addDeck(new MageDeck(Player).getDeck());
		else if(Player instanceof Hunter)
			Player.addDeck(new HunterDeck(Player).getDeck());
		else if(Player instanceof Paladin)
			Player.addDeck(new PaladinDeck(Player).getDeck());
		else if(Player instanceof Warrior)
			Player.addDeck(new WarriorDeck(Player).getDeck());
		gp.getListener().setPlayerHero(Player);
	}
	public void setAiHero() {
		int random=(int)(Math.random()*4);
		Hero ai;
		if(random==0) {
			ai=new Mage("Opponent",false);
			AiHero=ai;
			AiHero.addDeck(new MageDeck(AiHero).getDeck());
		}else if(random==1) {
			ai=new Hunter("Opponent",false);
			AiHero=ai;
			AiHero.addDeck(new HunterDeck(AiHero).getDeck());
		}else if(random==2) {
			ai=new Paladin("Opponent",false);
			AiHero=ai;
			AiHero.addDeck(new PaladinDeck(AiHero).getDeck());
		}else if(random==3) {
			ai=new Warrior("Opponent",false);
			AiHero=ai;
			AiHero.addDeck(new WarriorDeck(AiHero).getDeck());
		}
		//System.out.println(AiHero);
		gp.getListener().setAiHero(AiHero);
	}
	//performs necessary actions before game starts
	public void initialize() {
		setAiHero();
		started=true;
		//Player.addDeck(new Decklist3(Player).getDeck());
		//AiHero.addDeck(new Decklist2(AiHero).getDeck());
		//set enemyHeroes in both heroes
		Player.addEnemy(AiHero);
		AiHero.addEnemy(Player);
		//add decks to both players
		Player.drawCard(3);
		AiHero.drawCard(4);
		AI=new AI(AiHero,this);
		gp.getListener().setAI(AI);
		Player.startTurn();
	}
	@Override
	public void paintComponent(Graphics g) {
		if(started){
			super.paintComponent(g);
			//PLACE EVERYTHING AFTER THIS SO IT DOESN'T GET PAINTED OVER BY BACKGROUND
			//testing
			//delete these if i forget to before turning in
			/*g.setColor(Color.BLACK);
			g.drawRect(0, 0, 100, 100);
			g.drawString("draw", 50,50 );
			g.drawRect(0,100,100,100);
			g.drawString("start aiTurn", 50,150 );
			g.drawRect(0,200,100,100);
			g.drawString("ai attack", 50,250 );
			g.drawRect(0, 300, 100, 100);
			g.drawString("ai play", 50,350 );
			*/
			//draw end turn button and it's text
			
			g.setColor(new Color(214,200,188));
			g.fillRect(250, 150, 1400, 500);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(15));
			g2.setColor(Color.BLACK);
			g2.drawRect(250, 150, 1400, 500);
			g2.setStroke(new BasicStroke(0));
			if(!Player.isTurn()) 
				g.setColor(Color.GRAY);
			else if(Player.actionsRemaining())
				g.setColor(Color.YELLOW);
			else
				g.setColor(Color.GREEN);
			g.fillRect(1590, 380, 120, 40);
			g.setColor(Color.BLACK);
			g.drawRect(1590, 380, 120, 40);
			g.setFont(g.getFont().deriveFont( 20.0f ));
			if(Player.isTurn())
				g.drawString("End Turn", 1610, 408);
			else
				g.drawString("Enemy Turn", 1598, 408);

			//draw AI portrait
			AiHero.draw(g);
			//draw ai's cards in hand
			drawAiHand(g);

			//draw ai's board
			for(int i=0;i<AiHero.getBoard().size();i++) {
				AiHero.getBoard().get(i).draw(g);
			}
			//draw player's board
			for(int i=0;i<Player.getBoard().size();i++) {
				Player.getBoard().get(i).draw(g);
			}
			//draw player portrait
			Player.draw(g);
			//draw player's hand 
			for(int i=0;i<Player.getHand().size();i++) {
				if(!Player.getHand().get(i).isMouseHover())
					Player.getHand().get(i).draw(g);
			}
			//draw card being hovered
			//makes sure hovered card isn't hiding behind other cards in hand
			for(int i=0;i<Player.getHand().size();i++) {
				if(Player.getHand().get(i).isMouseHover())
					Player.getHand().get(i).draw(g);
			}
			//draw spells
			for(int i=0;i<Player.getUsedSpells().size();i++) {
				Player.getUsedSpells().get(i).draw(g);
			}
			for(int i=0;i<AiHero.getUsedSpells().size();i++) {
				AiHero.getUsedSpells().get(i).draw(g);
			}
			//draw both players' projectiles
			for(Projectile p:Player.getProjectiles()) {
				p.draw(g);
			}
			for(Projectile p:AiHero.getProjectiles()) {
				p.draw(g);
			}
			if(end) {
				g.setFont(g.getFont().deriveFont( 200.0f ));
				g.setColor(Color.RED);
				if(playerWin) {
					g.drawString("Victory!", 600, 500);
				}else{
					g.drawString("Defeat", 650, 500);
				}
			}else if(System.currentTimeMillis()<startTurn+1000&&System.currentTimeMillis()>startTurn) {
				g.setFont(g.getFont().deriveFont( 200.0f ));
				g.setColor(Color.BLUE);
				g.drawString("Your Turn", 550, 500);
			}
		}
	}
	private void drawAiHand(Graphics g) {
		Image img = AiHero.getCardback();
		int width=img.getWidth(null);
		int height=img.getHeight(null);
		for(int i=AiHero.getHand().size()-1;i>=0;i--) {
			GameCharacter temp=AiHero.getHand().get(i);
			temp.setX(795-(width/4)*(10-i));
			temp.setY(20);
			temp.setSizeMod(2);
			g.drawImage(img,temp.getX()+temp.getxDrawAnimation(),temp.getY(),(int)(width/temp.getSizeMod()),(int)(height/temp.getSizeMod()),null);
		}
	}
	public void setEnd(boolean playerWin) {
		this.end=true;
		this.playerWin=playerWin;
	}
	public boolean getEnd() {
		return this.end;
	}
	public void setStartTurn(long startTime) {
		this.startTurn=startTime;
	}
}
