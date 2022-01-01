package heroes;

import java.util.ArrayList;
import java.util.Objects;

import decks.Deck;
import generalClasses.GameCharacter;
import heroPower.*;
import mainGame.Board;
import mainGame.Runner;
import minions.*;
import spells.*;
public class AI{
	private Hero ai;
	private Hero player;
	private Runner r;

	public Hero testPlayerHero;
	private Hero testAiHero;
	private Board testPlayerBoard;
	private Board testAiBoard;
	private ArrayList<GameCharacter> testPlayerHand;
	private ArrayList<GameCharacter> testAiHand;
	private Deck testPlayerDeck;
	private Deck testAiDeck;
	private HeroPower testAiPower;
	private ArrayList<Spell> testAiSpells;

	public AI(Hero ai,Runner r) {
		this.ai=ai;
		this.player=ai.getEnemy();
		this.r=r;
	}
	public boolean takeTurn() {
		if(ai.isTurn()) {
			if(ai.actionsRemaining()==false) {
				ai.endTurn();
				player.startTurn();
				r.setStartTurn(System.currentTimeMillis());
				return true;
			}
			resetTheoreticalBoard();
			
				if(ai.getBoard().getMinionAttack()>=(player.getHealth()+player.getArmor())){
					for(int i=0;i<ai.getBoard().size();i++) {
						if(ai.getBoard().get(i).canAttack()) {
							ai.getBoard().get(i).attack(player);
							return true;
						}
					}
				}
				for(int i=0;i<ai.getBoard().size();i++) {
					if(ai.getBoard().get(i).getAttack()>=player.getHealth()*2&&
							player.getBoard().getMinionAttack()<ai.getHealth()) {
						ai.getBoard().get(i).attack(player);
						return true;
					}
				}
				double score = playScore();
				boolean inHand=false;
				int savedIndex=-1;
				resetTheoreticalBoard();
				//test playing each card
				for(int i=0;i<testAiHand.size();i++) {
					resetTheoreticalBoard();
					GameCharacter temp=testAiHand.get(i);
					GameCharacter real=ai.getHand().get(i);
					boolean tempInHand=false;
					//if it's a non-targetted battlecry minion
					//or it's a non-targetted spell
					//test play it
					if(real.playable()) {
						if((temp instanceof Minion &&
								((Minion)temp).getTb()==null)||
								(temp instanceof Spell&&
										((Spell)temp).getTs()==null)) {
							tempInHand=temp.isInHand();
							temp.play();

							//if it has the best score, save the play
							if(playScore()>=score) {
								score=playScore();
								inHand=tempInHand;
								savedIndex=i;
							}
						}
						//if its a targetted battlecry minion, test every possible battlecry target
						else if(temp instanceof TargettedBattlecryMinion&&
								temp.isInHand()) {
							for(int j=0;j<numCharacters();j++) {
								temp=testAiHand.get(i);
								if(temp.validTarget(getTarget(j))) {
									//test play on target
									tempInHand=temp.isInHand();
									temp.play(getTarget(j));
									//if it has the best score, save the play
									if(playScore()>score) {
										score=playScore();
										inHand=tempInHand;
										savedIndex=i;
									}
								}
								resetTheoreticalBoard();
							}

						}
						//if its a targetted spell, test every possible spell target
						else if(temp instanceof Spell&&
								((Spell)temp).getTs()!=null) {
							for(int j=0;j<numCharacters();j++) {
								resetTheoreticalBoard();
								temp=testAiHand.get(i);
								if(temp.validTarget(getTarget(j))) {
									//test play on target
									tempInHand=temp.isInHand();
									temp.attack(getTarget(j));
									//if it has the best score, save the play
									if(playScore()>score) {
										score=playScore();
										inHand=tempInHand;
										savedIndex=i;
									}
								}
							}
						}
					}
					resetTheoreticalBoard();
				}
				resetTheoreticalBoard();
				//test each minion attacking everything
				for(int i=0;i<testAiBoard.size();i++) {
					GameCharacter temp;
					GameCharacter real=ai.getBoard().get(i);
					boolean tempInHand=false;
					if(real.canAttack()) {
						for(int j=0;j<testPlayerBoard.size();j++) {
							resetTheoreticalBoard();
							temp=testAiBoard.get(i);
							tempInHand=temp.isInHand();
							GameCharacter tempEnemy=testPlayerBoard.get(j);
							//test attack each enemy
							temp.attack(tempEnemy);
							//if it has the best score, save the play
							if(playScore()>score) {
								score=playScore();
								inHand=tempInHand;
								savedIndex=i;
							}
						}
						resetTheoreticalBoard();
						temp=testAiBoard.get(i);
						//test attack enemy hero
						tempInHand=temp.isInHand();
						temp.attack(testPlayerHero);
						//if it has the best score, save the play
						if(playScore()>score) {
							score=playScore();
							inHand=tempInHand;
							savedIndex=i;
						}
					}
				}
				//test playing the hero power
				if(ai.getHeroPower().getPower() instanceof GeneralHeroPower) {
					//System.out.println("Testing General");
					resetTheoreticalBoard();
					testAiPower.attack(null);
					if(playScore()>score) {
						score=playScore();
						inHand=false;
						savedIndex=-2;
					}
				}else if(ai.getHeroPower().getPower() instanceof TargetedHeroPower) {
					//System.out.println("Testing Targetted");
					for(int j=0;j<numCharacters();j++) {
						resetTheoreticalBoard();
						if(testAiPower.validTarget(getTarget(j))) {
							//test play on target
							testAiPower.attack(getTarget(j));
							//if it has the best score, save the play
							if(playScore()>score) {
								score=playScore();
								inHand=false;
								savedIndex=-2;
							}
						}
					}
				}

				resetTheoreticalBoard();
				//if doing nothing is the best play, save doing nothing
				if(playScore()>score) {
					score=playScore();
					savedIndex=-1;
				/*	System.out.println("-----------SAVED-----------");
					System.out.println("Score: "+score);
					System.out.println("ENDED TURN");*/
				}
				/*
				 * ------------------------------HERE IS WHERE IT TRIES TO PLAY THE STUFF-------------------------------
				 */
				if(savedIndex==-1) {
					resetTheoreticalBoard();
					ai.endTurn();
					player.startTurn();
					r.setStartTurn(System.currentTimeMillis());
					//System.out.println("_________________________________ENDED TURN_________________________________");
					return true;
				}else {
					//System.out.println("=================================ACTION OCCURING=================================");
					//System.out.println("Index: "+savedIndex);
					//do everything again and when it finds the play with the highest score, do that play for real
					resetTheoreticalBoard();
					//test playing each card
					GameCharacter bestPlay=resetBestPlay(inHand,savedIndex);
					GameCharacter realPlay=setRealPlay(inHand,savedIndex);
					//System.out.println(realPlay);
					//System.out.println("RealPlayInHand: "+inHand);
					ArrayList<GameCharacter> realA=realPlay.validTargets();
					//if it's a non-targetted battlecry minion
					//or it's a non-targetted spell
					//test play it
					if(inHand) {
						if((bestPlay instanceof Minion &&
								((Minion)bestPlay).getTb()==null)||
								(bestPlay instanceof Spell&&
										((Spell)bestPlay).getTs()==null)) {
							bestPlay.play();
							//if it has the best score, save the play
							if(playScore()>=score) {
								ensureCorrectHero();
								//System.out.println("PLAYING MINION/SPELL: "+realPlay);
								//if i don't set hero hero for untargetted spells, the hero for the effect doesn't set correctly
								realPlay.setHero(ai);
								realPlay.play();
								return true;
							}
						}
						//if its a targetted battlecry minion, test every possible battlecry target
						else if(bestPlay instanceof TargettedBattlecryMinion&&
								bestPlay.isInHand()) {
							for(int j=0;j<numCharacters();j++) {
								resetTheoreticalBoard();
								bestPlay=resetBestPlay(inHand,savedIndex);
								if(bestPlay.validTarget(getTarget(j))) {
									//test play on target
									bestPlay.play(getTarget(j));
									//if it has the best score, save the play
									if(playScore()>=score) {
										ensureCorrectHero();
										//System.out.println("PLAYING TARGETTED BATTLECRY: "+realPlay);
										//System.out.println("TARGET: "+realA.get(j));
										realPlay.play(getRealTarget(j));
										return true;
									}
								}
							}
						}
						//if its a targetted spell, test every possible spell target
						else if(bestPlay instanceof Spell&&
								((Spell)bestPlay).getTs()!=null) {
							for(int j=0;j<numCharacters();j++) {
								resetTheoreticalBoard();
								bestPlay=resetBestPlay(inHand,savedIndex);
								if(bestPlay.validTarget(getTarget(j))) {
									//test play on target
									bestPlay.attack(getTarget(j));
									//if it has the best score, save the play
									if(playScore()>=score) {
										ensureCorrectHero();
										realPlay.attack(getRealTarget(j));
										//System.out.println("PLAYING SPELL: "+realPlay);
										return true;
									}
								}
							}
						}
					}else if(!inHand&&bestPlay instanceof Minion) {
						resetTheoreticalBoard();
						bestPlay=resetBestPlay(inHand,savedIndex);
						//test each minion attacking everything
						for(int j=0;j<testPlayerBoard.size()+1;j++) {
							resetTheoreticalBoard();
							bestPlay=resetBestPlay(inHand,savedIndex);

							if(j<testPlayerBoard.size()) {
								GameCharacter tempEnemy=testPlayerBoard.get(j);
								GameCharacter realEnemy=player.getBoard().get(j);
								//test attack each enemy
								//System.out.println("Real Play"+realPlay);
								bestPlay.attack(tempEnemy);
								//System.out.println("Real Play"+realPlay);
								//if it has the best score, save the play
								if(playScore()>=score) {
									ensureCorrectHero();
									//System.out.println("ATTACKING MINION: "+realPlay);
									realPlay.attack(realEnemy);
									return true;
								}
							}else {
								//test attack enemy hero
								bestPlay.attack(testPlayerHero);
								//if it has the best score, save the play
								if(playScore()>=score) {
									ensureCorrectHero();
									realPlay.attack(player);
									//System.out.println("ATTACKING HERO: "+realPlay);
									//System.out.println("ACTIONS REMAINING: "+ai.actionsRemaining());
									return true;
								}
							}
						}
					}else if(!inHand&&bestPlay instanceof HeroPower) {
						
						if(((HeroPower)bestPlay).getPower() instanceof GeneralHeroPower) {
							resetTheoreticalBoard();
							bestPlay.attack(null);
							if(playScore()>=score) {
								//System.out.println("PLAYING GHeroPower: "+realPlay);
								ensureCorrectHero();
								ai.getHeroPower().attack(null);
								return true;
							}
						}else {
							for(int j=0;j<numCharacters();j++) {
								resetTheoreticalBoard();
								bestPlay=resetBestPlay(inHand,savedIndex);
								if(bestPlay.validTarget(getTarget(j))) {
									//test play on target
									bestPlay.attack(getTarget(j));
									//if it has the best score, save the play
									if(playScore()>=score) {
										//System.out.println("PLAYING HeroPower: "+realPlay);
										ensureCorrectHero();
										ai.getHeroPower().attack(getRealTarget(j));
										return true;
									}
								}
							}
						}
					}

				}
			
		}
		//while loop ends here
		ensureCorrectHero();
		return false;
	}
	public double aiScore() {
		if(testPlayerHero.getHealth()<=0)
			return Integer.MAX_VALUE;
		else if(testAiHero.getHealth()<=0)
			return Integer.MIN_VALUE;
		else {
			return
					2*Math.sqrt(testAiHero.getHealth()+testAiHero.getArmor())+
					2*testAiHero.getHand().size()+
					Math.sqrt(testAiDeck.size())-
					testAiHero.getFatigueCount()+
					1.5*testAiBoard.getMinionAttack()+
					0.6*testAiBoard.getMinionHealth()+
					2*Math.sqrt(testAiBoard.actualSize());	
		}
	}
	public double playerScore() {
		if(testAiHero.getHealth()<=0)
			return Integer.MAX_VALUE;
		else if(testPlayerHero.getHealth()<=0)
			return Integer.MIN_VALUE;
		else {
			return
					2*Math.sqrt(testPlayerHero.getHealth()+testPlayerHero.getArmor())+
					2*testPlayerHero.getHand().size()+
					Math.sqrt(testPlayerDeck.size())-
					testPlayerHero.getFatigueCount()+
					1.5*testPlayerBoard.getMinionAttack()+
					testPlayerBoard.getMinionHealth()+
					4*Math.sqrt(testPlayerBoard.actualSize());
		}
	}
	public double playScore() {
		return aiScore()-playerScore();
	}
	public int getMinionAttack(Hero hero) {
		int attackSum=0;
		for(int i=0;i<hero.getBoard().size();i++) {
			attackSum+=hero.getBoard().get(i).getAttack();
		}
		return attackSum;
	}
	public int getMinionHealth(Hero hero) {
		int healthSum=0;
		for(int i=0;i<hero.getBoard().size();i++) {
			healthSum+=hero.getBoard().get(i).getHealth();
		}
		return healthSum;
	}
	public int boardClearedScore(Hero hero,Board board) {
		if(board.size()<=0) {
			return 2+hero.getMaxMana();
		}
		return 0;
	}
	public boolean enemyBoardCleared(Hero hero) {
		return hero.getEnemy().getBoard().size()==0;
	}
	public GameCharacter resetBestPlay(boolean inHand,int savedIndex) {
		if(inHand) {
			return testAiHand.get(savedIndex);
		}else {
			if(savedIndex==-2)
				return testAiPower;
			return (GameCharacter)testAiBoard.get(savedIndex);
		}
	}
	public GameCharacter setRealPlay(boolean inHand,int savedIndex) {
		if(inHand) {
			return ai.getHand().get(savedIndex);
		}else {
			if(savedIndex==-2)
				return ai.getHeroPower();
			return (GameCharacter)ai.getBoard().get(savedIndex);
		}
	}
	public void resetTheoreticalBoard() {
		//clone heroes and set right enemyHero
		testPlayerHero=(Hero)(player.clone());
		testAiHero=(Hero)(ai.clone());
		testPlayerHero.addEnemy(testAiHero);
		testAiHero.addEnemy(testPlayerHero);
		testPlayerHero.setName("testPlayer");
		testAiHero.setName("testAi");

		//clone hand,board,deck,heroPower
		testPlayerHand=(ArrayList<GameCharacter>)(player.getHand().clone());
		testPlayerHero.setHand(testPlayerHand);

		testPlayerBoard=(Board)(player.getBoard().clone());
		testPlayerHero.setBoard(testPlayerBoard);

		testPlayerDeck=(Deck)(player.getDeck().clone());
		testPlayerHero.setDeck(testPlayerDeck);

		//clone hand,board,deck,heroPower,usedSpellsList
		testAiHand=(ArrayList<GameCharacter>)(ai.getHand().clone());
		testAiHero.setHand(testAiHand);

		testAiBoard=(Board)(ai.getBoard().clone());
		testAiHero.setBoard(testAiBoard);

		testAiDeck=(Deck)(ai.getDeck().clone());
		testAiHero.setDeck(testAiDeck);

		testAiPower=(HeroPower)(ai.getHeroPower().clone());
		testAiPower.setHero(testAiHero);

		testAiSpells=(ArrayList<Spell>)ai.getUsedSpells().clone();
		testAiHero.setUsedSpells(testAiSpells);

		//clone each card in hand and set hero
		for(int i=0;i<testAiHand.size();i++) {
			testAiHand.set(i, testAiHand.get(i).clone());
			testAiHand.get(i).setHero(testAiHero);
		}
		//clone each card on ai board and set hero
		for(int i=0;i<testAiBoard.size();i++) {
			testAiBoard.set(i, (Minion)testAiBoard.get(i).clone());
			testAiBoard.get(i).setHero(testAiHero);
		}
		//clone each card on player board and set hero
		for(int i=0;i<testPlayerBoard.size();i++) {
			testPlayerBoard.set(i, (Minion)testPlayerBoard.get(i).clone());
			testPlayerBoard.get(i).setHero(testPlayerHero);
		}
		for(int i=0;i<testAiHero.getUsedSpells().size();i++) {
			testAiHero.getUsedSpells().set(i, (Spell)testAiHero.getUsedSpells().get(i).clone());
			testAiHero.getUsedSpells().get(i).setHero(testAiHero);
		}
	}
	public void ensureCorrectHero() {
		for(int i=0;i<ai.getHand().size();i++) {
			ai.getHand().get(i).setHero(ai);
		}
		for(int i=0;i<player.getHand().size();i++) {
			player.getHand().get(i).setHero(player);
		}
		for(int i=0;i<ai.getBoard().size();i++) {
			ai.getBoard().get(i).setHero(ai);
		}
		for(int i=0;i<player.getBoard().size();i++) {
			player.getBoard().get(i).setHero(player);
		}
		for(int i=0;i<ai.getUsedSpells().size();i++) {
			ai.getUsedSpells().get(i).setHero(ai);
		}
		ai.getHeroPower().setHero(ai);
		player.getHeroPower().setHero(player);
	}
	public int numCharacters() {
		return testPlayerBoard.size()+testAiBoard.size()+2;
	}
	public GameCharacter getTarget(int j) {
		if(j>(testPlayerBoard.size()+testAiBoard.size()+1))
			return null;
		if(j<testPlayerBoard.size())
			return testPlayerBoard.get(j);
		else if(j>=testPlayerBoard.size()&&j<testAiBoard.size()+testPlayerBoard.size())
			return testAiBoard.get(j-testPlayerBoard.size());
		else if(j==testAiBoard.size()+testPlayerBoard.size())
			return testPlayerHero;
		else if(j==testAiBoard.size()+testPlayerBoard.size()+1)
			return testAiHero;
		return null;
	}
	public GameCharacter getRealTarget(int j) {
		if(j>(player.getBoard().size()+ai.getBoard().size()+1))
			return null;
		if(j<player.getBoard().size())
			return player.getBoard().get(j);
		else if(j>=player.getBoard().size()&&j<ai.getBoard().size()+player.getBoard().size())
			return ai.getBoard().get(j-player.getBoard().size());
		else if(j==ai.getBoard().size()+player.getBoard().size())
			return player;
		else if(j==ai.getBoard().size()+player.getBoard().size()+1)
			return ai;
		return null;
	}
}
