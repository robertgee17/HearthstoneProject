package mainGame;
import java.util.ArrayList;


import minions.*;

public class Board extends ArrayList<Minion>{
	ArrayList<Integer> i = new ArrayList<Integer>();
	//add minion to board
	public boolean add(Minion m) {
		if(size()>=7) {
			m.removeFromPlay();
			return false;
		}
		return super.add(m);
	}
	public void add(int index,Minion m) {
		if(size()>=7) {
			m.removeFromPlay();
			return;
		}
		if(index<0)
			index=0;
		super.add(index,m);
	}
	//if 7 minions are on board, can't play any more
	public boolean playable() {
		if(size()>=7)
			return false;
		else
			return true;
	}
	public int getMinionAttack() {
		int attack=0;
		for(int i=0;i<this.size();i++) {
			if(!this.get(i).setToDie())
				if(this.get(i) instanceof WindfuryMinion)
					attack+=this.get(i).getAttack();
				attack+=this.get(i).getAttack();
		}
		return attack;
	}
	public double getMinionHealth() {
		double health=0;
		for(int i=0;i<this.size();i++) {
			if(!this.get(i).setToDie())
				health+=2*Math.sqrt(this.get(i).getHealth());
		}
		return health;
	}
	public int actualSize() {
		int size=0;
		for(int i=0;i<super.size();i++) {
			if(!this.get(i).setToDie())
				size++;
		}
		return size;
	}
}
