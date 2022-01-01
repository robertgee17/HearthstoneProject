package howToPlay;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import mainGame.GamePanel;

public class HowToJFrame {
	private JFrame window;
	public HowToJFrame() {
		window=new JFrame("How to Play");
		window.setSize(1900,1000);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, 0);
		window.setVisible(true);
		HowToPanel hp=new HowToPanel();
		window.setContentPane(hp);
		window.setVisible(true);
		window.setResizable(false);
		JMenuBar mBar = new JMenuBar();
		mBar.add(createMenu("Table of Contents",hp.getListener(),hp.getNames()));
		window.setJMenuBar(mBar);
	}
	private static JMenu createMenu(String menuName, ActionListener l, String[] items) {
		JMenu menu = new JMenu(menuName);
		for(String itemName:items) {
			JMenuItem item = new JMenuItem(itemName);
			item.addActionListener(l);
			menu.add(item);
		}
		return menu;
	}
}
