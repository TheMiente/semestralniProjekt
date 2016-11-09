package cz.uhk.FimBird.GUI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.List;

import cz.uhk.FimBird.Model.Bird;
import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Tube;
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
		bird.paint(g);
		
		
		List<Heart> hearts = world.getHearts();
		for(Heart heart : hearts)
			heart.paint(g);
		
		List<Tube> tubes = world.getTubes();
		for(Tube tube : tubes){
			tube.paint(g);
		}
	}

}
