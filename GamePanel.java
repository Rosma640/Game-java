package game2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

	//Pengaturan layar
    final int originalTileSize = 16; //16x16 title
    final int scale = 3;
	
    
    public  int tileSize = originalTileSize * scale; //48x48 title
    public  int maxScreenCol = 16;
    public  int maxScreenRow = 12;
    public  int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public  int screenHeight = tileSize * maxScreenRow; // 576 pixel
    
    //World Setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;
    
    //system
    Graphics2D g2;
    TileManager tileM = new TileManager(this);
    control ctrl = new control(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter (this);
    public UI ui = new UI(this);
    Thread gameThread;
    
  //entity and object
    public Player player = new Player(this,ctrl);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    
    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 30;
    
    // Set player posisi default
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(ctrl);
        this.setFocusable(true);
    }
    
    public void setupGame() {
    	
    	aSetter.setObject();
    	aSetter.setNPC();
    	playMusic(0);
    	gameState = playState;
    	
    }
    public void zoomInOut(int i) {
    	
    	int oldWorldWidth = tileSize * maxWorldCol; //2400
    	tileSize += i;
    	int newWorldWidth = tileSize * maxWorldCol; //2350
    	
    	player.speed = (double)newWorldWidth/600;
    	
    	double multiplier = (double)newWorldWidth/oldWorldWidth;
    	double newPlayerWorldX = player.worldX * multiplier;
    	double newPlayerWorldY = player.worldY * multiplier;
    	
    	player.worldX = newPlayerWorldX;
    	player.worldY = newPlayerWorldY;
    	
    }
    
    
    public void startGameThread() { 
        
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    public void run() {
    	double drawInterval = 1000000000/FPS;
    	double delta = 0;
    	long lastTime = System.nanoTime();
    	long currentTime;
    	long timer = 0;
    	int drawCount = 0;
    	
    	while(gameThread != null) {
    		
    		currentTime = System.nanoTime();
    		
    		delta += (currentTime - lastTime) / drawInterval;
    		timer += (currentTime - lastTime);
    		lastTime = currentTime;
    		 
    		 if(delta >= 1) {
    		update();
    		repaint();
    		delta--;
    		drawCount ++;
    		 }
    		 if(timer >= 1000000000) {
    		 drawCount = 0;
    		 timer = 0;
    	}
      }
    }
    public void update() {
    	//player
    	player.update();
    	//npc
    	for(int i = 0; i < npc.length; i++) {
    		if(npc[i] != null) {
    			npc[i].update(g2);
    		}
    	}
    	
    }
    public void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D)g;
    	
    	//debug
    	long drawStart = 0;
    	if(ctrl.checkDrawTime == true) {
    		drawStart = System.nanoTime();
    	}
    	
    	// title screen
    	if(gameState == titleState) {
    		ui.draw(g2);
    		
    	}
    	
    	//others
    	else {
    		//tile
        	tileM.draw(g2);
        	
        	//object
        	for(int i =0; i < obj.length; i++) {
        		if(obj[i] != null) {
        			obj[i].draw(g2, this);
        		}
        	}
        	
        	//npc
        	//for(int i = 0; i < npc.length; i++) {
        		//if(npc[i] != null) {
        			//npc[i].draw(g2);
        		//}
        	//}
        	
        	//player
        	player.draw(g2);
        	
        	//UI
        	ui.draw(g2);
    	}
    	
    	
    	//debug
    	if(ctrl.checkDrawTime == true) {
    		
    		long drawEnd = System.nanoTime();
        	long passed = drawEnd - drawStart;
        	g2.setColor(Color.white);
        	g2.drawString("Draw Time: " + passed, 10, 400);
        	System.out.println("Draw Time :" + passed);
        	
    	}
    	
    	g2.dispose();
    }
    public void playMusic(int i) {
    	
    	music.setFile(i);
    	music.play();
    	music.loop();
    }
    public void stopMusic() {
    	
    	music.stop();
    }
    public void playerSE(int i) {
    	
    	se.setFile(i);
    	se.play();
    }
}
