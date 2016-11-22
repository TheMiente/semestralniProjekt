package cz.uhk.FimBird.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Timer;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import cz.uhk.FimBird.Interface.WorldListener;
import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Tube;
import cz.uhk.FimBird.Model.World;

public class GameScreen extends BaseScreen implements WorldListener{
	
	private long lastTimeMillis;
	private Timer timer;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		JButton back = new JButton("Back");
		JButton pause = new JButton("Pause");
		
		back.setLocation(10, 10);
		back.setSize(80, 30);
		pause.setLocation(90, 10);
		pause.setSize(80, 30);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timer.isRunning())
					timer.stop();
				else{
					lastTimeMillis = System.currentTimeMillis();
					timer.restart();		
				}
			}
		});
		
		add(back);
		add(pause);

		Bird bird = new Bird("Pepa", 240, 400);
		World world = new World(bird, this);
		world.addTube(new Tube(400, 400));
		world.addTube(new Tube(600, 300));
		world.addTube(new Tube(800, 500));
		
		world.addHeart(new Heart(500, 300));
		world.addHeart(new Heart(700, 450));
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.width, MainFrame.height);
		gameCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bird.goUp();
			}
		});
		
		gameCanvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("asdasda");
			bird.goUp();
			}
		});
		
		add(gameCanvas);
		
		timer = new Timer(20, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMillis = System.currentTimeMillis();
				
				float deltaTime = (currentTimeMillis - lastTimeMillis) / 1000f;
				world.update(deltaTime);
				gameCanvas.repaint();
				
				lastTimeMillis = currentTimeMillis;
			}
		});
		
		lastTimeMillis = System.currentTimeMillis();
		timer.start();
		
		System.out.println(world);
	}

	@Override
	public void crashTube(Tube tube) {
		System.out.println("bum.");
	}

	@Override
	public void catchHeart(Heart heart) {
		System.out.println("jupí.");
	}

	@Override
	public void outOf() {
		System.out.println("seš lempl.");
	}
}
