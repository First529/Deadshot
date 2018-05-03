package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	public Bullet(double x, double y, ObjectId id, Handler handler, int velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		x += velocityX;
		Collision(object);
		if (Player2.isDamaged()) handler.removeObject(KeyInput.bullet);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, 16, 16);
		
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	
	public void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Block) {
				if (this.getBounds().intersects((Rectangle2D) tempObject.getBounds())) {
					handler.removeObject(KeyInput.bullet);
				}
			}
		}
	}
	

}
