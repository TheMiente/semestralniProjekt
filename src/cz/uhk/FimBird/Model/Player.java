package cz.uhk.FimBird.Model;

public class Player implements Comparable<Player>{
	private String nickname;
	private int score;
	
	public Player(){
		this("",0);
	}
	
	public Player(String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
	}
	
	public Player(String fileLine) {
		String[] split = fileLine.split(";");
		
		this.nickname = split[0];
		this.score = Integer.parseInt(split[1]);
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public int compareTo(Player p) {
		return  p.getScore() - this.getScore();
	}
	
	@Override
	public String toString() {
		return getNickname() + ";" + getScore();
	}
}
