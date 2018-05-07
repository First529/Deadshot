package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Arrow extends GameObject{
	
	private Handler handler;
	
	private int i = 0;
	
	private double time = 0.0;
	private double angle = Math.toRadians(80);
	private double initV = 15;
	private double maxH = (0.5*initV*initV*Math.sin(angle)*Math.sin(angle))/9.81;
	private double totalTime =( 2.0 * initV * Math.sin(angle) )/ 9.81;
	private double timePos = totalTime / 30;
	


	public Arrow(double x, double y, ObjectId id,Handler handler, double velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (i <= 50) {
				time += timePos;
				x += initV * Math.cos(angle)* time * -1;
				y += ((initV * Math.sin(angle)*time) + 0.5*-9.81*time*time) *-1;
				Collision(object);
				System.out.println(y);
				i++;
		
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.drawOval((int)x, (int)y, 16, 16);
		
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
					handler.removeObject(KeyInput.arrow);
				}
			}
		}
	}
	
	

}
