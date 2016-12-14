package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cz.uhk.FimBird.Model.World;

public class HomeScreen extends BaseScreen {

	public HomeScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		setBackground(Color.RED);
		
		JLabel label = new JLabel("Fimbird");
		label.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
	    label.setSize(100, 50);
	    label.setLocation(MainFrame.width/2 - 50, 10);
		
		JButton play = new JButton("Play");
		JButton score = new JButton("Score");
		JButton sound = new JButton("Sound");
		
		Dimension buttonSize = new Dimension(280, 50);
		int locationX = (int) (MainFrame.width/2 - buttonSize.getWidth()/2);
		play.setLocation(locationX, 410);
		play.setSize(buttonSize);
		score.setLocation(locationX, 470);
		score.setSize(buttonSize);
		sound.setLocation(locationX, 530);
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
