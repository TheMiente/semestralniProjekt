package cz.uhk.FimBird.Model;

public class Player {
	private String nickname;
	private int score;
	
	public Player(){
		this("",0);
	}
	
	public Player(String nickname, int score) {
		super();
		this.nickname = nickname;
		this.score = score;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public int getScore() {
		return score;
	}

	public boolean isBetter(Player p){
		return this.getScore() > p.getScore();
	}
}
