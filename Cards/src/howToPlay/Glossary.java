package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Glossary extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Glossary", 850, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Attacking:   When one character attacks another, both take damage equal to the others’ attack value.",50,50);
		g.drawString("Armor:   When a hero has armor, all damage it takes is dealt to the armor instead of it's health.", 50, 100);
		g.drawString("Battlecry:   Effect that occurs when you play a minion from your hand onto the board.",50,150);
		g.drawString("Charge:   A minion with charge can attack the turn it is played.",50,200);
		g.drawString("Discard:  Removes a card from your hand.", 50, 250);
		g.drawString("Deathrattle:   Effect occurring when a minion dies.",50,300);
		g.drawString("Hero Power:   Effect that can be played once per turn for 2 mana.",50,350);
		g.drawString("Minion:   Character that can be played and put onto field.",50,400);
		g.drawString("Spell:   Has an effect that occurs when that card is played.",50,450);
		g.drawString("Windfury:   A minion with windfury can attack twice per turn.",50,500);
		
		Image img=new ImageIcon("TheGeneral.png").getImage();
		g.drawImage(img,1175, 125,null);
	}
}
