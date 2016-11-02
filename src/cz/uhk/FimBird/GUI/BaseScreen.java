package cz.uhk.FimBird.GUI;

import javax.swing.JPanel;

public abstract class BaseScreen extends JPanel {
	
	protected MainFrame mainFrame;
	
	public BaseScreen(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		setLayout(null);
	}
}
