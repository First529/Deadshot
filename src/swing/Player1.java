package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Random;

import application.LevelController;



public class Player1 extends GameObject {

	private double width = 32, height = 64;
	private double gravity = 0.5;
	private final double MAX_SPEED = 10;
	private static int player1HP;
	private static boolean hit = false;
	public static int hitCount = 0;
	
	public static double xx = 0;
	public static double xs = 0;
	public static double yy = 0;

	public static int spellBar = 1;

	private Handler handler;

	private Random rand;

	public Texture texture = Game.getInstance();

	public Player1(double x, double y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		player1HP = 200;
		rand = new Random();
	}

	public static void resetSpellBar() {
		spellBar = 1;
	}

	public static boolean isSpellBarFull() {
		if (spellBar >= 100)
			return true;
		return false;
	}

	public static int getHP() {
		return player1HP;
	}

	public static boolean isDamaged() {
		return hit;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (player1HP <= 0) handler.removeObject(Game.p1);
		if (velocityX == 0)
			spellBar++;
		x += velocityX;
		y += velocityY;
		xx = x;
		yy = y;
		xs = velocityX;

		if (velocityX < 0) {
			facing = -1;
		}else {
			facing = 1;
		}

		if (falling || jumping) {
			velocityY += gravity;

			if (velocityY > MAX_SPEED)
				velocityY = MAX_SPEED;
		}

		Collision(object);

	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			hit = false;

			if (tempObject.getId() == ObjectId.Sword2) {
				
				if (KeyInput.pressedL) {
					
					
					if (getBoundsRight().intersects((Rectangle2D) tempObject.getBounds())) {
						player1HP -= rand.nextInt(3) + 1;
						hit = true;
						hitCount++;
						KeyInput.pressedL = false;
						

					}
					if (getBoundsLeft().intersects((Rectangle2D) tempObject.getBounds())) {
						player1HP -= rand.nextInt(3) + 1;
						hit = true;
						hitCount++;
						KeyInput.pressedL = false;
						

					}
					
					
				} 
			}
			
			if (tempObject.getId() == ObjectId.Bullet2 || tempObject.getId() == ObjectId.Arrow2) {

				if (getBoundsRight().intersects((Rectangle2D) tempObject.getBounds())) {
					player1HP -= 20;
					hit = true;
				}
				if (getBoundsLeft().intersects((Rectangle2D) tempObject.getBounds())) {
					player1HP -= 20;
					hit = true;
				}
			}

			

			if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.Block1 || tempObject.getId() == ObjectId.Block2) {

				if (getBounds().intersects((Rectangle2D) tempObject.getBounds())) {
					y = tempObject.getY() - (height);
					velocityY = 0;
					falling = false;
					jumping = false;
				} else
					falling = true;

				if (getBoundsTop().intersects((Rectangle2D) tempObject.getBounds())) {
					y = tempObject.getY() + height / 2;
					velocityY = 0;

				}
				if (getBoundsRight().intersects((Rectangle2D) tempObject.getBounds())) {
					x = tempObject.getX() - width;

				}
				if (getBoundsLeft().intersects((Rectangle2D) tempObject.getBounds())) {
					x = tempObject.getX() + 35;

				}

			}
			
			if (tempObject.getId() == ObjectId.BlockLava) {

				if (getBounds().intersects((Rectangle2D) tempObject.getBounds())) {
					player1HP = 0;
				} 

				

			}


		}
	}

	@Override
	public void render(Graphics g) {

		if (LevelController.player1Character.equals("hunter")) {
			if (KeyInput.checkFacing1 == -1) {
				g.drawImage(texture.bowMaster[0], (int) x, (int) y, 50, 90, null);
			

			} else {
				g.drawImage(texture.bowMaster[1], (int) x, (int) y, 50, 90, null);
			

			}
		}
		
		if (LevelController.player1Character.equals("wizard")) {
			if (KeyInput.checkFacing1 == -1) {
				g.drawImage(texture.wizard[0], (int) x, (int) y, 50, 90, null);
			

			} else {
				g.drawImage(texture.wizard[1], (int) x, (int) y, 50, 90, null);
			

			}
		}
		
		
		if (LevelController.player1Character.equals("assassin")) {
			
			if (KeyInput.checkFacing1 == -1) {
				g.drawImage(texture.assassin[0], (int) x, (int) y, 60, 90, null);

			} else  {
				g.drawImage(texture.assassin[1], (int) x, (int) y, 60, 90, null);

			}
			
		}
		
		
	}
		
		

	

	@Override
	public Shape getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Shape getBoundsTop() {
		return new Rectangle((int) ((int) x + (width / 2) - (width / 2 / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Shape getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}

	public Shape getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

}
