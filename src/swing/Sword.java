package swing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Random;

import application.LevelController;

public class Sword extends GameObject {

	private Handler handler;

	public Texture texture = Game.getInstance();

	private Random rand = new Random();

	private int n;

	public Sword(double x, double y, ObjectId id, Handler handler, int velocityX) {
		super(x, y, id);
		this.velocityX = velocityX;
		this.handler = handler;
		n = rand.nextInt(2) + 1;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		if (handler.object.contains(KeyInput.sword2)) {
			if (Player1.hitCount >= 10) {
				handler.removeObject(KeyInput.sword2);
				Player1.hitCount = 0;

			}
			if (Player2.xs2 <= 0) {
				x = Player2.xx2 + 2;
				y = Player2.yy2 + 20;
			} else {
				x = Player2.xx2 + 2;
				y = Player2.yy2 + 20;
			}
		}
		if (handler.object.contains(KeyInput.sword)) {
			if (Player2.hitCount >= 10) {
				handler.removeObject(KeyInput.sword);
				Player2.hitCount = 0;

			}
			if (Player1.xs <= 0) {
				x = Player1.xx + 2;
				y = Player1.yy + 20;
			} else {
				x = Player1.xx + 2;
				y = Player1.yy + 20;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (LevelController.player2Character.equals("assassin")) {
			if (KeyInput.checkFacing2 == 1)
				g.drawImage(texture.swords[1], (int) x, (int) y, 40, 30, null);
			else
				g.drawImage(texture.swords[0], (int) x, (int) y, 40, 30, null);
		}
		if (LevelController.player1Character.equals("assassin")) {
			if (KeyInput.checkFacing1 == 1)
				g.drawImage(texture.swords[1], (int) x, (int) y, 40, 30, null);
			else
				g.drawImage(texture.swords[0], (int) x, (int) y, 40, 30, null);
		}
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
