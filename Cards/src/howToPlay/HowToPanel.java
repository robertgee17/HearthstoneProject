package howToPlay;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class HowToPanel extends JPanel{
	private JPanel intro=new Intro();
	private JPanel slide2=new MinionDiagram();
	private JPanel playCards=new PlayCards();
	private JPanel attacking=new Attacking();
	private JPanel glossary=new Glossary();
	private JPanel rules=new Rules();
	private JPanel winning=new Winning();
	private JPanel unknownCards=new UnknownCards();
	private JPanel minionVsSpells=new MinionVsSpell();
	private JPanel heroesAndPowers=new HeroesAndPowers();
	private JPanel[] panels=new JPanel[] {intro,heroesAndPowers,minionVsSpells,slide2,playCards,attacking,glossary,rules,unknownCards};
	private String[] names=new String[]{"Intro","Heroes and Hero Powers","Minion vs Spell Cards","Minion Diagram",
			"Playing a Card","How to Attack","Glossary","Rules","Don't know what a card does?"};
	private int i=0;
	private CardLayout cardLayout;
	private ActionListener l;
	private JPanel screenPanel;
	public HowToPanel() {
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		screenPanel = new JPanel(); 
		cardLayout = new CardLayout();
		screenPanel.setLayout(cardLayout);
		for(int i=0;i<panels.length;i++) {
			screenPanel.add(panels[i],
					names[i]);
		}
		//screenPanel.add(intro,"intro");
		l=new HowToListener(this);
		//start.addGP(this);
		this.add(screenPanel);
		cardLayout.show(screenPanel,"Intro");
	}
	public String[] getNames() {
		return this.names;
	}
	public ActionListener getListener() {
		return this.l;
	}
	public void showNext() {
		if(i<panels.length-1) {
			i++;
		}else if(i==panels.length-1) {
			i=0;
		}
		cardLayout.show(screenPanel, names[i]);
	}
	public void showLast() {
		if(i>0) {
			i--;
		}else if(i==0) {
			i=panels.length-1;
		}
		cardLayout.show(screenPanel, names[i]);
	}
	public void showSlide(String slide) {
		i=slideNum(slide);
		cardLayout.show(screenPanel,slide);
	}
	public int slideNum(String slide) {
		for(int i=0;i<names.length;i++) {
			if(names[i].equals(slide))
				return i;
		}
		return 0;
	}
	public boolean containsSlide(String slide) {
		for(int i=0;i<names.length;i++) {
			if(names[i].equals(slide))
				return true;
		}
		return false;
	}

}
