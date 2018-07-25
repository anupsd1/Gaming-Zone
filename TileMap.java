import java.awt.*;
import java.util.*;
import java.io.*;


public class TileMap
{
	private Image[][] tiles;
	private LinkedList sprites;
	private Sprite player;

	
	//Creates a new TileMap with specified width and height(in number of tiles) of the map
	public TileMap(int width,int height)
	{
		tiles=new Image[width][height];
		sprites=new LinkedList();
	}

	//Gets the width of this TileMap(number of tiles across)
	public int getWidth()
	{
		return tiles.length;
	}

	//Gets the height of this TileMap(number of tiles down)
	public int getHeight()
	{
		return tiles[0].length;
	}

	//Gets the tile at the specified location. Returns null if no tile is at the location
	public Image getTile(int x, int y)
	{
		if(x<0 || x>=getWidth() || y<0 || y>=getHeight())
		{
			return null;
		}
		else
		{
			return tiles[x][y];
		}
	}

	//Sets the tile at the specified location
	public void setTile(int x, int y, Image tile)
	{
		tiles[x][y]=tile;
	}

	//Gets the player Sprite
	public Sprite getPlayer()
	{
		return player;
	}

	//Set the player sprite
	public void setPlayer(Sprite player)
	{
		this.player=player;
	}

	//Adds a Sprite object to this map
	public void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}

	//Removes a Sprite object from this map
	public void removeSprite(Sprite sprite)
	{
		sprites.remove(sprite);
	}

	//Gets an iterator of all Sprites in this map, excluding the player Sprite
	public Iterator getSprites()
	{
		return sprites.iterator();
	}


}






















