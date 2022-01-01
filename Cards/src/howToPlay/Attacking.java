package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Attacking extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Attacking", 850, 35);
		Image i=new ImageIcon("attackDiagram.png").getImage();
		g.drawImage(i, 300,50,null);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Click on a minion and drag until the arrow is over an enemy.", 1000, 400);
		g.drawString("A minion will become outlined in green when it can attack.", 750, 600);
	}
}
