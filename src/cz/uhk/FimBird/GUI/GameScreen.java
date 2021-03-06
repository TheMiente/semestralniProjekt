package cz.uhk.FimBird.GUI;

import javax.swing.JLabel;
import javax.swing.JTextField;

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

import cz.uhk.FimBird.ScoreBoard;
import cz.uhk.FimBird.Interface.WorldListener;
import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.Ground;
import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Missile;
import cz.uhk.FimBird.Model.Player;
import cz.uhk.FimBird.Model.Tube;
import cz.uhk.FimBird.Model.World;

public class GameScreen extends BaseScreen implements WorldListener{
	
	private long lastTimeMillis;
	private Timer timer;
	private Bird bird;
	private World world;
	
	private JLabel labelScore, labelGameOver;
	private JTextField nickname;
	private JButton back, pause, scoreBoard;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);

		back = new JButton("Back");
		back.setLocation(10, 10);
		back.setSize(80, 30);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});

		
		pause = new JButton("Pause");
		pause.setLocation(90, 10);
		pause.setSize(80, 30);
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timer.isRunning())
					timer.stop();
				else{
					if(pause.getText() == "Pause"){
						lastTimeMillis = System.currentTimeMillis();
						timer.restart();
					}else{
						ScoreBoard.newScore(
							new Player(
							nickname.getText(),
							bird.getScore()
							));
						
						labelGameOver.setVisible(false);
						nickname.setVisible(false);
						scoreBoard.setVisible(false);
						pause.setText("Pause");
						world.restart();
						labelScore.setText("" + bird.getScore());
						
						repaint();
					}
					addNotify();
				}
			}
		});
		
		scoreBoard = new JButton("Score board");
		scoreBoard.setBounds(MainFrame.width/2 - 100, MainFrame.height/2 + 45, 200, 40);
		scoreBoard.setVisible(false);
		scoreBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();

				ScoreBoard.newScore(
					new Player(
					nickname.getText(),
					bird.getScore()
					));
				
				mainFrame.setScreen(new ScoreScreen(mainFrame));
			}
		});
		
		labelGameOver = new JLabel("Game Over");
		labelGameOver.setBounds(MainFrame.width/2 - 100, MainFrame.height/2 - 45, 200, 40);
		labelGameOver.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
		labelGameOver.setForeground(Color.red);
		labelGameOver.setVisible(false);
		
		labelScore = new JLabel("" + Bird.DEFAULT_SCORE);
		labelScore.setBounds(MainFrame.width/2 - 10, 10, 100, 40);
		labelScore.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		labelScore.setForeground(Color.red);
		
		nickname = new JTextField("Nickname");
		nickname.setBounds(MainFrame.width/2 - 100, MainFrame.height/2, 200, 30);
		nickname.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		nickname.setForeground(Color.red);
		nickname.setVisible(false);
		
		add(scoreBoard);
		add(nickname);
		add(labelGameOver);
		add(labelScore);
		add(back);
		add(pause);

		bird = new Bird("Pepa");
		world = new World(bird, this);
		world.generateRandom();
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					bird.goUp();
					if(!timer.isRunning()){
						lastTimeMillis = System.currentTimeMillis();
						timer.start();
					}
				}else{
					world.addMissile(new Missile(bird.getPositionX(), bird.getPositionY()));
				}
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				bird.goUp();
				if(!timer.isRunning()){
					lastTimeMillis = System.currentTimeMillis();
					timer.start();
				}
			}
		});
		
		timer = new Timer(10, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMillis = System.currentTimeMillis();

				float deltaTime = (currentTimeMillis - lastTimeMillis) / 1000f;
				
				world.update(deltaTime);

				labelScore.setText("" + bird.getScore());	
				
				if(bird.isDead()){
					timer.stop();
					pause.setText("Restart");
					labelGameOver.setVisible(true);
					nickname.setVisible(true);
					scoreBoard.setVisible(true);
				}

				repaint();
				
				lastTimeMillis = currentTimeMillis;
			}
		});

		System.out.println(world);
	}
	
	@Override
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		world.drawWorldBackground(g, this);
		
		List<Heart> hearts = world.getHearts();
		for(Heart heart : hearts)
			heart.paint(g);
		
		List<Tube> tubes = world.getTubes();
		for(Tube tube : tubes)
			tube.paint(g);
		
		List<Missile> missiles = world.getMissiles();
		for(Missile missile : missiles)
			missile.paint(g);
		
		g.setColor(Color.red);
		for(int i = 0; i < bird.getLives(); i++)
			g.drawImage(Heart.getImg(), MainFrame.width - i*40 - 40, 10, this);
				
		bird.paint(g);

		world.getGround().paint(g);
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
		if(bird.getPositionY() > MainFrame.height - 100)
			bird.setPositionY(50);
		else
			bird.setPositionY(MainFrame.height - 150);
	}
}
