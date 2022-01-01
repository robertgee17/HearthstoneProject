package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class Intro extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Objectives of the Game", 750, 35);
		g.setFont(g.getFont().deriveFont( 15.0f ));
		g.drawString("Click on the table of contents to jump to a page", 100, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("In this game, the objective is to kill your opponent through the use of your hero power, and the minions and spells that start in your deck.",50,100);

		g.drawString("Both of you will start with 30 health, and will draw one card and gain one mana crystal at the start of each turn.",50,200);

		g.drawString("Minions can be placed in the board's playing area, indicated by a box in the middle of the screen.",50,300);
		g.drawString("     (For a visual on playing cards, go to the 'Playing a Card' page.)", 50, 350);
		g.drawString("Both minions and spells can have different effects, so make sure to read each card.", 50, 400);
		
		g.drawString("If you want to start playing right away, you can always alt-tab and reference this later.", 50, 500);
		Image img=new ImageIcon("DrBoom.png").getImage();
		g.drawImage(img,1175, 125,null);
		img=new ImageIcon("DrBoomBot.png").getImage();
		g.drawImage(img,900, 375,img.getWidth(null)*2/3,img.getHeight(null)*2/3,null);
		img=new ImageIcon("DrBoomBot.png").getImage();
		g.drawImage(img,1600, 375,img.getWidth(null)*2/3,img.getHeight(null)*2/3,null);
		/*g.drawString("Minions:   A minion can only attack once per turn.", 50, 550);
		g.drawString("        A minion can not attack the turn it is put onto the board.", 50, 600);
		g.drawString("Healing:   A character cannot be healed over it's maximum health.", 50, 650);
		*/
	}
}
