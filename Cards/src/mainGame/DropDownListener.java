package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import howToPlay.HowToJFrame;

public class DropDownListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		if(s.equals("How to Play")) {
			new HowToJFrame();
		}
	}

}
