package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private final static int width = 480;
	private final static int height = 800;
	
	public MainFrame(){
		setSize(width, height);
		setTitle("FimBird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
}
