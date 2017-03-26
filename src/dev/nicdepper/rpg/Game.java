package dev.nicdepper.rpg;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.nicdepper.rpg.display.Display;
import dev.nicdepper.rpg.gfx.Assets;
import dev.nicdepper.rpg.gfx.GameCamera;
import dev.nicdepper.rpg.inputs.KeyManager;
import dev.nicdepper.rpg.inputs.MouseManager;
import dev.nicdepper.rpg.states.GameState;
import dev.nicdepper.rpg.states.MenuState;
import dev.nicdepper.rpg.states.State;

public class Game implements Runnable {
	
	private int width = 160, height = width / 12 * 9; 
	public String title; 
	                            
	private boolean running = false; 
	private Thread thread; 
	
	private Display display;
	
	private BufferStrategy bs; 
	private Graphics g;   
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera; 
	
	//Handler
	private Handler handler; 
	
	public Game(String title, int height, int width){
	
		this.width = width; 
		this.height = height;  
		this.title = title; 
		
		keyManager = new KeyManager(); 
		mouseManager = new MouseManager();
	}
	
	private void init() {
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init(); 
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0,0);
		
		gameState = new GameState(handler); 
		menuState = new MenuState(handler); 
		
		State.setState(menuState); 
	}
	
	private void tick() { 
		
		keyManager.tick();
		
		if(State.getState() != null){
			
			State.getState().tick(); 
		}
	}
	
	public void run() {
		
		init(); 
		
		int ticks = 0; 
		int fps = 60; 
		
		double timePerTick = 1000000000/fps; 
		double delta = 0; 
		
		long now; 
		long lastTime = System.nanoTime(); 
		long timer = 0; 
		
		
		while(running){
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick; 
			timer += now - lastTime; 
			lastTime = now; 
			
			if(delta >= 1){
				tick();
				render();
				delta--; 
				ticks++; 
			}
			
			if(timer >= 1000000000){
				
				System.out.println("FPS: " + ticks);
				ticks = 0; 
				timer = 0; 
			}
		}
		
		stop(); 
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy(); 
		
		if(bs == null){
			
			display.getCanvas().createBufferStrategy(3); 
			return; 
		}
		
		g =  bs.getDrawGraphics();
		
		g.clearRect(0,0, height, width); 
		
		if(State.getState() != null){
			
			State.getState().render(g); 
		}
		
		bs.show();
		g.dispose(); 
	}
	
	public KeyManager getKeyManager() {
		
		return keyManager; 
	}
	
	public MouseManager getMouseManager() {
		
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		
		return gameCamera; 
	}
	
	public int getWidth() {
		
		return width;
	}
	
	public int getHeight() {
		
		return height;
	}
	
	public synchronized void start() {
		
		if(running){
			
			return; 
		}
		
		running = true; 
		
		thread = new Thread(this); 
		thread.start(); 
	}
	
    public synchronized void stop() {
		
    	if(!running){
    		
    		return; 
    	}
    	
    	running = false; 
    	
    	try {
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}