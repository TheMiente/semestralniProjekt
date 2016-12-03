package cz.uhk.FimBird.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import cz.uhk.FimBird.GUI.GameScreen;
import cz.uhk.FimBird.Interface.WorldListener;

public class World {
	public static final int SPEED = 200;

	private static final int SPACE_BETWEEN_TUBES = 300;
	private static final int SPACE_BETWEEN_HEARTS = 750;
	
	private Bird bird;
	private WorldListener worldListener;
	private List<Tube> tubes;
	private List<Heart> hearts;
	private Image background;
	private float backgroundMargin;
	
	public World(Bird bird, WorldListener worldListener){
		this.bird = bird;
		this.worldListener = worldListener;
		this.tubes = new ArrayList<>();
		this.hearts = new ArrayList<>();

		background = Toolkit.
				getDefaultToolkit().
				getImage(getClass().
				getResource("background.jpg"));
		System.out.println(background);
		backgroundMargin = 0;
	}
	
	public void update(float deltaTime){
		regenerate();

		bird.update(deltaTime);
		
		if(bird.isOut())
			worldListener.outOf();
		
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
		
		if(backgroundMargin > background.getWidth(null))
			backgroundMargin = 0;
		else
			backgroundMargin += 0.1f;
		
	}
	
	public void drawWorldBackground(Graphics g, GameScreen gameScreen){
		g.drawImage(background, (int)(backgroundMargin), 0, gameScreen);
		g.drawImage(background, (int)(backgroundMargin - background.getWidth(null)), 0, gameScreen);
	}
	
	public void restart(){
		backgroundMargin = 0;
		bird.dieMotherfucker();
		getTubes().clear();
		getHearts().clear();
		generateRandom();
	}
	
	public void generateRandom(){
		for(int i = 1; i < 3; i++)
			addTube(new Tube(SPACE_BETWEEN_TUBES + i * SPACE_BETWEEN_TUBES, Tube.getRandomHeight()));
		
		addHeart(new Heart(SPACE_BETWEEN_HEARTS, Heart.getRandomHeight()));
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
