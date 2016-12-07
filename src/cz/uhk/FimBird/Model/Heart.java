package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import cz.uhk.FimBird.GUI.HomeScreen;

public class Heart {
	private float positionX, positionY;
	private static Image img;
	static {
		img = Toolkit.
				getDefaultToolkit().
				getImage(Heart.class.
				getResource("heart.png"));
	}
	
	public static Image getImg() {
		return img;
	}

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
		
		g.drawImage(getImg(), rectangle.x, rectangle.y, null);
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(
				(int) positionX - img.getHeight(null)/2, 
				(int) positionY - img.getHeight(null)/2, 
				img.getWidth(null),
				img.getHeight(null));
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
