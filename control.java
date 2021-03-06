package game2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class control implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	//debug
	boolean checkDrawTime = false;
	
	GamePanel gp;
	
	public control(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A ) {
			leftPressed = true;
		}
		
		if(code == KeyEvent.VK_D ) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_UP ) {
			gp.zoomInOut(1);
		}
		if(code == KeyEvent.VK_DOWN ) {
			gp.zoomInOut(-1);
		}
		if(code == KeyEvent.VK_P ) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState ) {
				gp.gameState = gp.playState;
				
			}
		}

	//debug
	if(code == KeyEvent.VK_T) {
		if(checkDrawTime == false) {
			checkDrawTime = true;
		}
		else if(checkDrawTime = true) {
			checkDrawTime = false;
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A ) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D ) {
			rightPressed = false;
		}

	}
}