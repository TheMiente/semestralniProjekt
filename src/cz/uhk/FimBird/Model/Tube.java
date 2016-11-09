package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;

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
	
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		
		g.fillRect(
				(int)positionX - 25, 
				(int)height,
				50, 
				MainFrame.height - (int)height);
		
		g.fillRect(
				(int)positionX - 25, 
				0,
				50, 
				(int)height - GAP);
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
