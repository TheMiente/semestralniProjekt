package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.FimBird.GUI.MainFrame;

public class Tube {
	private static final int GAP = 200;
	
	private float positionX;
	private float height;
	private Color color;
	
	public Tube(float positionX, float height) {
		this.positionX = positionX;
		this.height = height;
		this.color = Color.GREEN;
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
				(int)positionX - 25, 
				0,
				50, 
				(int)height - GAP);
	}
	
	public Rectangle getBottomRectangle(){
		return new Rectangle(
				(int)positionX - 25, 
				(int)height,
				50, 
				MainFrame.height - (int)height);
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

	public Color getColor() {
		return color;
	}
	
	
}
