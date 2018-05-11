import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss extends Enemy{
	
	public Image overworldSprite;
	public Point position = new Point(8,7); 
	public int location = 1;
	
	Boss(String CharName){
		super(CharName);
		maxHP = 20;
		attack = 6;
		defense = 4;
		experience = 120;
		HP = maxHP;
		try {
			overworldSprite = ImageIO.read(new File("src\\Sprites\\dummyBoss.png"));
		}catch(IOException e) {
			//ree
		}
	}
	
	public Point getPosition() {
		return position;
	}

}
