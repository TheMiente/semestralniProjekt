package cz.uhk.FimBird.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Ground {

	private Image img;
	private float margin;
	
	public Ground(){
		img = Toolkit.
				getDefaultToolkit().
				getImage(getClass().
				getResource("ground.png"));
		
		margin = 0;
	}
	
	public void update(){
		if(margin > img.getWidth(null))
			margin = 0;
		else
			margin += 0.1f;
	}
	
	public void paint(Graphics g){
		g.drawImage(img, (int) (margin - img.getWidth(null) * 2), 700, null);
		g.drawImage(img, (int) (margin - img.getWidth(null)), 700, null);
		g.drawImage(img, (int) margin, 700, null);
		g.drawImage(img, (int) (margin + img.getWidth(null)), 700, null);
	}
}
