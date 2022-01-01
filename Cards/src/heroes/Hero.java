package heroes;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import decks.Deck;
import generalClasses.Card;
import generalClasses.GameCharacter;
import heroPower.*;
import mainGame.*;
import minions.Minion;
import spellEffects.*;
import spells.Spell;

public abstract class Hero extends GameCharacter implements Cloneable {
	protected Hero enemyHero;
	protected ArrayList<GameCharacter> hand = new ArrayList<GameCharacter>();
	protected Deck deck;
	protected Board board;
	protected boolean turn;
	protected int spellDamage = 0;
	protected int maxMana = 0;
	protected HeroPower h;
	protected boolean isPlayer;
	protected Image cardback;
	protected int turnCount;
	protected int fatigueCount = 0;
	protected int armor;
	protected ArrayList<Spell> usedSpells = new ArrayList<Spell>();
	protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public Hero(String name, HeroPower h, boolean isPlayer, Image image, Image cardback) {
		super(name, 0, 0, 30, image);
		this.h = h;
		setInPlay();
		this.isPlayer = isPlayer;
		this.sizeMod = 2.3;
		this.x = 950 - (int) ((width / sizeMod) / 2);
		if (isPlayer)
			this.y = 650;
		else
			this.y = 50;
		this.cardback = cardback;
		h.addHero(this);
		this.hero = this;
		board=new Board();
	}

	public ArrayList<Projectile> getProjectiles() {
		return this.projectiles;
	}

	public void draw(Graphics g) {
		// draw green oval if it can attack
		if (canAttack() && isPlayer()) {
			g.setColor(Color.GREEN);
			g.fillOval(x, y, (int) (width / sizeMod), (int) (height / sizeMod));
		}
		if (frozen) {
			g.setColor(Color.BLUE);
			g.fillOval(x, y, (int) (width / sizeMod), (int) (height / sizeMod));
		}
		// draw hero portrait
		g.drawImage(image, x, y, (int) (width / sizeMod), (int) (height / sizeMod), null);
		g.setFont(g.getFont().deriveFont(20.0f));
		// draw name
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int stringX = x + ((int) (width / sizeMod) - metrics.stringWidth(name)) / 2;
		int stringY = y + (((int) (height / sizeMod) - metrics.getHeight()) / 2) + metrics.getAscent() + 68;
		g.setColor(Color.WHITE);
		g.drawString(name, stringX, stringY);
		// draw number of cards in deck
		g.setColor(Color.BLACK);
		g.drawString("Cards Remaining: " + deck.size(), x + 835, y + 50);
		// if deck size=0 show next fatigue damage
		if (deck.size() <= 0) {
			g.setColor(Color.BLACK);
			g.drawString("Next Fatigue Damage: " + fatigueCount, x + 800, y + 100);
		}
		// draw hero's mana
		if (isPlayer)
			g.drawString("Mana: " + Integer.toString(mana) + "/" + Integer.toString(maxMana), 1500, y + 250);
		else
			g.drawString("Mana: " + Integer.toString(mana) + "/" + Integer.toString(maxMana), 1500, y);
		// draw hero's hp
		if (isDamaged())
			g.setColor(Color.PINK);
		else
			g.setColor(Color.WHITE);
		g.drawString(Integer.toString(health), x + 138, y + 170);
		g.setColor(Color.BLACK);
		if (dragging && canAttack()) {
			g.setColor(Color.RED);
			drawAttackTriangle(g);
		}
		// draw armor
		if (hasArmor()) {
			Image i = new ImageIcon("Armor.png").getImage();
			double imageMod = 5.5;
			int width = i.getWidth(null);
			int height = i.getHeight(null);
			g.drawImage(i, x + 130, y + 90, (int) (width / imageMod), (int) (height / imageMod), null);
			g.drawString(Integer.toString(armor), x + 137, y + 118);
		}
		// draw hero power
		h.draw(g);
		// draw damage amount if hero takes damage
		for (int i = 0; i < recentDamageA.size(); i++) {
			if (System.currentTimeMillis() < this.recentDamageTimeA.get(i) + 2000
					&& System.currentTimeMillis() > this.recentDamageTimeA.get(i) + 250) {
				g.setColor(Color.BLACK);
				g.setFont(g.getFont().deriveFont( 50.0f ));
				FontMetrics metric=g.getFontMetrics(g.getFont());
				String damage="-"+Integer.toString(recentDamageA.get(i));
				g.fillRect((int)(x+width/sizeMod/2)-20+xAttackAnimation, (int)(y+height/sizeMod/2)-metric.getHeight()+24+yAttackAnimation, metric.stringWidth(damage), 46);
				g.setColor(Color.WHITE);
				g.drawString(damage, (int) (x + width / sizeMod / 2) - 20 + xAttackAnimation,
						(int) (y + height / sizeMod / 2) + yAttackAnimation);
			} else if (System.currentTimeMillis() > this.recentDamageTimeA.get(i) + 2250) {

				recentDamageA.remove(i);
				recentDamageTimeA.remove(i);
				i--;
			}
		}
		// show the heal amount if hero is healed
		if (recentHeal > 0 && System.currentTimeMillis() < this.recentHealTime + 2000
				&& System.currentTimeMillis() > this.recentHealTime + 250) {
			g.setFont(g.getFont().deriveFont(50.0f));
			String damage = "+" + Integer.toString(recentHeal);
			g.setColor(Color.WHITE);
			g.drawString(damage, (int) (x + width / sizeMod / 2) - 20 + xAttackAnimation,
					(int) (y + height / sizeMod / 2) + yAttackAnimation);
		}
		/*
		 * if(recentlyDamaged&&System.currentTimeMillis()<this.recentDamageTime+2250&&
		 * System.currentTimeMillis()>this.recentDamageTime+250) {
		 * g.setFont(g.getFont().deriveFont( 50.0f )); String
		 * damage="-"+Integer.toString(recentDamage); g.setColor(Color.WHITE);
		 * g.drawString(damage,(int)(x+width/sizeMod/2)-20,(int)(y+height/sizeMod/2)); }
		 */
	}

