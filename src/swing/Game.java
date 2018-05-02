package swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private boolean running = false;
	
	private Thread thread;
	
	public static int WIDTH, HEIGHT;

	public Handler handler;
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler();
		
		handler.addObject(new Player1(100, 100, handler, ObjectId.Player1));
		handler.addObject(new Player2(200, 100, handler, ObjectId.Player2));
		handler.createLevel();
		
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
		
		g2d.setColor(Color.green);
		if (Player2.getHP() > 0) g2d.fillRect(5, 5, Player2.getHP(), 25); // player2
		if (Player1.getHP() > 0) g2d.fillRect(600, 5, Player1.getHP(), 25); // player1
	
		g2d.setColor(Color.white);
		g2d.drawRect(5, 5, 200, 25); // player2
		g2d.drawRect(600, 5, 200, 25); // player1
		
		g.dispose();
		bs.show();
		
	}
	

}
