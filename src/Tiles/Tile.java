package Tiles;

import Engine.Screen;
import Sprites.Sprite;

public class Tile {
	public final int id;
	public Sprite tile;
	
	public static Tile[] tiles = new Tile[25000];  //number of tiles I'll eventually have.
	public static Tile grassTile = new GrassTile(0);
	public static Tile rockTile = new RockTile(1);
	
	public Tile(int id){
		this.id = id;
		tiles[id] = this;
		
	}
	public void render(int x, int y, Screen screen){
		
	}
}
