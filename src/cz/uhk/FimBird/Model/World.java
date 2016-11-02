package cz.uhk.FimBird.Model;

import java.util.ArrayList;
import java.util.List;

public class World {
	
	private Bird bird;
	private List<Tube> tubes;
	private List<Heart> hearts;
	
	public World(Bird bird){
		this.bird = bird;
		this.tubes = new ArrayList<>();
		this.hearts = new ArrayList<>();
	}
	
	public void addTube(Tube tube){
		tubes.add(tube);
	}
	
	public void addHeart(Heart heart){
		hearts.add(heart);
	}
	
	public Bird getBird() {
		return bird;
	}

	@Override
	public String toString() {
		return "Jmeno: " + bird.getName() + 
				" Pozice: " + bird.getPositionX() + " " + bird.getPositionY() + 
				" Pocet trubek: " + tubes.size() + 
				" Pocet srdicek: " + hearts.size();
	}

}
