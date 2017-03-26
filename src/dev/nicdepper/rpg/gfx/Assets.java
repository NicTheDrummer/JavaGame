package dev.nicdepper.rpg.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16; 
	
	public static Font font28;
	public static Font font22;
	
	//Tiles
	public static BufferedImage grass, water1, water2, brick, dirt, stone;
	//Static Entities
	public static BufferedImage crystal, tree, boulder;
	//Items
	public static BufferedImage shards, log, rock;
	//Animated Entities
	public static BufferedImage[] player_down, player_up, player_left, player_right; 
	public static BufferedImage[] start_button;
	//public static BufferedImage[] water; 
	
	//Inventory Screen
	public static BufferedImage inventoryScreen;
	
	public static void init(){
		
		font28 = FontLoader.loadFont("res/fonts/PressStart2P.ttf", 28);
		font22 = FontLoader.loadFont("res/fonts/PressStart2P.ttf", 22);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures//inventoryScreen.png"); 
		
		start_button = new BufferedImage[2]; 
		start_button[0] = sheet.crop(0, height * 4, width * 2, height);
		start_button[1] = sheet.crop(0, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(0, height*2, width, height);
		player_down[1] = sheet.crop(width*1, height*2, width, height);
		
		player_up[0] = sheet.crop(width*2, height*2, width, height);
		player_up[1] = sheet.crop(width*3, height*2, width, height);
		
		player_right[0] = sheet.crop(0, height*3, width, height);
		player_right[1] = sheet.crop(width*1, height*3, width, height);
		
		player_left[0] = sheet.crop(width*2, height*3, width, height);
		player_left[1] = sheet.crop(width*3, height*3, width, height);
		
		//water = new BufferedImage[2];
		//water[1] = sheet.crop(0, height, width, height);
		//water[2] = sheet.crop(width, height, width, height);
		
		crystal = sheet.crop(width*2, 0, width, height); 
		water1 = sheet.crop(0, height, width, height); 
		water2 = sheet.crop(width, height, width, height);
		grass = sheet.crop(width*2, height, width, height); 
		brick = sheet.crop(width*3, 0, width, height); 
		dirt = sheet.crop(width*3, height, width, height); 
		stone = sheet.crop(width*4, 0, width, height);
		tree = sheet.crop(width*4, height*2, width, height*2);
		boulder = sheet.crop(width, 0, width, height);
		
		rock = sheet.crop(width*5, height, width, height);
		shards = sheet.crop(width*5, 0, width, height);
		log = sheet.crop(width*4, height, width, height);
	}
}