package dev.nicdepper.rpg.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.items.Item;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		
		super(handler, x, y, StaticEntity.DEFAULT_STATIC_ENTITY_WIDTH, StaticEntity.DEFAULT_STATIC_ENTITY_HEIGHT * 2);
		
		hitBox.x = 25;
		hitBox.y = 58;
		hitBox.width = 21;
		hitBox.height = 70;
		
	}

	@Override
	public void tick() {
		
	}
	
	public void die() {
		
		handler.getWorld().getItemManager().addItem(Item.log.createNew((int)x, (int)y + 64));
		handler.getWorld().getItemManager().addItem(Item.log.createNew((int)x, (int)y));
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()), 
		                          (int)(y - handler.getGameCamera().getyOffset()), width, height, null); 
		/*
		g.setColor(Color.red);
		g.fillRect((int)(x + hitBox.x - handler.getGameCamera().getxOffset()),
			   (int)(y + hitBox.y - handler.getGameCamera().getyOffset()),
				   			 hitBox.width, hitBox.height);
		*/
	}
}