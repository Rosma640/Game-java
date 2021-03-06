package game2D;

import entity.Entity;
import entity.Player;

public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile (Entity entity) {
		
		int entityLeftWorldX = (int) entity.worldX + entity.solidArea.x;
		int entityRightWorldX = (int) entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = (int) entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = (int) entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		 
		switch(entity.direction) {
		case "up" :
			entityTopRow = (int) (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "down" :
			entityBottomRow = (int) (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "left" :
			entityLeftCol = (int) (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "right" :
			entityRightCol = (int) (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		}
	}
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity solid area posisi
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				// get the object solid area posisition
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up" :
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
					}
				case "down" :
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
					}
				case "left" :
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
					}
				case "right" :
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					break;
				}
				
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
			gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
		}
	}
		return index;
		
	}
	//npc

	public int checkEntity(Player entity, Entity[] target) {
int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				
				// get entity solid area posisi
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				// get the object solid area posisition
				target[i].solidArea.x = (int) (target[i].worldX + target[i].solidArea.x);
				target[i].solidArea.y = (int) (target[i].worldY + target[i].solidArea.y);
				
				switch(entity.direction) {
				case "up" :
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
							entity.collisionOn = true;
							index = i;
						}
					break;
				case "down" :
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.npc[i].solidArea)) {
							entity.collisionOn = true;
							index = i;
						}
					break;
				case "left" :
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.npc[i].solidArea)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				case "right" :
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.npc[i].solidArea)) {
							entity.collisionOn = true;
							index = i;
					break;
	}
}
				
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			target[i].solidArea.x = target[i].solidAreaDefaultX;
			target[i].solidArea.y = target[i].solidAreaDefaultY;
		}
	}
		return index;
		
	}
	public void checkPlayer(Entity entity) {
		// get entity solid area posisi
		entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
		entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
		
		// get the object solid area posisition
		gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
		gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);
		
		switch(entity.direction) {
		case "up" :
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
				}
			break;
		case "down" :
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
				}
			break;
		case "left" :
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
			}
			break;
		case "right" :
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
			break;
			}
		}
		
	entity.solidArea.x = entity.solidAreaDefaultX;
	entity.solidArea.y = entity.solidAreaDefaultY;
	gp.player.solidArea.x = gp.player.solidAreaDefaultX;
	gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
}
