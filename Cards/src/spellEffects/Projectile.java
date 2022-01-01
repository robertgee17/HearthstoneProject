package spellEffects;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int x;
	private int y;
	private int size=40;
	private Color c;
	private boolean atTarget=false;
	public Projectile(int startX,int startY,int endX,int endY,Color c) {
		this.startX=startX-12;
		this.startY=startY-30;
		this.endX=endX-12;
		this.endY=endY-30;
		x=startX;
		y=startY;
		this.c=c;
	}
	public void incrementXY() {
		if(!atTarget) {
			x+=(endX-startX)/12;
			y+=(endY-startY)/12;
		}
		if(startY>=endY&&y<=endY) {
			atTarget=true;
		}
		else if(startY<=endY&&y>=endY) {
			atTarget=true;
		}else if((endY-100)<startY&&startY<(endY+100)) {
			if(startX>=endX&&x<=endX) {
				atTarget=true;
			}
			else if(startX<=endX&&x>=endX) {
				atTarget=true;
			}
		}
	}
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x-size/2, y-size/2, size, size);
		g.drawOval(x-size/2, y-size/2, size, size);
		
	}
	public boolean atTarget() {
		return atTarget;
	}
}
