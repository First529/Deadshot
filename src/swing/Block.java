package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Block extends GameObject{
	
	public Texture texture = Game.getInstance();
	
	private int type;


	public Block(double x, double y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		if (type == 0) g.drawImage(texture.blocks[0], (int)x, (int)y, null);
		if (type == 1) g.drawImage(texture.blocks[1], (int)x, (int)y, null);
		if (type == 2) g.drawImage(texture.blocks[2], (int)x, (int)y, null);
		if (type == 3) g.drawImage(texture.blocks[3], (int)x, (int)y, null);
	
	}

	@Override
	public Shape getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	


	
	

}
