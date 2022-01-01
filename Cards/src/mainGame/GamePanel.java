package mainGame;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private Runner runner;
	private StartScreen start;
	private CardLayout cardLayout;
	private RunnerListener listener;
	private JPanel screenPanel;
	public GamePanel() {
		cardLayout=new CardLayout();
		runner = new Runner(this);
		listener=new RunnerListener(runner);
		this.setLayout(cardLayout);
		start = new StartScreen();
		screenPanel = new JPanel(); 
		cardLayout = new CardLayout();
		screenPanel.setLayout(cardLayout);
		screenPanel.add(start, "StartScreen");	
		screenPanel.add(runner, "RunScreen");
		StartListener sListener=new StartListener(start);
		start.addGP(this);
		this.add(screenPanel);
		cardLayout.show(screenPanel, "StartScreen");
	}
	public RunnerListener getListener() {
		return this.listener;
	}
	public void startGame() {
		cardLayout.show(screenPanel,"RunScreen");
	}
	public Runner getRunner() {
		return this.runner;
	}
}
