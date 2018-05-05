package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Player2 extends GameObject {

	private double width = 32, height = 64;
	private double gravity = 0.5;
	private final double MAX_SPEED = 10;
	private static int player2HP = 200;
	private static boolean hit = false;

	public static int spellBar = 1;

	public static double xx = 0;
	public static double xs = 0;
	public static double yy = 0;

	private Handler handler;

	public Texture texture = Game.getInstance();

	public Player2(double x, double y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	public static int getHP() {
		return player2HP;
	}

	public static void resetSpellBar() {
		spellBar = 1;
	}

	public static boolean isSpellBarFull() {
		if (spellBar >= 200)
			return true;
		return false;
	}

	public static boolean isDamaged() {
		return hit;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (velocityX != 0)
			spellBar++;
		x += velocityX;
		y += velocityY;
		xx = x;
		yy = y;
		xs = velocityX;

		if (velocityX < 0)
			facing = -1;
		else
			facing = 1;

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

			if (tempObject.getId() == ObjectId.Bullet) {

				if (getBoundsRight().intersects((Rectangle2D) tempObject.getBounds())) {
					player2HP -= 20;
					hit = true;
				}
				if (getBoundsLeft().intersects((Rectangle2D) tempObject.getBounds())) {
					player2HP -= 20;
					hit = true;
				}
			}

			if (tempObject.getId() == ObjectId.Block) {

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

		}

	}

	@Override
	public void render(Graphics g) {
		if (velocityX == 0) {
			g.drawImage(texture.player2[1], (int) x, (int) y, 60, 90, null);
//			g.drawImage(texture.swords[1], (int) x, (int) y, 40, 30, null);
		} else if (velocityX > 0) {
			g.drawImage(texture.player2[0], (int) x, (int) y, 60, 90, null);
//			g.drawImage(texture.swords[0], (int) x, (int) y, 40, 30, null);
		} else {
			g.drawImage(texture.player2[1], (int) x, (int) y, 60, 90, null);
//			g.drawImage(texture.swords[1], (int) x, (int) y, 40, 30, null);
		}
		// g.setColor(Color.blue);
		// g.fillRect((int)x, (int)y, (int)width, (int)height);
		//
		// Graphics2D g2d = (Graphics2D) g;
		// g.setColor(Color.red);
		// g2d.draw(getBounds());
		// g2d.draw(getBoundsRight());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsTop());

	}

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
