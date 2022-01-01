package howToPlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HowToListener implements ActionListener,MouseListener{
	HowToPanel p;
	public HowToListener(HowToPanel p) {
		this.p=p;
		p.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		// TODO Auto-generated method stub
		if(x<250&&y>750) {
			p.showLast();
		}else if(x>1650&&y>750) {
			p.showNext();
		}
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		if(p.containsSlide(s))
			p.showSlide(s);
	}

}
