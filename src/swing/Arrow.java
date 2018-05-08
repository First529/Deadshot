package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Arrow extends GameObject{
	
	private Handler handler;
	
	public Texture texture = Game.getInstance();
	
	private int i = 0;
	
	public static int player1Facing;
	
	private double time = 0.0;
	public static double angle = 60; 
	private double realY, realY2;
	public static double initV = 15;
	private double totalTime =( 2.0 * initV * Math.sin(Math.toRadians(angle)) )/ 9.81;
	private double timePos = totalTime / 20;
	private double time1 = timePos;
	private double diff;
	


	public Arrow(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (i <= 50) {
			if (i >= 50) handler.removeObject(KeyInput.arrow); 
			time += timePos;
			time1 += timePos;
			if (KeyInput.checkFacing1 == -1) {
				
				x += initV * Math.cos(Math.toRadians(angle))* timePos * 4;
				realY = ((initV * Math.sin(Math.toRadians(angle))*time) + 0.5*-9.81*time*time);
				realY2 = ((initV * Math.sin(Math.toRadians(angle))*time1) + 0.5*-9.81*time1*time1);
				diff = realY2 - realY;
				y -= diff * 10;
				System.out.println(totalTime);
				Collision(object);
				
			}
			if (KeyInput.checkFacing1 == 1) {
				
				x -= initV * Math.cos(Math.toRadians(angle))* time * 1.1;
				realY = ((initV * Math.sin(Math.toRadians(angle))*time) + 0.5*-9.81*time*time);
				realY2 = ((initV * Math.sin(Math.toRadians(angle))*time1) + 0.5*-9.81*time1*time1);
				diff = realY2 - realY;
				y -= diff * 10;
				Collision(object);
			}
			i++;
		
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture.arrow[0], (int)x, (int)y, 16, 16, null);

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
			
			if (tempObject.getId() == ObjectId.Player2) {
				if (this.getBounds().intersects((Rectangle2D) tempObject.getBounds())) {
					handler.removeObject(KeyInput.arrow);
				}
			}
		}
	}
	
	

}
