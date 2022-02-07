package game2D;

import entity.npc_OldMan;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 9 * gp.tileSize;
		gp.obj[0].worldY = 31 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Key();
		gp.obj[6].worldX = 36 * gp.tileSize;
		gp.obj[6].worldY = 33 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Key();
		gp.obj[7].worldX = 11 * gp.tileSize;
		gp.obj[7].worldY = 10 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 14 * gp.tileSize;
		gp.obj[2].worldY = 18 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 23 * gp.tileSize;
		gp.obj[3].worldY = 22 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 38 * gp.tileSize;
		gp.obj[4].worldY = 7 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 35 * gp.tileSize;
		gp.obj[5].worldY = 21 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Door();
		gp.obj[8].worldX = 10 * gp.tileSize;
		gp.obj[8].worldY = 6 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Chest();
		gp.obj[1].worldX = 8 * gp.tileSize;
		gp.obj[1].worldY = 6 * gp.tileSize;
	}
	
	public void setNPC() {
		
		gp.npc[0] = new npc_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
}
