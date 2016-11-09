package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;

public class Heart {
	private float positionX, positionY;

	public Heart(float positionX, float positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void paint(Graphics g){
		g.setColor(Color.RED);
		
		g.fillRect(
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
}