	// if heropower is targetted, call targettedHeroPower, else use generalHeroPower
	public boolean heroPower() {
		if (h instanceof TargetedHeroPower) {
			/*
			 * implement once find target is implemented return heroPower(findTarget());
			 */
			return true;
		} else {
			return ((GeneralHeroPower) h).heroPower();
		}
	}

	public boolean heroPower(GameCharacter target) {
		return ((TargetedHeroPower) h).heroPower(target);
	}

	// takes card from deck and adds to hand
	public void drawCard() {
		if (deck.size() > 0) {
			Card c = deck.remove(0);
			c.setRecentlyDrawn(true);
			hand.add((GameCharacter) c);
			c.setInHand();
			if (hand.size() > 10) {
				hand.remove(10);
			}
		}
		if (deck.size() <= 0) {
			this.takeDamage(fatigueCount);
			fatigueCount++;
		}
	}
	// draws x amt of cards
	public void drawCard(int amt) {
		if (amt <= 0)
			return;
		drawCard();
		drawCard(amt - 1);
	}

	@Override
	// take x amount of damage
	public void takeDamage(int damage) {
		if (hasArmor()) {
			this.armor -= damage;
			if (armor < 0) {
				this.health += armor;
				armor = 0;
			}
		} else {
			this.health -= damage;
		}
		if(damage>0) {
			this.recentDamageA.add(damage);
			this.recentDamageTimeA.add(System.currentTimeMillis());
		}
		if (this.health <= 0)
			kill();
	}
	

	// make enemy reference
	public void addEnemy(Hero enemyHero) {
		this.enemyHero = enemyHero;
		for(int i=0;i<deck.size();i++) {
			((GameCharacter)deck.get(i)).setEnemyHero(enemyHero);
		}
	}

	// set deck
	public void addDeck(Card[] deck) {
		this.deck = new Deck(deck);
	}

	public boolean play() {
		return true;
	}

	public boolean play(GameCharacter target) {
		return false;
	}

	public void kill() {

	}

	// do things necessary for start of turn
	public void startTurn() {
		drawCard();
		addMaxMana();
		restoreMana();
		for (int i = 0; i < board.size(); i++) {
			board.get(i).setSummoningSickness(false);
			board.get(i).resetAttacks();
		}
		this.resetAttacks();
		h.resetAttacks();
		turnCount++;
		turn = true;
	}

