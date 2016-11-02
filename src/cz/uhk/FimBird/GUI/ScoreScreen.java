package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ScoreScreen extends BaseScreen {
	
	public ScoreScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		setBackground(Color.YELLOW);
		
		JButton back = new JButton("Back");
		
		back.setLocation(100, 410);
		back.setSize(280, 50);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		add(back);
	}

}
