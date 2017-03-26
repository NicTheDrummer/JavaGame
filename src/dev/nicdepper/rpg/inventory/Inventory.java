package dev.nicdepper.rpg.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.gfx.Text;
import dev.nicdepper.rpg.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invX = 160, invY = 192,
				 invWidth = 512, invHeight = 384,
				 invListCenterX = invX + 171,
				 invListCenterY = invY + invHeight / 2 + 6,
				 invListSpacing = 30;
	
	private int invImageX = 548, invImageY = 226,
				 invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 580, invCountY = 317;
	
	private int selectedItem = 0;
	
	public Inventory(Handler handler) {
		
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		addItem(Item.log.createNew(5));
		addItem(Item.shards.createNew(1));
	}
	
	public void tick() {
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}
	
	public void render(Graphics g) {
		
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		for(int i = -5; i < 6; i++) {
			
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			
			if(i == 0) {	
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, 
							                      invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, 
	                      invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font22);
	}

	//Inventory Logic
	public void addItem(Item item) {
		
		for(Item i : inventoryItems) {
			
			if(i.getId() == item.getId()) {
				
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		
		inventoryItems.add(item); 
	} 
	
	//Getters & Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
	
	
}