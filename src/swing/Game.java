package swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Controller;
import application.LevelController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Canvas implements Runnable {

	private boolean running = false;

	public static String URL = null;

	private Thread thread;

	public static int WIDTH, HEIGHT;

	public Handler handler;

	private BufferedImage level = null;

	public static Texture texture;

	public static Player1 p1;
	public static Player2 p2;

	private Stage stage;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		texture = new Texture();

		ImageLoader loader = new ImageLoader();
		level = loader.loadImage(URL);

		handler = new Handler();

		p1 = new Player1(100, 100, handler, ObjectId.Player1);
		p2 = new Player2(700, 100, handler, ObjectId.Player2);

		handler.loadImageLevel(level);

		handler.addObject(p1);
		handler.addObject(p2);

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
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecs;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + updates);
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

		// background color

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g2d);

		g2d.setColor(Color.gray);
		g2d.fillRect(5, 5, 200, 25); // player2
		g2d.fillRect(600, 5, 200, 25); // player1
		g2d.fillRect(5, 40, 100, 25);
		g2d.fillRect(690, 40, 100, 25);

		g2d.setColor(Color.green);
		if (Player1.getHP() > 0)
			g2d.fillRect(5, 5, Player1.getHP(), 25); // player2
		if (Player2.getHP() > 0)
			g2d.fillRect(600, 5, Player2.getHP(), 25); // player1
		g2d.setColor(Color.blue);
		if (Player1.spellBar < 100)
			g2d.fillRect(5, 40, Player1.spellBar, 25);
		if (Player2.spellBar < 100)
			g2d.fillRect(690, 40, Player2.spellBar, 25);

		g2d.setColor(Color.white);
		g2d.drawRect(5, 5, 200, 25); // player2
		g2d.drawRect(600, 5, 200, 25); // player1

		Font fnt0 = new Font("arial", Font.BOLD, 15);
		g.setFont(fnt0);
		if (LevelController.player1Character.equals("hunter")) {
			g2d.drawString("Angle: ", 5, 100);
			g2d.drawString("Velocity: ", 5, 125);
			g2d.drawString(String.format("%.2f", Arrow.angle), 70, 100);
			g2d.drawString(String.format("%.2f", Arrow.initV), 80, 125);
		}

		if (LevelController.player2Character.equals("hunter")) {
			g2d.drawString("Angle: ", 650, 100);
			g2d.drawString("Velocity: ", 650, 125);
			g2d.drawString(String.format("%.2f", Arrow.angle), 720, 100);
			g2d.drawString(String.format("%.2f", Arrow.initV), 730, 125);
		}

		if (Player1.isSpellBarFull())
			g2d.fillRect(5, 40, 100, 25);

		if (Player2.isSpellBarFull())
			g2d.fillRect(690, 40, 100, 25);

		if (Player2.getHP() <= 0) {
			running = false;
			JOptionPane.showMessageDialog(null, "Player 1 win");
		}
		if (Player1.getHP() <= 0) {
			running = false;
			JOptionPane.showMessageDialog(null, "Player 2 win");
		}

		bs.show();

	}

	public static Texture getInstance() {
		return texture;
	}

}
