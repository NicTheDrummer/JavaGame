package dev.nicdepper.rpg.states;

import java.awt.Graphics;

import dev.nicdepper.rpg.Handler;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.ui.ClickListener;
import dev.nicdepper.rpg.ui.UIImageButton;
import dev.nicdepper.rpg.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(final Handler handler) {
		
		super(handler);
		uiManager = new UIManager(handler); 
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(275, 275, 256, 128, Assets.start_button, new ClickListener(){

			public void onClick() {
				
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}
		}));
	}

	@Override
	public void tick() {
		
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		
		uiManager.render(g);
	}
}