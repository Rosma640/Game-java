package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import game2D.GamePanel;

public class Entity {
	
	GamePanel gp;
	public double worldX;
	public double worldY;
	public double speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		double screenX = worldX - gp.player.worldX + gp.player.screenX;
	    double screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
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
		}
	
		
	}
public void setAction() {
	
}
public void update(Graphics g2) {
	
	//setAction();
	

	//gp.cChecker.checkTile(this);
	//gp.cChecker.checkObject(this, false);
	//gp.cChecker.checkPlayer(this);
	
}
		

	public void draw(Graphics2D g2, GamePanel gamePanel) {
		double screenX = worldX - gp.player.worldX + gp.player.screenX;
	    double screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
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
		
			g2.drawImage(image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
		}
		
	}
}
