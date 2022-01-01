package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartScreen extends JPanel{
	private GamePanel gp;
	private StartHero mage=new StartHero("Mage");
	private StartHero hunter=new StartHero("Hunter");
	private StartHero paladin=new StartHero("Paladin");
	private StartHero warrior=new StartHero("Warrior");
	private StartHero chosenHero;
	private StartHero[][] heroes=new StartHero[][] {{mage,hunter},{paladin,warrior}};
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(g.getFont().deriveFont( 50.0f ));
		g.drawString("Choose Your Hero:", 450, 100);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		for(int i=0;i<heroes.length;i++) {
			for(int j=0;j<heroes[0].length;j++) {
				//draw cyan circle around hero portrait when chosen
				if(heroes[i][j].isChosen()) {
					g.setColor(Color.CYAN);
					g.fillOval(300+400*i, 150+400*j, heroes[i][j].getWidth(), heroes[i][j].getHeight());
				}
				heroes[i][j].draw(g, 300+400*i, 150+400*j);	
			}
		}
		//draw chosen hero on the right side
		if(chosenHero!=null) {
			chosenHero.draw(g, 1300, 250);
			chosenHero.drawPower(g, 1570, 250);
		}
		
		g.setFont(g.getFont().deriveFont( 50.0f ));
		g.setColor(Color.BLACK);
		g.drawString("Your Hero", 1430, 200);
		g.setFont(g.getFont().deriveFont( 30.0f ));
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 200, 200);
		g.drawString("How to Play", 25, 110);
		//draw start button
		if(chosenHero!=null) {
			g.setColor(Color.CYAN);
			
		}else {
			g.setColor(Color.GRAY);
		}
		g.fillOval(1475, 700, 200, 200);
		g.setColor(Color.BLACK);
		g.setFont(g.getFont().deriveFont( 50.0f ));
		g.drawOval(1475, 700, 200, 200);
		g.drawString("Start", 1520, 820);
	}
	public void addGP(GamePanel gp) {
		this.gp=gp;
	}
	public GamePanel getPanel() {
		return this.gp;
	}
	public StartHero inCoords(int x,int y) {
		if(x<300+mage.getWidth()&&
				x>300&&
				y<150+mage.getHeight()&&
				y>150)
			return mage;
		else if(x<300+hunter.getWidth()&&
				x>300&&
				y<550+hunter.getHeight()&&
				y>550)
			return hunter;
		else if(x<700+paladin.getWidth()&&
				x>700&&
				y<150+paladin.getHeight()&&
				y>150)
			return paladin;
		else if(x<700+warrior.getWidth()&&
				x>700&&
				y<550+warrior.getHeight()&&
				y>550)
			return warrior;
		return null;
	}
	public void setChosen(StartHero chosen) {
		for(int i=0;i<heroes.length;i++) {
			for(int j=0;j<heroes[0].length;j++) {
				heroes[i][j].setChosen(false);	
			}
		}
		chosenHero=chosen;
		chosen.setChosen(true);
	}
	public StartHero getChosen() {
		return this.chosenHero;
	}
}
