package swing;

import java.awt.image.BufferedImage;

public class Texture {
	
	
	private BufferedImage block_sheet = null;
	private BufferedImage player1_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player1 = new BufferedImage[1];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		try {
			block_sheet = loader.loadImage("/2mzh3s1.png");
			player1_sheet = loader.loadImage("/char.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = this.grabImage(2, 1, 32, 32, block_sheet);
		player1[0] = this.grabImage(1, 1, 32, 64, player1_sheet);
	}
	
	private BufferedImage grabImage(int col, int row, int width, int height, BufferedImage image) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

}
