package Engine;
/**
 * @author Andrew
 *
 */
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;


import javax.swing.JFrame;

import Level.Level;
import Sprites.SpriteSheetLoader;

public class Game extends Canvas implements Runnable {

	//Auto-generated this as default serial version ID
	private static final long serialVersionUID = 1L;
	
	public static final int HEIGHT = 240;
	public static final int WIDTH = 240;
	public static final int SCALE = 2;
	public static Dimension GAME_DIM = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	
	public static final String NAME = "Millenia";

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public SpriteSheetLoader loader;
	private Screen screen;
	public Level level;
	public InputHandler input = new InputHandler(this);
	
	private boolean running = false;
	public int xScroll = 0, yScroll = 0;
	
	Random random = new Random();
	
	public void start(){
		running = true;
		new Thread(this).start();
	}
	
	public Game() {
		
	}
	
	public void init(){
		
		loader = new SpriteSheetLoader();
		screen = new Screen(WIDTH,HEIGHT);
		
		level = new Level(16,16);
	}
	
	@Override
	public void run() {
		init();
		while(running){
			tick();
			render();
			
			try{
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}
	
	public void tick(){
		if(input.right) xScroll++;
		if(input.left) xScroll--; 
		if(input.down) yScroll++; 
		if(input.up) yScroll--; 
		
		//screen.render(10, 10, 0, 16, 16);
		//for (int i = 0; i < pixels.length; i++){
			//pixels[i] = random.nextInt();
			//pixels[i] = random.nextInt()<<16;  bit-shift: this turns it red! omgz
	//}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		level.renderBackground(xScroll, yScroll, screen);
		
		for(int y = 0; y < screen.h; y++){
			for(int x = 0; x < screen.w; x++){
				pixels[x+(y*WIDTH)] = screen.pixels[x+(y*screen.w)];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(GAME_DIM);
		game.setMaximumSize(GAME_DIM);
		game.setMinimumSize(GAME_DIM);
		
		JFrame frame = new JFrame(NAME);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.start();
	}

}
