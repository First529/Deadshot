package swing;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import application.Controller;
import application.LevelController;
import application.Main;
import javafx.stage.Stage;

public class KeyInput extends KeyAdapter {

	public Handler handler;

	public static Bullet bullet;

	public static Sword sword;

	public static Arrow arrow;

	public static boolean pressedL = false;;

	public static int checkFacing1;

	public static int checkFacing2;

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
					checkFacing1 = -1;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelocityX(-5);
					tempObject.setFacing(-1);
					tempObject.setFalling(false);
					checkFacing1 = 1;
				}
				if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelocityY(-10);
				}
				if (key == KeyEvent.VK_G) {
					if (Player1.isSpellBarFull() && tempObject.velocityX == 0) {
						// bullet = new Bullet(tempObject.getX() + 10, tempObject.getY() + 30,
						// ObjectId.Bullet, handler,
						// tempObject.getFacing() * 10);
						// handler.addObject(bullet);
						arrow = new Arrow(tempObject.getX() + 10, tempObject.getY() + 30, ObjectId.Bullet, handler);
						handler.addObject(arrow);
						Player1.resetSpellBar();
					}

				}
				if (LevelController.player1Character.equals("hunter")) {
				
					if (key == KeyEvent.VK_Q) {
						Arrow.angle -= 1;
					}
					if (key == KeyEvent.VK_E) {
						Arrow.angle += 1;
					}
					if (key == KeyEvent.VK_Z) {
						Arrow.initV -= 1;
					}
					if (key == KeyEvent.VK_C) {
						Arrow.initV += 1;
					}
				}

			}

			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelocityX(5);
					tempObject.setFalling(false);
					checkFacing2 = -1;
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelocityX(-5);
					tempObject.setFalling(false);
					checkFacing2 = 1;
				}
				if (key == KeyEvent.VK_CONTROL && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelocityY(-10);
				}
				if (key == KeyEvent.VK_SLASH) {
					if (Player2.isSpellBarFull()) {
						if (!tempObject.isJumping()) {
							pressedL = false;
							sword = new Sword(tempObject.getX(), tempObject.getY(), ObjectId.Sword, handler,
									tempObject.getFacing() * 10);
							handler.addObject(sword);
							Player2.resetSpellBar();

						}
					}
				}
				if (key == KeyEvent.VK_L) {

					if (handler.object.contains(sword)) {
						pressedL = true;

					}

				}

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player1) {
				if (key == KeyEvent.VK_D)
					tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_A)
					tempObject.setVelocityX(0);

			}

			if (tempObject.getId() == ObjectId.Player2) {
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_L)
					pressedL = false;

			}
		}
	}

}
