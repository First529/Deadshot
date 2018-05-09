package swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
	
	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		System.out.println(w + " x " + h);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// reference
				// https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255 && green == 255 && blue == 255) {
					addObject(new Block(i * 32, j * 32, 0, ObjectId.Block));
				}
				if (red == 255 && green == 106 && blue == 0) {
					addObject(new Block(i * 32, j * 32, 1, ObjectId.Block1));
				}
				if (red == 64 && green == 64 && blue == 64) {
					addObject(new Block(i * 32, j * 32, 3, ObjectId.Block2));
				}
				if (red == 255 && green == 0 && blue == 0) {
					addObject(new Block(i * 32, j * 32, 2, ObjectId.BlockLava));
				}

			}

		}

	}

	
	
	public void clearLevel() {
		this.object.clear();
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	
}
