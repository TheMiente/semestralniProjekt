package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import cz.uhk.FimBird.ScoreBoard;
import cz.uhk.FimBird.Model.Player;

public class ScoreScreen extends BaseScreen {
	private static final int SCORE_GAP = 30;
	
	public ScoreScreen(MainFrame mainFrame){
		super(mainFrame);
		
		setBackground(Color.YELLOW);
		
		JButton back = new JButton("Back");

		back.setLocation(10, 10);
		back.setSize(80, 30);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		add(back);
		
		List<Player> playerList = ScoreBoard.getScoreBoard();
		Dimension size = new Dimension(150, 50);
		int locationX = (int) (MainFrame.width/2 - size.getWidth()/2);
		
		for(int i = 0; i < playerList.size(); i++){
			Player player = playerList.get(i);
			
			JLabel label = new JLabel((i + 1) + ". " + player.getNickname() + " - " + player.getScore());
			
			label.setSize(size);
			label.setLocation(locationX, (MainFrame.height/2 - 300) + i * SCORE_GAP);
			
			add(label);
		}
	}
}
