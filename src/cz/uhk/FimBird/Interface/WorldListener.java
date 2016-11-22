package cz.uhk.FimBird.Interface;

import cz.uhk.FimBird.Model.Heart;
import cz.uhk.FimBird.Model.Tube;

public interface WorldListener {
	void crashTube(Tube tube);
	
	void catchHeart(Heart heart);
	
	void outOf();
}
