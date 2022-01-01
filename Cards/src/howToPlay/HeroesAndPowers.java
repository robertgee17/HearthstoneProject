package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class HeroesAndPowers extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Heroes and Hero Powers", 750, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("In this game, you will control one hero throughout the game and will be able to use it's hero power.",50,250);

		g.drawString("You can see the hero power to the right of a hero you've selected in the start screen, or when you're in game.",50,350);
		
		Image img=new ImageIcon("Mage.png").getImage();
		g.drawImage(img,1200, 150,img.getWidth(null)*2/3,img.getHeight(null)*2/3,null);
		img=new ImageIcon("MagePower.png").getImage();
		g.drawImage(img, 1470, 150,img.getWidth(null)*2/3,img.getHeight(null)*2/3, null);
		/*g.drawString("Minions can be placed in the board's playing area, indicated by a box in the middle of the screen.",50,300);
		g.drawString("     (For a visual on playing cards, go to the 'Playing a Card' page.)", 50, 350);
		g.drawString("Both minions and spells can have different effects, so make sure to read each card.", 50, 400);
		*/
	}
}
