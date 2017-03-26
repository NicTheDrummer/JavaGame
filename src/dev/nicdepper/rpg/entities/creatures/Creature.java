package dev.nicdepper.rpg.entities.creatures;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.entities.Entity;
import dev.nicdepper.rpg.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_CREATURE_WIDTH = 64,
			  				DEFAULT_CREATURE_HEIGHT = 64;
	 
	public static final float DEFAULT_SPEED = 5f; 
	
	protected float speed; 
	protected float xMove, yMove; 
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		
		super(handler, x, y, width, height);
		 
		speed = DEFAULT_SPEED;
		
		xMove = 0;
		yMove = 0; 
	}

	public void move(){
		
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX(){
		
		if(xMove > 0){//Moving Right
			
			int tx = (int) (x + xMove + hitBox.x + hitBox.width) / Tile.TILEWIDTH; 
			
			if(!collisionWithTile(tx, (int) (y + hitBox.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + hitBox.height) / Tile.TILEHEIGHT)){
				
				x += xMove; 
			}else{
				
				x = tx * Tile.TILEWIDTH - hitBox.x - hitBox.width - 1; 
			}
		}else if (xMove < 0){//Moving Left
			int tx = (int) (x + xMove + hitBox.x) / Tile.TILEWIDTH; 
			
			if(!collisionWithTile(tx, (int) (y + hitBox.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + hitBox.height) / Tile.TILEHEIGHT)){
				
				x += xMove; 
			}else{
				
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - hitBox.x; 
			}
		}
	}
	
	public void moveY(){
		
		if(yMove < 0){ //Up
			
			int ty = (int) (y + yMove + hitBox.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x + hitBox.x) / Tile.TILEWIDTH, ty) &&
			   !collisionWithTile((int)(x + hitBox.x + hitBox.width) / Tile.TILEWIDTH, ty)){
				
				y += yMove;
			}else{
				
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - hitBox.y; 
			}
		}else if(yMove > 0){ //Down
			int ty = (int) (y + yMove + hitBox.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x + hitBox.x) / Tile.TILEWIDTH, ty) &&
			   !collisionWithTile((int)(x + hitBox.x + hitBox.width) / Tile.TILEWIDTH, ty)){
				
				y += yMove;
			}else{
				
				y = ty * Tile.TILEHEIGHT - hitBox.y - hitBox.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		
		return handler.getWorld().getTile(x, y).isSolid(); 
	}
	
	//GETTERS & SETTERS
	
	public int getHealth() {
		
		return health;
	}

	public void setHealth(int health){		
		this.health = health;
	}

	public float getSpeed() {		
		return speed;
	}

	public void setSpeed(float speed){		
		this.speed = speed;
	}

	public static int getDefaultHealth(){		
		return DEFAULT_HEALTH;
	}

	public static float getDefaultSpeed(){		
		return DEFAULT_SPEED;
	}

	public float getxMove(){
		return xMove;
	}

	public void setxMove(float xMove){		
		this.xMove = xMove;
	}

	public float getyMove(){		
		return yMove;
	}

	public void setyMove(float yMove){		
		this.yMove = yMove;
	}
}