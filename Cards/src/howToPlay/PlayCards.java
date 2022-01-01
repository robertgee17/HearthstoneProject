package howToPlay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayCards extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw image of playing a battlecry minion & its text
		Image img=new ImageIcon("battlecry.png").getImage();
		int width=img.getWidth(null);
		int height=img.getHeight(null);
		g.drawImage(img,100,75,(int)(width/1.4),(int)(height/1.4),null);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.drawString("Playing a Targeted Battlecry Minion or Spell", 10, 50);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Drag the card from your hand to the target you want.", 300, 300);
		
		//draw image of playing a regular minion & its text
		img=new ImageIcon("playMinion.png").getImage();
		width=img.getWidth(null);
		height=img.getHeight(null);
		g.drawImage(img,900,75,(int)(width/1.4),(int)(height/1.4),null);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.drawString("Playing a Regular Minion or Spell", 950, 50);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("Drag the card from your hand to the playing area of the board.", 1200, 300);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.drawString("A card will become outlined in green when it is playable.", 600, 800);
	}
}
