package minions;

import javax.swing.ImageIcon;

import heroes.Hero;

public class Ogre extends Minion{
	public Ogre(Hero hero) {
		super("Ogre",6,6,7,hero,
				new ImageIcon("Ogre.png").getImage(),
				new ImageIcon("OgreM.png").getImage());
	}
}
