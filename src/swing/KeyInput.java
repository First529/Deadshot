package swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	public Handler handler;
	
	public static Bullet bullet;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player1) {
				if (key == KeyEvent.VK_D) {
					tempObject.setVelocityX(5);
					tempObject.setFacing(1);
					tempObject.setFalling(false);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelocityX(-5);
					tempObject.setFacing(-1);
					tempObject.setFalling(false);
				}
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelocityY(-10);
				}
				if (key == KeyEvent.VK_G) {
					if (Player1.isSpellBarFull()) {
						bullet = new Bullet(tempObject.getX() + 10, tempObject.getY() + 30, ObjectId.Bullet, handler, tempObject.getFacing() * 10);
						handler.addObject(bullet);
						Player1.resetSpellBar();
					}
					
					
				}
			}
			
			
			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelocityX(5);
					tempObject.setFalling(false);
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelocityX(-5);
					tempObject.setFalling(false);
				}
				if (key == KeyEvent.VK_CONTROL && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelocityY(-10);
				}
				if (key == KeyEvent.VK_SLASH) {
					if (tempObject.getVelocityX() != 0)
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet2, handler, tempObject.getFacing() * 10));
				}
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player1) {
				if (key == KeyEvent.VK_D) tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_A) tempObject.setVelocityX(0);
				
			}
			
			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT) tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_LEFT) tempObject.setVelocityX(0);
				
			}
		}
	}

}
