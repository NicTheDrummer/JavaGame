package dev.nicdepper.rpg.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.gfx.Assets;

public class Item {

	//Handler
	public static Item[] items = new Item[128]; 
	
	public static Item log = new Item(Assets.log, "Log", 0); 
	public static Item shards = new Item(Assets.shards, "Shards", 1);
	public static Item rock = new Item(Assets.rock, "Rock", 2);
	
	//Class
	public static final int ITEM_WIDTH = 64, ITEM_HEIGHT = 64;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	protected Rectangle itemHitBox;
	
	public Item(BufferedImage texture, String name, int id) {
		
		this.texture = texture;
		this.name = name;
		this.id = id;
		
		count = 1;
		
		itemHitBox = new Rectangle(x, y, ITEM_WIDTH - 24, ITEM_HEIGHT - 24);
		
		items[id] = this;
	}
	
	public void tick() {
		
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(itemHitBox)) {
		
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g) {
		
		if(handler == null)
			return;
		
		render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}
	
	public void setPosition(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		itemHitBox.x  = x;
		itemHitBox.y = y;
	}

	public Item createNew(int count) {
		
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public Item createNew(int x, int y) {
		
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
}