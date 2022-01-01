package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MinionVsSpell extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img=new ImageIcon("Yeti.png").getImage();
		g.drawImage(img,300,200,null);
		img=new ImageIcon("Fireball.png").getImage();
		g.drawImage(img,1200,200,null);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("Minion vs Spell Cards", 750, 35);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.drawString("Minion cards look like this:", 325, 150);
		g.drawString("Spell cards look like this:", 1225, 150);
	}
}
