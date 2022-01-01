package howToPlay;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GenericHowTo extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(-1, 750, 251, 251);
		g.drawRect(1650, 750, 251, 251);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.drawString("BACK", 85, 860);
		g.drawString("NEXT", 1735, 860);
	}
}
