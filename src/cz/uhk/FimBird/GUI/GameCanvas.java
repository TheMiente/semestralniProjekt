package cz.uhk.FimBird.GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.World;

public class GameCanvas extends Canvas{
	
	private World world;

	public GameCanvas(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Bird bird = world.getBird();
		g.setColor(Color.red);
		g.fillRect((int)bird.getPositionX() - 10, (int)bird.getPositionY() - 10, 20, 20);
	}

}
