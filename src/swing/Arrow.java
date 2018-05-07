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
	private double angle = Math.toRadians(60);
	private double initV = 12;
	private double maxH = (0.5*initV*initV*Math.sin(angle)*Math.sin(angle))/9.81;
	private double totalTime =( 2.0 * initV * Math.sin(angle) )/ 9.81;
	private double timePos = totalTime / 30;
	


	public Arrow(double x, double y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (i <= 50) {
			if (i >= 50) handler.removeObject(KeyInput.arrow); 
				
			if (player1Facing == 1) {
				time += timePos;
				
				x += initV * Math.cos(angle)* time ;
				y -= ((initV * Math.sin(angle)*time) + 0.5*-9.81*time*time) ;
				Collision(object);
				System.out.println(y);
			}
			if (player1Facing == -1) {
				time += timePos;
				
				x -= initV * Math.cos(angle)* time ;
				y -= ((initV * Math.sin(angle)*time) + 0.5*-9.81*time*time) ;
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
		}
	}
	
	

}
