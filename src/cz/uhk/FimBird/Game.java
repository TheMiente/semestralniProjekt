package cz.uhk.FimBird;

import cz.uhk.FimBird.GUI.HomeScreen;
import cz.uhk.FimBird.GUI.MainFrame;

public class Game {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		HomeScreen homeScreen = new HomeScreen(mainFrame);
		mainFrame.setScreen(homeScreen);
	}
}
