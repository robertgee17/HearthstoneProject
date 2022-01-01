package howToPlay;

import java.awt.Graphics;

public class Winning extends GenericHowTo{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 40.0f ));
		g.drawString("How to Win", 850, 35);
		g.setFont(g.getFont().deriveFont( 20.0f ));
		g.drawString("In order to win the game, you must kill the enemy hero be reducing ",100,100);
		g.drawString("his/her hitpoints below 0 before they are able to do the same to you.",100,150);
	}
}
