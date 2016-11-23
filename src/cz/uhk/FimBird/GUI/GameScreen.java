package cz.uhk.FimBird.GUI;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Timer;

import cz.uhk.FimBird.Interface.WorldListener;
import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Tube;
import cz.uhk.FimBird.Model.World;

public class GameScreen extends BaseScreen implements WorldListener{
	
	private long lastTimeMillis;
	private Timer timer;
	private Bird bird;
	
	private JLabel labelLives, labelScore;

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
				timer.stop();
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
		labelLives = new JLabel("Lives: " + Bird.DEFAULT_LIVES);
		labelScore = new JLabel("Score: " + Bird.DEFAULT_SCORE);
		labelLives.setBounds(10,50,50,30);
		labelScore.setBounds(10,80,50,30);
		labelLives.setOpaque(true);
		
		add(labelLives);
		add(labelScore);
		add(back);
		add(pause);

		bird = new Bird("Pepa", 240, 400);
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
				bird.attackGodMod(deltaTime);
				world.update(deltaTime);

				labelLives.setText("Lives: " + bird.getLives());
				labelScore.setText("Score: " + bird.getScore());
				
				if(bird.isDead()){
					timer.stop();
				}
				
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
		bird.removeLife();
		bird.godModOn();
		//bird.setPositionY(tube.getCenterY());
		System.out.println("Poèet životù: " + bird.getLives());
	}

	@Override
	public void catchHeart(Heart heart) {
		heart.setPositionY(-100);
		bird.catchHeart();
		System.out.println("Poèet životù: " + bird.getLives());
	}

	@Override
	public void outOf() {
		System.out.println("seš lempl.");
		bird.setPositionY(MainFrame.height/2);
		bird.setSpeed(Bird.JUMP/2);
		
		bird.removeLife();
	}
}
