package cz.uhk.FimBird.Model;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.FimBird.Interface.WorldListener;

public class World {
	public static final int SPEED = 150;

	private static final int SPACE_BETWEEN_TUBES = 300;
	private static final int SPACE_BETWEEN_HEARTS = 750;
	
	private Bird bird;
	private WorldListener worldListener;
	private List<Tube> tubes;
	private List<Heart> hearts;
	private boolean generated;
	
	public World(Bird bird, WorldListener worldListener){
		this.bird = bird;
		this.worldListener = worldListener;
		this.tubes = new ArrayList<>();
		this.hearts = new ArrayList<>();
		generated = false;
	}
	
	public void update(float deltaTime){
		bird.update(deltaTime);
		
		if(generated)
			regenerate();
		
		if(bird.isOut()){
			worldListener.outOf();
		}
		
		for(int i = 0; i < tubes.size(); i++){
			Tube tube = tubes.get(i);
			tube.update(deltaTime);
			
			if(bird.collideWith(tube)){
				if(!bird.isGodModOn())
				worldListener.crashTube(tube);
				tube.pointAdded();
			}else if(!tube.isPointAdded() && bird.getPositionX() > tube.getMinimumX() && bird.getPositionX() < tube.getMaximumX()){
					tube.pointAdded();
					bird.addPoint();
				}
		}
		
		for(int i = 0; i < hearts.size(); i++){
			Heart heart = hearts.get(i);
			heart.update(deltaTime);

			if(bird.collideWith(heart)){
				worldListener.catchHeart(heart);
			}
		}
		
	}
	
	public void generateRandom(){
		for(int i = 1; i < 3; i++){
			addTube(new Tube(SPACE_BETWEEN_TUBES + i * SPACE_BETWEEN_TUBES, Tube.getRandomHeight()));
		}
		
		addHeart(new Heart(SPACE_BETWEEN_HEARTS, Heart.getRandomHeight()));
		
		generated = true;
	}
	
	public void regenerate(){
		for(Tube tube : tubes){
			if(tube.getPositionX() < -50){
				tube.setPositionX(tube.getPositionX() + tubes.size() * SPACE_BETWEEN_TUBES);
				tube.setHeight(Tube.getRandomHeight());
				tube.pointNotAddedAnymore();
			}
		}
		
		for(Heart heart : hearts)
			if(heart.getPositionX() < -100){
				heart.setPositionX(heart.getPositionX() + hearts.size() * SPACE_BETWEEN_HEARTS);
				heart.setPositionY(Heart.getRandomHeight());
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
