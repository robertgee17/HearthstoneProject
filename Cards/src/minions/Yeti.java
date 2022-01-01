package minions;
import java.awt.Image;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Yeti extends Minion{
	public Yeti(Hero hero) {
		super("Yeti",4,4,5,hero,
				new ImageIcon("Yeti.png").getImage(),
				new ImageIcon("YetiM.png").getImage());
	}
}
