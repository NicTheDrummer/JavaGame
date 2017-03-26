package dev.nicdepper.rpg;

public class Launcher {
	public static void main(String [] args){
		
		System.out.println("LET'S START THE GAME");
		Game game = new Game("Crafting Game Alpha 0.01", 800, 800);
		game.start(); 
	}
}