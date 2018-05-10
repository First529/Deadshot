package swing;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Texture {
	
	
	private BufferedImage block_sheet = null;
	private BufferedImage assassin_sheet = null;
	private BufferedImage wizard_sheet = null;
	private BufferedImage bow_master_sheet = null;
	private BufferedImage fireball = null;
	private BufferedImage sword = null;
	private BufferedImage arrow_sheet = null;
	
	public BufferedImage[] blocks = new BufferedImage[5];
	public BufferedImage[] bullet = new BufferedImage[2];
	public BufferedImage[] swords = new BufferedImage[4];
	public BufferedImage[] wizard = new BufferedImage[2];
	public BufferedImage[] assassin = new BufferedImage[2];
	public BufferedImage[] bowMaster = new BufferedImage[2];
	public BufferedImage[] arrow = new BufferedImage[1];
	
	public Texture() {
		
		ImageLoader loader = new ImageLoader();
		try {
			block_sheet = loader.loadImage("/2mzh3s1.png");
			wizard_sheet = loader.loadImage("/Wizard.png");
			assassin_sheet = loader.loadImage("/Assassin.png");
			bow_master_sheet = loader.loadImage("/Bow_Master.png");
			fireball = loader.loadImage("/fireball.png");
			sword = loader.loadImage("/weapons.png");
			arrow_sheet = loader.loadImage("/arrow.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {

		blocks[0] = this.grabImage(3, 1, 32, 32, block_sheet);
		blocks[1] = this.grabImage(4, 1, 32, 32, block_sheet);
		blocks[2] = this.grabImage(16, 16, 32, 32, block_sheet);
		blocks[3] = this.grabImage(2, 2, 32, 32, block_sheet);
		blocks[4] = this.grabImage(1, 2, 32, 32, block_sheet);
		wizard[0] = this.grabImage(1, 1, 55, 120, wizard_sheet);
		wizard[1] = this.grabImage(2, 1, 55, 120, wizard_sheet);
		assassin[0] = this.grabImage(1, 1, 59, 120, assassin_sheet);
		assassin[1] = this.grabImage(2, 1, 59, 120, assassin_sheet);
		bowMaster[0] = this.grabImage(1, 1, 50, 120, bow_master_sheet);
		bowMaster[1] = this.grabImage(2, 1, 50, 120, bow_master_sheet);
		bullet[0] = this.grabImage(1, 1, 16, 16, fireball);
		bullet[1] = this.grabImage(2, 1, 16, 16, fireball);
		swords[0] = this.grabImage(1, 1, 64, 64, sword);
		swords[1] = this.grabImage(8, 1, 64, 64, sword);
		swords[2] = this.grabImage(3, 1, 64, 64, sword);
		swords[3] = this.grabImage(6, 1, 64, 64, sword);
		arrow[0] = this.grabImage(1, 1, 16, 16, arrow_sheet);
		
	
		
		
	}
	
	private BufferedImage grabImage(int col, int row, int width, int height, BufferedImage image) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

}
