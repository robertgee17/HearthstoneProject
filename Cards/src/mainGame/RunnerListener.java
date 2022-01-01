package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.Timer;
import javax.swing.JFrame;

import battlecry.*;
import generalClasses.Card;
import generalClasses.GameCharacter;
import heroPower.*;
import heroes.AI;
import heroes.Hero;
import howToPlay.HowToJFrame;
import spells.*;
import spellEffects.*;
import minions.*;

public class RunnerListener implements MouseListener,MouseMotionListener,ActionListener{
	private Runner r;
	private Hero Player;
	private Hero AiHero;
	private AI Ai;
	private GameCharacter draggingHandCard;
	private GameCharacter draggingBoardCard;
	private Timer timer;
	private long endPause=0;
	private long pause=0;
	public RunnerListener(Runner r) {
		this.r=r;
		this.Ai=r.getAI();
		r.addMouseMotionListener(this);
		r.addMouseListener(this);
		timer=new Timer(17,this);
		timer.start();
	}
	public void setPlayerHero(Hero hero) {
		this.Player=hero;
	}
	public void setAiHero(Hero hero) {
		this.AiHero=hero;
	}
	public void setAI(AI Ai) {
		this.Ai=Ai;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(r.isStarted()) {
			// testing stuff
			int x=e.getX();
			int y=e.getY();
			/*if(e.getX()<100&&e.getY()<100)
				Player.startTurn();
			else if(e.getX()<100&&e.getY()<200)
				AiHero.startTurn();
			else if(e.getX()<100&&e.getY()<300) {
				Player.setFrozen(true);
				for(int i=0;i<Player.getBoard().size();i++) {
					Player.getBoard().get(i).setFrozen(true);
				}
			}
			else if(e.getX()<100&&e.getY()<400) {
				if(AiHero.getHand().size()>0)
					AiHero.getHand().get(0).play();
			}else if(e.getX()>100&&e.getX()<200&&e.getY()<100) {
				Player.addArmor(10);
			}*/
			if(Player.getHeroPower().inCoords(x, y)&&
					Player.getHeroPower().getPower() instanceof GeneralHeroPower) {
				Player.getHeroPower().attack(null);
			}
			//if clicking the end turn button
			if(x>=1600&&x<=1700&&y>=380&&y<=420&&Player.isTurn()) {
				Player.endTurn();
				//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NEW TURN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				pause=System.currentTimeMillis();
				AiHero.startTurn();
				//System.out.println("AiHand: "+Arrays.toString(AiHero.getHand().toArray()));
			}
			//if right click, cancel all dragging things
			int z=e.getButton();
			if(z==3) {
				if(draggingHandCard!=null) {
					draggingHandCard.setDragging(false);
					draggingHandCard=null;
				}if(draggingBoardCard!=null) {
					draggingBoardCard.setDragging(false);
					draggingBoardCard=null;
				}
			}
			endGame();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(r.isStarted()) {
			//make sure no action occurs if right click
			if(e.getButton()==3) {
				return;
			}
			GameCharacter c=Player.mouseOnCardInHand(e.getX(),e.getY());
			//if the card the mouse is on is in hand, set draggingHandCard to that
			if(c!=null&&c.playable()) {
				if(c instanceof TargetedSpellCard) {
					draggingBoardCard=c;
				}else{
					draggingHandCard=c;
				}
			}
			//else if it's a minion,hero or heropower, set draggingBoardCard to that
			else if(Player.mouseOnCardOnBoard(e.getX(),e.getY())!=null) {
				draggingBoardCard=Player.mouseOnCardOnBoard(e.getX(),e.getY());
			}else if(Player.mouseOnHero(e.getX(),e.getY())!=null) {
				draggingBoardCard=Player.mouseOnHero(e.getX(),e.getY());
			}else if(Player.mouseOnHeroPower(e.getX(),e.getY())!=null&&Player.mouseOnHeroPower(e.getX(),e.getY()).playable()) {
				draggingBoardCard=Player.mouseOnHeroPower(e.getX(),e.getY());
			}
			endGame();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==3) {
			return;
		}
		int x=e.getX();
		int y=e.getY();
		if(r.isStarted()) {
			// dragging a card and it's in play area, put it in play and set dragging  card to null
			if(draggingHandCard!=null) {
				//if the card being dragged has a targettedbattlecry
				if(draggingHandCard instanceof TargettedBattlecryMinion) {
					if(AiHero.mouseOnCardOnBoard(x,y)!=null&&
							draggingHandCard.validTarget(AiHero.mouseOnCardOnBoard(x,y))){

						((TargettedBattlecryMinion)draggingHandCard).setTarget(AiHero.mouseOnCardOnBoard(x,y));

					}//mouse over enemy hero
					else if(AiHero.mouseOnHero(x, y)!=null&&
							draggingHandCard.validTarget(AiHero.mouseOnHero(x, y))) {

						((TargettedBattlecryMinion)draggingHandCard).setTarget(AiHero);

					}//mouse over allied minion on board
					else if(Player.mouseOnCardOnBoard(x,y)!=null&&
							draggingHandCard.validTarget(Player.mouseOnCardOnBoard(x,y))) {

						((TargettedBattlecryMinion)draggingHandCard).setTarget(Player.mouseOnCardOnBoard(x,y));

					}//mouse over player hero
					else if(Player.mouseOnHero(x,y)!=null&&
							draggingHandCard.validTarget(Player.mouseOnHero(x,y))) {

						((TargettedBattlecryMinion)draggingHandCard).setTarget(Player.mouseOnHero(x,y));
					}
					if(((TargettedBattlecryMinion)draggingHandCard).getTarget()!=null) {
						draggingHandCard.play();
						((TargettedBattlecryMinion)draggingHandCard).setTarget(null);
					}
				}
				//else if it's a minion without a targettedbattlecry
				else if(x>250&&x<1650&&y<650) {
					draggingHandCard.play();
					
				}
				draggingHandCard=null;
				
			}

			//if the card being dragged is on the board(or hero power)
			if(draggingBoardCard!=null) {
				//attack the character that the mouse is over if it's a valid target
				//mouse over enemy minion on board
				if(AiHero.mouseOnCardOnBoard(x,y)!=null&&
						draggingBoardCard.validTarget(AiHero.mouseOnCardOnBoard(x,y))){
					draggingBoardCard.attack(AiHero.mouseOnCardOnBoard(x,y));
				}//mouse over enemy hero
				else if(AiHero.mouseOnHero(x, y)!=null&&
						draggingBoardCard.validTarget(AiHero.mouseOnHero(x, y))) {
					draggingBoardCard.attack(AiHero);
				}//mouse over allied minion on board
				else if(Player.mouseOnCardOnBoard(x,y)!=null&&
						draggingBoardCard.validTarget(Player.mouseOnCardOnBoard(x,y))) {
					draggingBoardCard.attack(Player.mouseOnCardOnBoard(x,y));
				}//mouse over player hero
				else if(Player.mouseOnHero(x,y)!=null&&
						draggingBoardCard.validTarget(Player.mouseOnHero(x,y))) {
					draggingBoardCard.attack(Player.mouseOnHero(x,y));
				}
				draggingBoardCard=null;
			}
			//make card snap back to hand if released outside play area and set to right size
			for(int i=0;i<Player.getHand().size();i++) {
				Player.getHand().get(i).setDragging(false);
				Player.getHand().get(i).setMouseHover(false);
			}
			//make sure all cards&objects aren't dragging
			for(int i=0;i<Player.getBoard().size();i++) {
				Player.getBoard().get(i).setDragging(false);
			}
			Player.setDragging(false);
			Player.getHeroPower().setDragging(false);
			endGame();
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(r.isStarted()) {
			//change x&y coords of card while dragging
			if(draggingHandCard!=null) {
				draggingHandCard.setDragging(true);
				draggingHandCard.setXDrag(e.getX());
				draggingHandCard.setYDrag(e.getY());
			}
			//if the mouse is moving too fast to keep card on mouse, keep the card moving
			//instead of it being stuck where it left the mouse
			else if(Player.getDraggingCard()!=null) {
				draggingHandCard.setXDrag(e.getX());
				draggingHandCard.setYDrag(e.getY());
			}
			//set x&yDrag coords to make the attack arrow move
			if(draggingBoardCard!=null) {
				draggingBoardCard.setDragging(true);
				draggingBoardCard.setXDrag(e.getX());
				draggingBoardCard.setYDrag(e.getY());
			}
			endGame();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if(r.isStarted()) {
			GameCharacter a=Player.mouseOnCardOnBoard(x, y);
			GameCharacter b=AiHero.mouseOnCardOnBoard(x, y);
			GameCharacter c=Player.mouseOnHeroPower(x, y);
			GameCharacter d=AiHero.mouseOnHeroPower(x, y);
			//makes the card increase in size when hovering over
			if(Player.mouseOnCardInHand(x, y)!=null) {
				Player.mouseOnCardInHand(x, y).setMouseHover(true);
			}else if(a!=null) {
				a.setMouseHover(true);
			}else if(b!=null) {
				b.setMouseHover(true);
			}else if(c!=null) {
				c.setMouseHover(true);
			}else if(d!=null) {
				d.setMouseHover(true);
			}
			for(int i=0;i<Player.getBoard().size();i++) {
				if(!(Player.getBoard().get(i).equals(a)))
					Player.getBoard().get(i).setMouseHover(false);
			}
			for(int i=0;i<AiHero.getBoard().size();i++) {
				if(!(AiHero.getBoard().get(i).equals(b))) 
					AiHero.getBoard().get(i).setMouseHover(false);
			}
			if(c==null)
				Player.getHeroPower().setMouseHover(false);
			if(d==null)
				AiHero.getHeroPower().setMouseHover(false);
			endGame();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(r.isStarted()) {
			if(s==(null)) {
				for(int i=0;i<Player.getProjectiles().size();i++) {
					if(Player.getProjectiles().get(i).atTarget())
						Player.getProjectiles().remove(i);
					else {
						Player.getProjectiles().get(i).incrementXY();
					}
				}
				for(int i=0;i<AiHero.getProjectiles().size();i++) {
					if(AiHero.getProjectiles().get(i).atTarget())
						AiHero.getProjectiles().remove(i);
					else {
						AiHero.getProjectiles().get(i).incrementXY();
					}
				}
				for(int i=0;i<Player.getHand().size();i++) {
					if(Player.getHand().get(i).isRecentlyDrawn()) {
						Player.getHand().get(i).incrementXYDrawAnimation();
					}
				}
				for(int i=0;i<AiHero.getHand().size();i++) {
					if(AiHero.getHand().get(i).isRecentlyDrawn()) {
						AiHero.getHand().get(i).incrementXYDrawAnimation();
					}
				}
				for(int i=0;i<Player.getBoard().size();i++) {
					if(Player.getBoard().get(i).isAttackingTowards()||
							Player.getBoard().get(i).isAttackingReturn()) {
						Player.getBoard().get(i).attackIncrement();
					}
					if(Player.getBoard().get(i).timeToKill())
						Player.getBoard().get(i).destroy();
				}
				for(int i=0;i<AiHero.getBoard().size();i++) {
					if(AiHero.getBoard().get(i).isAttackingTowards()||
							AiHero.getBoard().get(i).isAttackingReturn()) {
						AiHero.getBoard().get(i).attackIncrement();
					}
					if(AiHero.getBoard().get(i).timeToKill())
						AiHero.getBoard().get(i).destroy();
				}
				if(AiHero.isTurn()&&System.currentTimeMillis()>pause+2000&&!r.getEnd()) {
					if(Ai.takeTurn())
						pause=System.currentTimeMillis();
				}
				r.repaint();
				if(r.getEnd()) {
					if(System.currentTimeMillis()>endPause+2000) {
						timer.stop();
					}
				}
				endGame();
			}
			else if(s.equals("HowtoPlay")) {
				new HowToJFrame();
			}
		}
	}
	public long getTime() {
		return System.currentTimeMillis();
	}
	public boolean checkEnd() {
		if(Player.getHealth()<=0)
			return true;
		else if(AiHero.getHealth()<=0) 
			return true;
		return false;
	}
	public Hero heroKilled() {
		if(Player.getHealth()<=0)
			return Player;
		else if(AiHero.getHealth()<=0)
			return AiHero;
		return null;
	}
	public void endGame() {
		if(checkEnd()&&!r.getEnd()) {
			if(heroKilled().equals(Player)) {
				r.setEnd(false);
			}else if(heroKilled().equals(AiHero)) {
				r.setEnd(true);
			}
			endPause=System.currentTimeMillis();
		}
	}
}
