package dev.nicdepper.rpg.tiles;

import dev.nicdepper.rpg.gfx.Assets;

public class Water extends Tile {

	//private Animation waterAnimation;
	
	public Water(int id){
		
		
		super(Assets.water1, id);
		//waterAnimation = new Animation(250, Assets.water);
	}
	
	@Override
	public boolean isSolid(){
		return true; 
	}
}