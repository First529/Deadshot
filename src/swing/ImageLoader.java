package swing;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String imagePath) {
		try {
			image = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
