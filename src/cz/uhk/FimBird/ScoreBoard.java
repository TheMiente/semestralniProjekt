package cz.uhk.FimBird;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import cz.uhk.FimBird.Model.Player;

public class ScoreBoard {
	public static final int MAX_PLAYERS = 15;
	
	private static final String SCORE_FILE = "score.txt";
	
	public static List<Player> getScoreBoard(){
		try {
			Path path = Paths.get(SCORE_FILE);
			List<String> players;
			
			File file = new File(SCORE_FILE);	
			if(!file.exists())
				file.createNewFile();
			
			players = Files.readAllLines(path, Charset.forName("UTF-8"));
			
			List<Player> playerList = new ArrayList<>();
			
			for(int i = 0; i < players.size(); i++)
				playerList.add(new Player(players.get(i)));
	
			if(playerList.size() < MAX_PLAYERS){
				int until = MAX_PLAYERS - playerList.size();
				
				for(int i = 0; i < until; i++)
					playerList.add(new Player());
			}

			return playerList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void newScore(Player player){
		List<Player> playerList = getScoreBoard();
		int index = 0;
		
		while(index < MAX_PLAYERS && !player.isBetter(playerList.get(index)))
			index++;
		
		if(index < MAX_PLAYERS)
			playerList.add(index, player);

		try {
			PrintStream pt = new PrintStream(new File(SCORE_FILE), "UTF-8");
			 
			for (int i = 0; i < MAX_PLAYERS; i++) 
				pt.println(playerList.get(i));
		 
			pt.flush();
			pt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
