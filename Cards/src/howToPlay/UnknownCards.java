package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class UnknownCards extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Don't know what a card does?", 750, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Whether a card(Minion or Spell) is in your hand or on the board,",100,100);
		g.drawString("you can hover over it to see an enlarged version with it's description.",100,150);
		g.drawString("This is also applicable to hero powers.",100,200);
		
		g.drawString("Also, when a card, spell, or hero power is played, it's image will",100,300);
		g.drawString("appear in the top-right hand corner so that you know what happened.",100,350);
		
		Image img=new ImageIcon("hoverDiagram.png").getImage();
		g.drawImage(img, 800, 75,img.getWidth(null)*2/3,img.getHeight(null)*2/3,null);
	}
}
