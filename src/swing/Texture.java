package swing;

import java.awt.image.BufferedImage;

public class Texture {
	
	
	private BufferedImage block_sheet = null;
	private BufferedImage player1_sheet = null;
	private BufferedImage player2_sheet = null;
	private BufferedImage fireball = null;
	private BufferedImage sword = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] bullet = new BufferedImage[2];
	public BufferedImage[] player1 = new BufferedImage[1];
	public BufferedImage[] player2 = new BufferedImage[1];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		try {
			block_sheet = loader.loadImage("/2mzh3s1.png");
			player1_sheet = loader.loadImage("/pixel.png");
			player2_sheet = loader.loadImage("/assassin.png");
			fireball = loader.loadImage("/fireball.png");
			sword = loader.loadImage("/weapons_by_kenny1654-darqi7u.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = this.grabImage(9, 2, 32, 32, block_sheet);
		player1[0] = this.grabImage(1, 1, 60, 120, player1_sheet);
		player2[0] = this.grabImage(1, 1, 60, 120, player2_sheet);
		bullet[0] = this.grabImage(1, 1, 16, 16, fireball);
		bullet[1] = this.grabImage(1, 2, 64, 64, sword);
	}
	
	private BufferedImage grabImage(int col, int row, int width, int height, BufferedImage image) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

}
