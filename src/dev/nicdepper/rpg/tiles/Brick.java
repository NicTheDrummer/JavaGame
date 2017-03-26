package dev.nicdepper.rpg.tiles;

import dev.nicdepper.rpg.gfx.Assets;

public class Brick extends Tile {

public Brick(int id){
		
		super(Assets.brick, id);
	}

	@Override
	public boolean isSolid(){
		return true; 
	}
}
