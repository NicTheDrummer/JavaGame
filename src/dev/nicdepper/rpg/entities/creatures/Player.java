package dev.nicdepper.rpg.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.entities.Entity;
import dev.nicdepper.rpg.gfx.Animation;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.inventory.Inventory;

public class Player extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight;
	
	//Attack Timer
	private long lastAttack, attackCooldown = 250, attackTimer = attackCooldown;
	
	//Inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y){
		
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		hitBox.x = 16; 
		hitBox.y = 0; 
		
		hitBox.width = 31; 
		hitBox.height = 62; 
		
		animDown = new Animation(250, Assets.player_down); 
		animUp = new Animation(250, Assets.player_up);
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
	
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		getInput();
		move(); 
		
		handler.getGameCamera().centerOnEntity(this);
		
		checkAttacks();
		
		inventory.tick();
	}
	
	private void checkAttacks() {
		
		//Attack Timer
		attackTimer += System.currentTimeMillis() - lastAttack;
		lastAttack = System.currentTimeMillis(); 
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		//AttackBox
		Rectangle cb = getCollisionBounds(0, 0); 
		Rectangle ar = new Rectangle();
		int arSize = 25; 
		
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			
			ar.x = cb.x + cb.width / 2 - arSize / 2; 
			ar.y  = cb.y - arSize;
		}else if(handler.getKeyManager().aDown) {
			
			ar.x = cb.x + cb.width / 2 - arSize / 2; 
			ar.y  = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			
			ar.x = cb.x - arSize; 
			ar.y  = cb.y + cb.height / 2 - arSize / 2;
		}else if(handler.getKeyManager().aRight) {
			
			ar.x = cb.x + cb.width; 
			ar.y  = cb.y + cb.height / 2 - arSize / 2;
		}else{
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			
			if(e.equals(this))
				continue;
			
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				
				e.hurt(2);
				return;
			}
		}
	}
	
	public void die() {
		
		System.out.println("PLAYER HAS DIED");
	}

	public void getInput(){
		
		xMove = 0;
		yMove = 0; 
		
		if(inventory.isActive())
			return;
		
		
		if(handler.getKeyManager().up)	  //Regular Movement
			yMove = -speed; 
		
		if(handler.getKeyManager().down)
			yMove = speed; 
		
		if(handler.getKeyManager().right)
			xMove = speed;
		
		if(handler.getKeyManager().left)
			xMove = -speed; 
		
		if(handler.getKeyManager().sprint && handler.getKeyManager().right) //Sprinting
			xMove = speed * 2;
		
		if(handler.getKeyManager().sprint && handler.getKeyManager().left)
			xMove = -speed * 2;
		
		if(handler.getKeyManager().sprint && handler.getKeyManager().down)
			yMove = speed * 2;
		
		if(handler.getKeyManager().sprint && handler.getKeyManager().up)
			yMove = -speed * 2;
		
		
		if(handler.getKeyManager().crash){ //Close Game
			
			System.exit(0);
		}
	}
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
								   				(int) (y - handler.getGameCamera().getyOffset()), width, height, null); 
		
		//Visualize Hit-box
		/*
		g.setColor(Color.red);
		g.fillRect((int)(x + hitBox.x - handler.getGameCamera().getxOffset()),
				   (int)(y + hitBox.y - handler.getGameCamera().getyOffset()),
				   			 hitBox.width, hitBox.height);
		*/
	}
	
	public void postRender(Graphics g) {
		
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if (xMove > 0) {
			return animRight.getCurrentFrame();
		}else if (yMove < 0) {
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}