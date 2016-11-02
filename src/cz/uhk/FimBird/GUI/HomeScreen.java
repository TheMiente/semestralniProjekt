package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HomeScreen extends BaseScreen {

	public HomeScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		setBackground(Color.RED);
		
		JLabel label = new JLabel("Fimbird");
		label.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
	    label.setSize(480, 50);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton play = new JButton("Play");
		JButton score = new JButton("Score");
		JButton sound = new JButton("Sound");
		
		Dimension buttonSize = new Dimension(280, 50);
		play.setLocation(100, 410);
		play.setSize(buttonSize);
		score.setLocation(100, 470);
		score.setSize(buttonSize);
		sound.setLocation(100, 530);
		sound.setSize(buttonSize);

		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new GameScreen(mainFrame));
			}
		});
		
		score.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new ScoreScreen(mainFrame));
			}
		});
		
		sound.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("dasdasdas");
			}
		});
		
		add(label);
		add(play);
		add(score);
		add(sound);
	}

}
