package dev.nicdepper.rpg.entities.statics;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.entities.Entity;

public abstract class StaticEntity extends Entity{
	
	public static final int DEFAULT_STATIC_ENTITY_WIDTH = 64, DEFAULT_STATIC_ENTITY_HEIGHT = 64; 
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		
		super(handler, x, y, width, height);
	}	
}