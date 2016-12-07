package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import cz.uhk.FimBird.GUI.MainFrame;

public class Bird {
	public final static int JUMP = 600;
	public final static int DEFAULT_SCORE = 0;
	public final static int DEFAULT_LIVES = 3;
	public final static int DEFAULT_X = 240;
	public final static int DEFAULT_Y = 400;
	public final static int MAX_LIVES = 5;
	
	private final static int GRAVITY = 350;
	private final static float IMMORTALITY = 1;
	
	private String name;
	private float positionX, positionY;
	private float speed;
	private float immortality;
	private int lives;
	private int score;
	private Image img;
	
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
		img = Toolkit.
				getDefaultToolkit().
				getImage(getClass().
				getResource("bird.png"));
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
		if(getLives() < MAX_LIVES)
			lives++;
	}
	
	public void removeLife(){
		lives--;
	}

	public void addPoint(){
		score++;
	}
	
	public void paint(Graphics g){
		g.drawImage(img, getX(), getY(), null);
	}

	
	public void update(float deltaTime){
		positionY -= speed * deltaTime;
		positionY += GRAVITY * deltaTime;
		
		speed -= speed * deltaTime;
		

		if(immortality > 0)
			immortality -= deltaTime;
	}
	
	public int getX(){
		return (int) positionX - img.getHeight(null)/2;
	}

	public int getY(){
		return (int) positionY - img.getHeight(null)/2;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(
				(int) positionX - img.getHeight(null)/2, 
				(int) positionY - img.getHeight(null)/2, 
				img.getWidth(null),
				img.getHeight(null));
	}
	
	public List<Rectangle> getCollisionRectangles(){
		List<Rectangle> rectangles = new ArrayList<>();
		int x = getX();
		int y = getY();

		rectangles.add(new Rectangle(x+35, y+10, 5, 5));
		rectangles.add(new Rectangle(x+35, y+10, 5, 5));
		rectangles.add(new Rectangle(x+30, y+5, 5, 15));
		rectangles.add(new Rectangle(x+20, y, 10, 25));
		rectangles.add(new Rectangle(x+10, y+5, 10, 25));
		rectangles.add(new Rectangle(x+5, y+20, 20, 5));
		
		return rectangles;
	}
	
	public boolean collideWith(Tube tube){
		List<Rectangle> rectangles = getCollisionRectangles();
		
		for(Rectangle r : rectangles){
			if(r.intersects(tube.getBottomRectangle()) || r.intersects(tube.getTopRectangle()))
				return true;
		}
		
		return false;
	}
	
	public boolean collideWith(Heart heart){
		List<Rectangle> rectangles = getCollisionRectangles();
	
		for(Rectangle r : rectangles){
			if(r.intersects(heart.getRectangle()))
				return true;
	}
	
	return false;
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
