package cz.uhk.FimBird.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.FimBird.GUI.MainFrame;

public class Missile {
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;
	
	private float positionX, positionY;
	
	public Missile(float positionX, float positionY){
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public void update(float deltaTime){
		positionX += World.MISSILE_SPEED * deltaTime;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(
				(int)(positionX - WIDTH/2),
				(int)(positionY - HEIGHT/2), 
				WIDTH, HEIGHT);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		
		Rectangle rectangle = getRectangle();
		g.fillRect(
				rectangle.x,
				rectangle.y,
				rectangle.width,
				rectangle.height
				);
		
	}
	
	public boolean isBeyond(){
		return positionX > MainFrame.width;
	}

	public int collideWith(Tube tube){
		if(tube.getTopRectangle().intersects(getRectangle()) && !tube.isTopDestroyed()){
			System.out.println(1 + " c");
			tube.destroyTopTube();
			return 1;
		}
		
		if(tube.getBottomRectangle().intersects(getRectangle()) && !tube.isBottomDestroyed()){
			System.out.println(2 + " c");
			return 2;
		}
		
		return 0;
	}
}
