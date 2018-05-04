package swing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Sword extends GameObject{
	
	private Handler handler;
	
	public Texture texture = Game.getInstance();
	
	public Sword(double x, double y, ObjectId id, Handler handler, int velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		x = Player2.xx + 20;
		y = Player2.yy + 30;
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture.bullet[1], (int) x, (int) y, 40 , 30, null);
		
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	
	

}
