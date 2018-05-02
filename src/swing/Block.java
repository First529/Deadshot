package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

public class Block extends GameObject{

	public Block(double x, double y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y, 32, 32);
		
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}


	
	

}
