package swing;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void update() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.update(object);
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for (int i = 0; i < Game.WIDTH + 32; i += 32) 
			addObject(new Block(i ,Game.HEIGHT - 32,ObjectId.Block));
		
		for (int i = 0; i < Game.HEIGHT + 32; i += 32) 
			addObject(new Block(0 , Game.HEIGHT - i ,ObjectId.Block));
		
		
		for (int i = 0; i < Game.HEIGHT + 32; i += 32) 
			addObject(new Block(Game.WIDTH - 32 ,Game.HEIGHT - i,ObjectId.Block));
		
		for (int j = 0; j < Game.WIDTH - 500; j += 32)
		addObject(new Block(j +200, Game.HEIGHT - 200,ObjectId.Block));
	}
}
