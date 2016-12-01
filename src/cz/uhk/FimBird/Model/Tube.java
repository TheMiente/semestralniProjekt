package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import cz.uhk.FimBird.GUI.MainFrame;

public class Tube {
	private static final int GAP = 200;
	private static final int WIDTH = 50;
	
	private float positionX;
	private float height;
	private Color color;
	private boolean pointAdded;
	
	public Tube(float positionX, float height) {
		this.positionX = positionX;
		this.height = height;
		this.color = Color.GREEN;
		this.pointAdded = false;
	}
	
	public void update(float deltaTime){
		positionX -= World.SPEED * deltaTime;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		
		Rectangle topRectangle = getTopRectangle();
		g.fillRect(
				topRectangle.x, 
				topRectangle.y, 
				topRectangle.width,
				topRectangle.height);
		
		Rectangle bottomRectangle = getBottomRectangle();
		g.fillRect(
				bottomRectangle.x, 
				bottomRectangle.y, 
				bottomRectangle.width,
				bottomRectangle.height);
	}
	
	public Rectangle getTopRectangle(){
		return new Rectangle(
				(int)positionX - WIDTH/2, 
				0,
				WIDTH, 
				(int)height - GAP);
	}
	
	public Rectangle getBottomRectangle(){
		return new Rectangle(
				(int)positionX - WIDTH/2, 
				(int)height,
				WIDTH, 
				MainFrame.height - (int)height);
	}
	
	public boolean isPointAdded(){
		return pointAdded;
	}
	
	public void pointAdded(){
		this.pointAdded = true;
	}
	
	public void pointNotAddedAnymore(){
		this.pointAdded = false;
	}
	
	public int getMaximumX(){
		return (int)getPositionX() + WIDTH/2;
	}
	
	public int getMinimumX(){
		return (int)getPositionX() - WIDTH/2;
	}
	
	public int getCenterY(){
		return (int) height - GAP/2;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}
	
	public static float getRandomHeight(){
		return new Random().nextFloat() * 400 + 200;
	}
}
