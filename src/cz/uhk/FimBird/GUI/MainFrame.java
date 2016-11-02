package cz.uhk.FimBird.GUI;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public final static int width = 480;
	public final static int height = 800;
	
	private BaseScreen screen;
	
	public MainFrame(){
		setSize(width, height);
		setTitle("FimBird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void setScreen(BaseScreen screen){
		if(this.screen != null){
			remove(this.screen);
		}
		
		this.screen = screen;
		add(screen);
		
		revalidate();
	}
	
}
