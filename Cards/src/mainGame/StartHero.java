package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StartHero {
	private Image img;
	private int width;
	private int height;
	private double sizeMod=1.5;
	private String name;
	private Image power;
	private int powerWidth;
	private int powerHeight;
	private double powerSizeMod=1.5;
	private boolean chosen;
	public StartHero(String name) {
		this.img=new ImageIcon(name+".png").getImage();
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
		this.name=name;
		this.power=new ImageIcon(name+"Power.png").getImage();
		this.powerWidth=power.getWidth(null);
		this.powerHeight=power.getHeight(null);
	}
	public void draw(Graphics g,int x,int y) {
		g.drawImage(img,x,y,(int)(width/sizeMod),(int)(height/sizeMod),null);
		g.setColor(Color.WHITE);
		g.drawString(name, x+90, y+300);
	}
	public void drawPower(Graphics g,int x,int y) {
		g.drawImage(power,x,y,(int)(powerWidth/powerSizeMod),(int)(powerHeight/powerSizeMod),null);
	}
	public String getName() {
		return this.name;
	}
	public void setChosen(boolean chosen) {
		this.chosen=chosen;
	}
	public boolean isChosen() {
		return this.chosen;
	}
	public int getWidth() {
		return (int)(width/sizeMod);
	}
	public int getHeight() {
		return (int)(height/sizeMod);
	}
}
