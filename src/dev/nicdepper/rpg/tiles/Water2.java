package dev.nicdepper.rpg.tiles;

import dev.nicdepper.rpg.gfx.Assets;

public class Water2 extends Tile {

	public Water2(int id){
		
		super(Assets.water2, id);
	}
	
	@Override
	public boolean isSolid(){
		return true; 
	}
}