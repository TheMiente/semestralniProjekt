package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.FimBird.GUI.MainFrame;

public class Bird {
	public final static int JUMP = 600;
	public final static int DEFAULT_SCORE = 0;
	public final static int DEFAULT_LIVES = 3;
	public final static int DEFAULT_X = 240;
	public final static int DEFAULT_Y = 400;
	
	private final static int GRAVITY = 300;
	private final static float IMMORTALITY = 1;
	
	private String name;
	private float positionX, positionY;
	private float speed;
	private float immortality;
	private int lives;
	private int score;
	
	public Bird(String name){
		this(name, DEFAULT_X, DEFAULT_Y);
	}
	
	public Bird(String name, float positionX, float positionY) {
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.speed = 0;
		this.lives = DEFAULT_LIVES;
		this.score = DEFAULT_SCORE;
	}
	
	public void godModOn(){
		immortality = IMMORTALITY;
	}
	
	public boolean isGodModOn(){
		return immortality > 0;
	}

	public void goUp(){
		speed = JUMP;
	}

	public void catchHeart(){
		if(getLives() < 5)
			lives++;
	}
	
	public void removeLife(){
		lives--;
	}

	public void addPoint(){
		score++;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		
		Rectangle rectangle = getRectangle();
		
		g.fillRect(
				rectangle.x, 
				rectangle.y, 
				rectangle.width,
				rectangle.height);
	}

	
	public void update(float deltaTime){
		positionY -= speed * deltaTime;
		positionY += GRAVITY * deltaTime;
		
		speed -= speed * deltaTime;
		

		if(immortality > 0)
			immortality -= deltaTime;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(
				(int) positionX - 25, 
				(int) positionY - 25, 
				50, 50);
	}
	
	public boolean collideWith(Tube tube){
		Rectangle rectangle = getRectangle();
		
		return rectangle.intersects(tube.getTopRectangle()) || 
				rectangle.intersects(tube.getBottomRectangle());
	}
	
	public boolean collideWith(Heart heart){
		return getRectangle().intersects(heart.getRectangle());
	}
	
	public boolean isOut(){
		return positionY > MainFrame.height-100 || positionY - 25 < 0;
	}
		
	public int getScore() {
		return score;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public String getName(){
		return name;
	}
	
	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void dieMotherfucker(){
		score = DEFAULT_SCORE;
		lives = DEFAULT_LIVES;
		setPositionX(DEFAULT_X);
		setPositionY(DEFAULT_Y);
	}

	public boolean isDead() {
		return getLives() < 1;
	}
}
