package swing;

import java.awt.image.BufferedImage;

public class Texture {
	
	
	private BufferedImage image = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		try {
			image = loader.loadImage("/2mzh3s1.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = this.grabImage(2, 1, 32, 32);
	}
	
	private BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

}
