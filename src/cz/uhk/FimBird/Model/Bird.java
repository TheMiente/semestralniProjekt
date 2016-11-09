package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;

public class Bird {
	private String name;
	private float positionX, positionY;
	private float speed;
	private int lives;
	
	//TODO
	//konstruktor, gettery, settery, toString

	public Bird(String name, float positionX, float positionY) {
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.speed = 0;
		this.lives = 3;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		
		g.fillRect(
				(int) positionX - 25, 
				(int) positionY - 25, 
				50, 50);
	}
	
	public void update(float deltaTime){
		positionX += World.SPEED * deltaTime;
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

	public void goUp(){
		
	}

	public void catchHeart(){
		
	}
	
	public void die(){
		
	}
	public void addLive(){
		
	}
	
	public void removeLive(){
		
	}
}
