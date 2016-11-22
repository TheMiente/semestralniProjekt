package cz.uhk.FimBird.Model;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.FimBird.Interface.WorldListener;

public class World {
	public static final int SPEED = 100;
	
	private Bird bird;
	private WorldListener worldListener;
	private List<Tube> tubes;
	private List<Heart> hearts;
	
	public World(Bird bird, WorldListener worldListener){
		this.bird = bird;
		this.worldListener = worldListener;
		this.tubes = new ArrayList<>();
		this.hearts = new ArrayList<>();
	}
	
	public void update(float deltaTime){
		bird.update(deltaTime);
		
		if(bird.isOut()){
			worldListener.outOf();
		}
		
		for(int i = 0; i < tubes.size(); i++){
			Tube tube = tubes.get(i);
			tube.update(deltaTime);
			
			if(tube.getPositionX() < -25){
				tubes.remove(i);
			}
			
			if(bird.collideWith(tube)){
				worldListener.crashTube(tube);
			}
		}
		
		for(int i = 0; i < hearts.size(); i++){
			Heart heart = hearts.get(i);
			heart.update(deltaTime);
			
			if(heart.getPositionX() < -25){
				hearts.remove(i);
			}

			if(bird.collideWith(heart)){
				worldListener.catchHeart(heart);
			}
		}
		
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
	
	public List<Heart> getHearts(){
		return hearts;
	}

	public List<Tube> getTubes() {
		return tubes;
	}

	@Override
	public String toString() {
		return "Jmeno: " + bird.getName() + 
				" Pozice: " + bird.getPositionX() + " " + bird.getPositionY() + 
				" Pocet trubek: " + tubes.size() + 
				" Pocet srdicek: " + hearts.size();
	}

}
