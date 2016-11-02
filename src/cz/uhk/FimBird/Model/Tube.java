package cz.uhk.FimBird.Model;

import java.awt.Color;

public class Tube {
	private float positionX;
	private float height;
	private Color color;
	
	public Tube(float positionX, float height) {
		this.positionX = positionX;
		this.height = height;
		this.color = Color.GREEN;
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
