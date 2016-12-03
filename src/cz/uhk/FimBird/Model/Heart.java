package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Heart {
	private float positionX, positionY;

	public Heart(float positionX, float positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public void update(float deltaTime){
		positionX -= World.SPEED * deltaTime;
	}

	public void paint(Graphics g){
		g.setColor(Color.RED);
		
		Rectangle rectangle = getRectangle();
		
		g.fillRect(
				rectangle.x, 
				rectangle.y, 
				rectangle.width,
				rectangle.height);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(
				(int) positionX - 25, 
				(int) positionY - 25, 
				50, 50);
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
	
	public static float getRandomHeight(){
		return new Random().nextFloat() * 300 + 200;
	}
}
