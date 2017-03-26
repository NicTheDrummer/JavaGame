package dev.nicdepper.rpg.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.nicdepper.rpg.Handler;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 10;
	
	protected Handler handler; 
	protected Rectangle hitBox; 
	protected float x, y; 
	protected int width, height; 
	protected int health;
	protected boolean active = true;
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Entity(Handler handler, float x, float y, int width, int height){
        
		health = DEFAULT_HEALTH;
		
		this.handler = handler; 
		
		this.width = width; 
		this.height = height;
		
		this.x = x; 
		this.y = y; 
		
		hitBox = new Rectangle(0,0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public abstract void die();
	
	public void hurt(int damage) {
		
		health -= damage;
		
		if(health <=0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			
			if(e.equals(this))
				continue; 
			
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		
		return false; 
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		
		return new Rectangle((int) (x + hitBox.x + xOffset), 
							  (int) (y + hitBox.y + yOffset), hitBox.width, hitBox.height); 
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x){
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y){
		this.y = y;
	}

	public int getWidth(){
		return width;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getHeight(){
		return height;
	}

	public void setHeight(int height){
		this.height = height;
	}
}