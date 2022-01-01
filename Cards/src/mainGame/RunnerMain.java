package mainGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;
public class RunnerMain{
	private static JFrame window;
	public static void main (String[] args){
		window = new JFrame("Hurtstone");
		window.setSize(1900,1040);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width/2-window.getSize().width/2, 0);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel gp=new GamePanel();
		window.setContentPane(gp);
		window.setVisible(true);
		window.setResizable(false);
		JMenuBar mBar = new JMenuBar();
		mBar.add(createMenu("How to Play",new String[] {"How to Play"}));
		window.setJMenuBar(mBar);
	}
	private static JMenu createMenu(String menuName, String[] items) {
		JMenu menu = new JMenu(menuName);
		for(String itemName:items) {
			JMenuItem item = new JMenuItem(itemName);
			item.addActionListener(new DropDownListener());
			menu.add(item);
		}
		return menu;
	}
}