	// do things necessary for end of turn
	public void endTurn() {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).isFrozen() && board.get(i).getAttacksThisTurn() > 0) {
				board.get(i).setFrozen(false);
			}
		}
		if (this.isFrozen() && this.getAttacksThisTurn() > 0) {
			this.setFrozen(false);
		}
		turn = false;
	}

	// add mana make sure it's not more than 10
	public void addMaxMana() {
		if (maxMana < 10)
			maxMana++;
	}

	public void addMaxMana(int amt) {
		if (amt <= 0)
			return;
		addMaxMana();
		addMaxMana(amt - 1);
	}

	public void addMana() {
		if (mana < 10)
			mana++;
	}

	public void addMana(int amt) {
		if (amt <= 0)
			return;
		addMana();
		addMana(amt - 1);
	}

	// reset mana to maxMana
	public void restoreMana() {
		this.mana = maxMana;
	}

	// get a random enemy character
	public GameCharacter getRandomEnemy() {
		int x = (int) (Math.random() * (enemyHero.getBoard().size() + 1));
		if (x < enemyHero.getBoard().size())
			return enemyHero.getBoard().get(x);
		else
			return enemyHero;
	}

	// get a random enemy minion
	public GameCharacter getRandomEnemyMinion() {
		int x = (int) (Math.random() * enemyHero.getBoard().size());
		return enemyHero.getBoard().get(x);
	}

	public GameCharacter getRandomCharacter() {
		int enemyBoard = enemyHero.getBoard().size();
		int heroBoard = board.size();
		int x = (int) (Math.random() * (enemyBoard + heroBoard + 2));
		if (x < enemyBoard) {
			return enemyHero.getBoard().get(x);
		} else if ((x >= enemyBoard) && (x < enemyBoard + heroBoard)) {
			return board.get(x - enemyBoard);
		} else if (x == enemyBoard + heroBoard) {
			return this;
		} else if (x == enemyBoard + heroBoard + 1) {
			return enemyHero;
		}
		return null;
	}

	public Minion getRandomMinion() {
		int enemyBoard = enemyHero.getBoard().size();
		int heroBoard = board.size();
		int x = (int) (Math.random() * (enemyBoard + heroBoard));
		if (x < enemyBoard) {
			return enemyHero.getBoard().get(x);
		} else if ((x >= enemyBoard) && (x < enemyBoard + heroBoard)) {
			return board.get(x - enemyBoard);
		}
		return null;
	}

	// remove summoning sickness from friendly minion
	// (SS means minion can't attack the turn it's summoned)
	public void removeSummoningSickness() {
		for (int i = 0; i < board.size(); i++) {
			board.get(i).setSummoningSickness(false);
		}
	}

	// check if mouse is on a card when prompted
	public GameCharacter mouseOnCardInHand(int xCoord, int yCoord) {
		for (int i = hand.size() - 1; i >= 0; i--) {
			if (!dragging)
				hand.get(i).setMouseHover(false);
			if (hand.get(i).inCoords(xCoord, yCoord)) {
				for (int j = hand.size() - 1; j >= 0; j--) {
					hand.get(j).setMouseHover(false);
				}
				return hand.get(i);
			}
		}
		return null;
	}

	// check if mouse is on a card that's on the board
	public GameCharacter mouseOnCardOnBoard(int xCoord, int yCoord) {
		for (int i = board.size() - 1; i >= 0; i--) {
			if (board.get(i).inCoords(xCoord, yCoord))
				return board.get(i);

		}
		return null;
	}

	public GameCharacter mouseOnHero(int xCoord, int yCoord) {
		if (this.inCoords(xCoord, yCoord))
			return this;
		else {
			this.mouseHover = false;
		}
		return null;
	}

	public GameCharacter mouseOnHeroPower(int xCoord, int yCoord) {
		if (h.inCoords(xCoord, yCoord))
			return h;
		else {
			h.setMouseHover(false);
		}
		return null;
	}

	// check if a card in hand is being dragged
	public boolean draggingCard() {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).isDragging())
				return true;
		}
		return false;
	}

	// return card being dragged
	public Card getDraggingCard() {
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).isDragging())
				hand.get(i);
		}
		return null;
	}

	public boolean validTarget(GameCharacter target) {
		return (enemyHero.getBoard().contains(target) || enemyHero.equals(target));
	}

	// if the hero has any possible actions remaining, return true
	public boolean actionsRemaining() {
		// if one of your minions can attack
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).canAttack())
				return true;
		}
		// if your hero can attack
		if (this.canAttack())
			return true;
		// if the hero power is playable
		if (h.playable())
			return true;
		// if any minion in hand can be played
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).playable())
				return true;
		}
		return false;
	}

	public boolean canAttack() {
		return super.canAttack() && this.isTurn();
	}

	public int getMana() {
		return this.mana;
	}

	public int getMaxMana() {
		return this.maxMana;
	}

	public void spendMana(int manaSpent) {
		this.mana -= manaSpent;
	}

	public Board getBoard() {
		return this.board;
	}

	public ArrayList<GameCharacter> getHand() {
		return this.hand;
	}

	public Deck getDeck() {
		return this.deck;
	}

	public Hero getEnemy() {
		return this.enemyHero;
	}

	public int getSpellDamage() {
		return this.spellDamage;
	}

	public boolean isPlayer() {
		return this.isPlayer;
	}

	public boolean isTurn() {
		return this.turn;
	}

	public Image getCardback() {
		return this.cardback;
	}

	public HeroPower getHeroPower() {
		return this.h;
	}

	public int getTotalMinionAttack() {
		int total = 0;
		for (int i = 0; i < board.size(); i++)
			total += board.get(i).getAttack();
		return total;
	}

	public boolean hasArmor() {
		return armor > 0;
	}

	public int getArmor() {
		return this.armor;
	}

	public void addArmor(int amt) {
		this.armor += amt;
	}

	public ArrayList<Spell> getUsedSpells() {
		return usedSpells;
	}

	public int getFatigueCount() {
		return this.fatigueCount;
	}

	public void setHand(ArrayList<GameCharacter> hand) {
		this.hand = hand;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setUsedSpells(ArrayList<Spell> spells) {
		this.usedSpells = spells;
	}

	public void setProjectiles(ArrayList<Projectile> ar) {
		this.projectiles = ar;
	}

	@Override
	public Hero clone() {
		Hero temp = (Hero) super.clone();
		temp.setProjectiles((ArrayList<Projectile>) temp.getProjectiles().clone());
		return temp;
	}
}
