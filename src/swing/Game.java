package swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	private boolean running = false;
	
	private Thread thread;
	
	public static int WIDTH, HEIGHT;

	public Handler handler;
	
	private BufferedImage level = null;
	
	public static Texture texture;
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		texture = new Texture();
		
		ImageLoader loader = new ImageLoader();
		level = loader.loadImage("/level.png");
	
		handler = new Handler();
		
		loadImageLevel(level);
		
		handler.addObject(new Player1(100, 100, handler, ObjectId.Player1));
		handler.addObject(new Player2(700, 100, handler, ObjectId.Player2));

		
		this.addKeyListener(new KeyInput(handler));
	}

	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSecs = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecs;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
//				System.out.println("FPS: " + updates);
				updates = 0;
			}
		}
		
	}
	
	
	
	private void update() {
		handler.update();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
	
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		//background color
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g2d);
		
		g2d.setColor(Color.gray);
		g2d.fillRect(5, 5, 200, 25); // player2
		g2d.fillRect(600, 5, 200, 25); // player1
		g2d.fillRect(5, 40 ,100, 25);
		g2d.fillRect(600, 40 ,200, 25);
		
		g2d.setColor(Color.green);
		if (Player1.getHP() > 0) g2d.fillRect(5, 5, Player1.getHP(), 25); // player2
		if (Player2.getHP() > 0) g2d.fillRect(600, 5, Player2.getHP(), 25); // player1
		g2d.setColor(Color.blue);
		if (Player1.spellBar < 100)
		g2d.fillRect(5, 40, Player1.spellBar, 25);
		if (Player2.spellBar < 200)
		g2d.fillRect(600, 40, Player2.spellBar, 25);
		
	
		g2d.setColor(Color.white);
		g2d.drawRect(5, 5, 200, 25); // player2
		g2d.drawRect(600, 5, 200, 25); // player1
		
		Font fnt0 = new Font("arial",Font.BOLD, 15);
		g.setFont(fnt0);
		g2d.drawString("Angle: ", 5 , 100);
		g2d.drawString("Velocity: ", 5 , 125);
		g2d.drawString(String.format("%.2f", Arrow.angle), 70, 100);
		g2d.drawString(String.format("%.2f", Arrow.initV), 80, 125);
		
		
		if (Player1.isSpellBarFull()) 
		g2d.fillRect(5, 40, 100, 25);
		
		if (Player2.isSpellBarFull()) 
		g2d.fillRect(600, 40, 200, 25);
		
		
		
		g.dispose();
		bs.show();
		
	}
	
	private void loadImageLevel(BufferedImage image) { 
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println(w + " x " + h);
		
		int count = 0;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				//reference https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255 && green == 255 && blue == 255) {
					count++;
					handler.addObject(new Block(i*32 , j*32 ,0,ObjectId.Block));
				}
				
			}
			
		}
		System.out.println(count);
	}
	
	public static Texture getInstance() {
		return texture;
	}
	

}
