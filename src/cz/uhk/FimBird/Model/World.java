package cz.uhk.FimBird.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import cz.uhk.FimBird.GUI.GameScreen;
import cz.uhk.FimBird.Interface.WorldListener;

public class World {
	public static final int SPEED = 200;
	public static final int MISSILE_SPEED = 300;

	private static final int SPACE_BETWEEN_TUBES = 300;
	private static final int SPACE_BETWEEN_HEARTS = 1050;
	
	private Bird bird;
	private WorldListener worldListener;
	private List<Tube> tubes;
	private List<Heart> hearts;
	private List<Missile> missiles;
	private Ground ground;
	private Image background;
	private float backgroundMargin;
	
	public World(Bird bird, WorldListener worldListener){
		this.bird = bird;
		this.worldListener = worldListener;
		this.tubes = new ArrayList<>();
		this.hearts = new ArrayList<>();
		this.missiles = new ArrayList<>();
		this.ground = new Ground();
		background = Toolkit.
				getDefaultToolkit().
				getImage(getClass().
				getResource("background.jpg"));

		backgroundMargin = 0;
	}
	
	public void update(float deltaTime){
		regenerate();

		bird.update(deltaTime);
		
		if(bird.isOut())
			worldListener.outOf();
		
		for(int i = 0; i < missiles.size(); i++){
			Missile missile = missiles.get(i);
			missile.update(deltaTime);
			
			if(missile.isBeyond())
				missiles.remove(i);
			else{
				boolean notCollided = true;
				for(int j = 0; j < tubes.size() && notCollided; j++){
					Tube tube = tubes.get(j);
	
					if(missile.collideWith(tube) == 1){
						missiles.remove(i);
						tube.setdestroyTopTube(true);
						notCollided = false;
					}
					
					if(missile.collideWith(tube) == 2){
						missiles.remove(i);
						tube.setdestroyBottomTube(true);
						notCollided = false;
					}
				}

			}
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
		
		if(backgroundMargin > background.getWidth(null))
			backgroundMargin = 0;
		else
			backgroundMargin += 0.1f;
		
		ground.update();
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
		getMissiles().clear();
		generateRandom();
	}
	
	public void generateRandom(){
		for(int i = 1; i < 4; i++)
			addTube(new Tube(SPACE_BETWEEN_TUBES + i * SPACE_BETWEEN_TUBES, Tube.getRandomHeight()));
		
		addHeart(new Heart(SPACE_BETWEEN_HEARTS, Heart.getRandomHeight()));
	}
	
	public void regenerate(){
		for(Tube tube : tubes){
			if(tube.getPositionX() < -50){
				tube.setPositionX(tube.getPositionX() + tubes.size() * SPACE_BETWEEN_TUBES);
				tube.setHeight(Tube.getRandomHeight());
				tube.pointNotAddedAnymore();
				tube.setdestroyTopTube(false);
				tube.setdestroyBottomTube(false);
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
	
	public void addMissile(Missile missile){
		missiles.add(missile);
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
	
	public List<Missile> getMissiles(){
		return missiles;
	}
	
	public Ground getGround() {
		return ground;
	}

	@Override
	public String toString() {
		return "Jmeno: " + bird.getName() + 
				" Pozice: " + bird.getPositionX() + " " + bird.getPositionY() + 
				" Pocet trubek: " + tubes.size() + 
				" Pocet srdicek: " + hearts.size();
	}

}
