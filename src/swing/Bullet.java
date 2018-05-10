package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import application.LevelController;

public class Bullet extends GameObject{
	
	private Handler handler;
	
	public Texture texture = Game.getInstance();
	
	public Bullet(double x, double y, ObjectId id, Handler handler, double velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		if (LevelController.player1Character.equals("wizard")) {
			if (KeyInput.checkFacing1 == -1) {
				x += velocityX;
			} else {
				x -= velocityX;
			}
		}
		
		if (LevelController.player2Character.equals("wizard")) {
			if (KeyInput.checkFacing2 == -1) {
				x += velocityX;
			} else {
				x -= velocityX;
			}
		}
		
		
		
		Collision(object);
		if (Player2.isDamaged()) handler.removeObject(KeyInput.bullet);
		if (Player1.isDamaged()) handler.removeObject(KeyInput.bullet2);
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture.bullet[0], (int) x, (int) y, 40 , 30, null);
		
		
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
					if (handler.object.contains(KeyInput.bullet))
					handler.removeObject(KeyInput.bullet);
					if (handler.object.contains(KeyInput.bullet2))
					handler.removeObject(KeyInput.bullet2);
				}
			}
		}
	}
	

}
