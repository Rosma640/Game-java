package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game2D.GamePanel;
import game2D.control;

public class Player extends Entity {
	GamePanel gp;
	control ctrl;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	int counter2 = 0;
	boolean moving = false;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, control ctrl) {
		
		super(gp);
		this.gp = gp;
		this.ctrl = ctrl;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 43;
		speed = 4;
		speed = gp.worldWidth/600;
		direction = "right";
		
	}
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
			right1= ImageIO.read(getClass().getResourceAsStream("/player/Right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update () {
		
		if(moving == false) {
			
			if(ctrl.upPressed == true || ctrl.downPressed == true || ctrl.leftPressed == true || ctrl.rightPressed == true) {			
				
				if(ctrl.upPressed == true) {
					direction = "up";
		    	}
		    	else if(ctrl.downPressed == true ) {
		    		direction = "down";
		    	}
		    	else if(ctrl.leftPressed == true) {
		    		direction = "left";
		    	}
		    	else if(ctrl.rightPressed == true) {
		    		direction = "right";
		    	}
				
				moving = true;
				
				// chechk tile collision
				collisionOn = false;
				gp.cChecker.checkTile(this);
				
				//check npc coolision
				//int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
				//interactNPC(npcIndex);
				
				//chechk object collision
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);
			}
				else {
					if(standCounter == 20) {
						spriteNum = 1;
						standCounter = 0;
					}
				
				}
		}
		
		if(moving == true) {	
		
		// if false collsion is false, player can move
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up" : worldY -= speed; break;
			case "down" : worldY += speed; break;
			case "left" : worldX -= speed; break;
			case "right" : worldX += speed; break;
				}
		}
		 
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		pixelCounter += speed;
		
		if(pixelCounter == 48) {
			moving = false;
			pixelCounter = 0;
		}
	  }
		
	}
	public void pickUpObject(int i) {
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key" :
				gp.playerSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("Kamu Mendapatkan Kunci!");
				break;
			case "Door" :
				gp.playerSE(3);
				gp.ui.showMessage("Pintu Terbuka!");
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					
				}
				else {
					gp.ui.showMessage("Kamu Tidak Punya Kunci!");
				}
				break;
			case "Chest" :
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playerSE(4);
				break;
				
			}
		}
	}
	
	//public void interactNPC(int i) {
		
		//if(i != 999) {
			
			//gp.gameState = gp.dialogueState;
			
			
		//}//
	//}
	public void draw (Graphics2D g2) {
		
	 //   g2.setColor(Color.white);
    //	  g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
			image = up1;
		}
			if(spriteNum == 2) {
			image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
			image = down1;
		}
			if(spriteNum == 2) {
			image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
			image = left1;
		}
			if(spriteNum == 2) {
			image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
			image = right1;
		}
			if(spriteNum == 2) {
			image = right2;
			}
			break;
		}
		
		//int x = screenX;
		//int y = screenY;
		
		///if(screenX > worldX) {
			//x = (int) worldX;
		//}
		//if(screenY > worldY) {
			//y = (int) worldY;
		//}
		//int rightoffset = gp.screenWidth - gp.player.screenX;
		//if(rightoffset > gp.worldWidth - gp.player.worldX) {
			//x = (int) (gp.screenWidth - (gp.worldWidth - worldX));	
		//}
		//int bottomOffset = gp.screenHeight - gp.player.screenY;
		//if(bottomOffset > gp.worldHeight - gp.player.worldY) {
			//y = (int) (gp.screenHeight - (gp.worldHeight - worldY));
		//}
		
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
