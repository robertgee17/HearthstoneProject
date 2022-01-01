package mainGame;

import java.awt.event.MouseEvent;
import heroes.*;
import java.awt.event.MouseListener;
import javax.swing.*;

import howToPlay.HowToJFrame;

public class StartListener implements MouseListener{
	private StartScreen start;
	public StartListener(StartScreen start) {
		this.start=start;
		this.start.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		if(x<200&&y<200) {
			new HowToJFrame();
		}
		if(start.inCoords(x, y)!=null) {
			start.setChosen(start.inCoords(x, y));
		}
		if(x>1475&&x<1675&&y>700&&y<900&&start.getChosen()!=null) {
			if(start.getChosen().getName().equals("Mage"))
				start.getPanel().getRunner().setPlayerHero(new Mage("You",true));
			else if(start.getChosen().getName().equals("Hunter"))
				start.getPanel().getRunner().setPlayerHero(new Hunter("You",true));
			else if(start.getChosen().getName().equals("Paladin"))
				start.getPanel().getRunner().setPlayerHero(new Paladin("You",true));
			else if(start.getChosen().getName().equals("Warrior"))
				start.getPanel().getRunner().setPlayerHero(new Warrior("You",true));
			start.getPanel().getRunner().initialize();
			start.getPanel().startGame();
		}
		start.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
