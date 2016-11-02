package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Tube;
import cz.uhk.FimBird.Model.World;

public class GameScreen extends BaseScreen {

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		setBackground(Color.BLUE);
		
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

		Bird bird = new Bird("Pepa", 240, 400);
		World world = new World(bird);
		world.addTube(new Tube(400, 400));
		world.addTube(new Tube(600, 300));
		world.addTube(new Tube(800, 500));

		world.addHeart(new Heart(500, 450));
		world.addHeart(new Heart(500, 450));
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.width, MainFrame.height);
		add(gameCanvas);
		
		System.out.println(world);
	}
}
