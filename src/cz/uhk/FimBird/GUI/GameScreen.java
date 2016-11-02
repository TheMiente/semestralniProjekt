package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameScreen extends BaseScreen {

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		setBackground(Color.BLUE);
		
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
