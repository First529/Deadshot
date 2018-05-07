package swing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Random;

public class Sword extends GameObject{
	
	private Handler handler;
	
	public Texture texture = Game.getInstance();
	
	private Random rand = new Random();
	
	private int n;
	
	public Sword(double x, double y, ObjectId id, Handler handler, int velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
		n = rand.nextInt(2) + 1;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (Player1.hitCount >= 10) {
			handler.removeObject(KeyInput.sword);
			Player1.hitCount = 0;
		
		}
		if (Player2.xs <= 0) {
			x = Player2.xx + 2;
			y = Player2.yy + 20;
		} else {
			x = Player2.xx + 2;
			y = Player2.yy + 20;
		}
	}

	@Override
	public void render(Graphics g) {
		if (n == 1) {
			if (Player2.xs <= 0) 
				g.drawImage(texture.swords[1], (int) x, (int) y, 40 , 30, null);
			else 
				g.drawImage(texture.swords[0], (int) x, (int) y, 40 , 30, null);
		} else {
			if (Player2.xs <= 0) 
				g.drawImage(texture.swords[3], (int) x, (int) y, 40 , 30, null);
			else 
				g.drawImage(texture.swords[2], (int) x, (int) y, 40 , 30, null);
		}
		
		
		
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	
	

}
