package game2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objects.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	double playTime;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		if(gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			
			text = "You Found The Treasure !";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Your Time :" + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.YELLOW);
	
			text = "Congratulations !";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}
		else { 
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);
			
			//time
			playTime +=(double)1/60;
			g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);
			
			
			// message
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
					
			// play
				if(gp.gameState == gp.playState) {
					drawPlayScreen();
				}
				if(gp.gameState == gp.pauseState) {
					drawPauseScreen();
				}
			//dialogue state
				if(gp.gameState == gp.dialogueState) {
					drawDialogueScreen();
				}		
				}
			}
		}
	}
	public void drawPauseScreen() {
		String text = "PAUSE";
		int x;
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int y = gp.screenHeight/2;
		
		//gp.drawString(text, x, y);
	}
	public void drawPlayScreen() {
		
	}
	public void drawTitleScreen() {
		
		//title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Adventure Game";
		int x = getXforCenteredText (text);
		int y = gp.tileSize * 3;
		
	}

	public int getXforCenteredText(String text) {
		
		return 0;
	}

	public void drawDialogueScreen() {
		//window
		int x = gp.tileSize * 2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 5;
		
		drawSubWindow(x, y, width, height);
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
	}

}
