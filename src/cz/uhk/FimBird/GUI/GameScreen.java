package cz.uhk.FimBird.GUI;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
	private World world;
	
	private JLabel labelLives, labelScore;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		setBackground(Color.cyan);

		JButton back = new JButton("Back");
		back.setLocation(10, 10);
		back.setSize(80, 30);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});

		
		JButton pause = new JButton("Pause");
		pause.setLocation(90, 10);
		pause.setSize(80, 30);
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
		labelLives.setForeground(Color.red);
		labelLives.setBounds(10,50,50,30);
		
		labelScore = new JLabel("" + Bird.DEFAULT_SCORE);
		labelScore.setBounds(MainFrame.width/2 - 10, 10, 50, 40);
		labelScore.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		labelScore.setForeground(Color.red);
		
		add(labelLives);
		add(labelScore);
		add(back);
		add(pause);

		bird = new Bird("Pepa", 240, 400);
		world = new World(bird, this);
		world.generateRandom();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bird.goUp();
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				bird.goUp();
			}
		});
		
		timer = new Timer(10, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMillis = System.currentTimeMillis();

				float deltaTime = (currentTimeMillis - lastTimeMillis) / 1000f;
				
				world.update(deltaTime);
				
				labelLives.setText("Lives: " + bird.getLives());
				labelScore.setText("" + bird.getScore());
				
				if(bird.isDead()){
					timer.stop();
				}

				repaint();
				lastTimeMillis = currentTimeMillis;
			}
		});
		
		lastTimeMillis = System.currentTimeMillis();
		timer.start();
		
		System.out.println(world);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		List<Heart> hearts = world.getHearts();
		for(Heart heart : hearts)
			heart.paint(g);
		
		List<Tube> tubes = world.getTubes();
		for(Tube tube : tubes){
			tube.paint(g);
		}
		
		Bird bird = world.getBird();
		bird.paint(g);
	
		g.setColor(new Color(0xf4a460));
		g.fillRect(0, 700, MainFrame.width, 100);
	}

	@Override
	public void crashTube(Tube tube) {
		bird.godModOn();
		bird.removeLife();
		System.out.println("Po�et �ivot�: " + bird.getLives());
	}

	@Override
	public void catchHeart(Heart heart) {
		heart.setPositionY(-100);
		bird.catchHeart();
		System.out.println("Po�et �ivot�: " + bird.getLives());
	}

	@Override
	public void outOf() {
		System.out.println("se� lempl.");
		if(bird.getPositionY() > MainFrame.height - 100)
			bird.setPositionY(50);
		else
			bird.setPositionY(MainFrame.height - 150);
		//bird.setPositionY(MainFrame.height/2);
		//bird.setSpeed(Bird.JUMP/2);
		
		//bird.removeLife();
	}
}
