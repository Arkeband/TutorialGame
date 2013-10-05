package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
	
	public boolean up = false, down = false, left = false, right = false;
	
	public InputHandler(Game game){
		game.addKeyListener(this);
	}
	
	public void toggle(KeyEvent key, boolean pressed){
		int keyCode = key.getKeyCode();
	
		if(keyCode == KeyEvent.VK_UP) up = pressed;
		if(keyCode == KeyEvent.VK_DOWN) down = pressed;
		if(keyCode == KeyEvent.VK_LEFT) left = pressed;
		if(keyCode == KeyEvent.VK_RIGHT) right = pressed;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
