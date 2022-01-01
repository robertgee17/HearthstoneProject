package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MinionDiagram extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Image i=new ImageIcon("attackHealthDiagram.png").getImage();
		g.drawImage(i,250,25,null);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Minion and Card Diagram", 750, 35);
	}
}
