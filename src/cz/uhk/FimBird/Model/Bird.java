package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.FimBird.GUI.MainFrame;

public class Bird {
	public final static int JUMP = 600;
	public final static int DEFAULT_SCORE = 0;
	public final static int DEFAULT_LIVES = 3;
	
	private final static int GRAVITY = 300;
	private final static int IMMORTALITY = 1;
	
	private String name;
	private float positionX, positionY;
	private float speed;
	private int lives;
	private int score;
	public float immortality;
	
	//TODO
	//konstruktor, gettery, settery, toString

	public Bird(String name, float positionX, float positionY) {
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.speed = 0;
		this.lives = DEFAULT_LIVES;
		this.score = DEFAULT_SCORE;
		this.immortality = 0;
	}

	public void goUp(){
		speed = JUMP;
	}

	public void catchHeart(){
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
		return positionY - 25 > MainFrame.height || positionY - 25 < 0;
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
		
	}
	
	public void godModOn(){
		immortality = IMMORTALITY;
	}
	
	public boolean isGodModOn(){
		return immortality > 0;
	}
	
	public void attackGodMod(float ms){
		immortality -= ms;
	}
	
	public boolean isDead() {
		return getLives() < 1;
	}
}
