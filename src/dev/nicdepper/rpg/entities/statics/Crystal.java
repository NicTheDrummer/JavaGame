package dev.nicdepper.rpg.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.items.Item;

public class Crystal extends StaticEntity{

	public Crystal(Handler handler, float x, float y) {
		super(handler, x, y, StaticEntity.DEFAULT_STATIC_ENTITY_WIDTH, StaticEntity.DEFAULT_STATIC_ENTITY_HEIGHT );
		
		hitBox.x = 4;
		hitBox.y = 4;
		hitBox.width = 60;
		hitBox.height = 36; 
		
	}
	
	@Override
	public void tick() {
		
	}
	
	public void die() {
		
		handler.getWorld().getItemManager().addItem(Item.shards.createNew((int)this.x, (int)this.y));
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.crystal, (int)(x - handler.getGameCamera().getxOffset()), 
		                          (int)(y - handler.getGameCamera().getyOffset()), width, height, null); 
		
		/*
		g.setColor(Color.red);
		g.fillRect((int)(x + hitBox.x - handler.getGameCamera().getxOffset()),
				   (int)(y + hitBox.y - handler.getGameCamera().getyOffset()),
				   			 hitBox.width, hitBox.height);
		*/
	}
}