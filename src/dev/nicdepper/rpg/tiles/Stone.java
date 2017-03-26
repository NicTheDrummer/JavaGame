package dev.nicdepper.rpg.tiles;

import dev.nicdepper.rpg.gfx.Assets;

public class Stone extends Tile {

	public Stone(int id){
		
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid(){
		return true; 
	}
}