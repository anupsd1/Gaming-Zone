import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import javax.swing.*;

//GAMEMANAGER MANAGES ALL PARTS OF THE GAME

public class GameManager extends Core implements ActionListener
{
	public void actionPerformed(ActionEvent e){}
	public static void main(String[] args)
	{
		new GameManager().run();
	}
	
	public boolean gameOver=false;
	//public boolean fornewLogin;
	public static final float GRAVITY=0.002f;
	//Timer timer=new Timer(20,this);
	public static int count;

	public Point pointCache=new Point();
	public TileMap map;	
	public ResourceManager resourceManager;
	public InputManager inputManager;
	public TileMapRenderer renderer;
	public JPanel pan=new JPanel();
	protected JLabel score;

	protected GameAction moveLeft;
	protected GameAction moveRight;
	protected GameAction pause;
	protected GameAction jump;
	protected GameAction exit;
	
	protected boolean paused;
	public static boolean start;

	protected MenuTest menu;
	protected JFrame w;
	//ScreenManager s=new ScreenManager();
	
	/*public void scoreSet()
	{
		System.out.println("IN SCORESET!!");
		newLogin nl=new newLogin();
		
		//String userid=newLogin.userid;
		String userid=nl.userid;
		//String sscore=newLogin.highscore;
		String sscore=nl.highscore;
		int hscore=Integer.valueOf(sscore);
		if(hscore<count)
		{
			System.out.println("In if!!");
			try
			{
				System.out.println("Loading drivers and creating connection");
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//GameDB.mdb");
				System.out.println("Before preparestament");
				PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
				int x=pst.executeUpdate();
				if(x>0)
					System.out.println("Updated");
				pst.close();
				con.close();
			}catch(Exception ex) {}
		}
	}*/

	
	
	public void init()
	{
		super.init();
		//for(int i=0;i<5;i++)
		start=true;
		//start=false;
		count=0;

		
		//set up input manager
		initInput();

		//start resource manager
		resourceManager=new ResourceManager(s.getFullScreenWindow().getGraphicsConfiguration());
		resourceManager.start=true;
		
		
		//load resources
		renderer=new TileMapRenderer();
		renderer.setBackground(resourceManager.loadImage("background2.png"));

		//load first map
		map=resourceManager.loadNextMap();
		
		paused=false;


		//JFrame f=(JFrame)super.s.getFullScreenWindow();
		//JPanel p=new JPanel();	
		

		score=new JLabel("SCORE");
		//score.setOpaque(false);	
		score.setFont(new Font("Arial",Font.BOLD,70));	
		//f.add(p);
		//p.add(score);
	}

	//Closes any resources used by the GameManager
	public void stop()
	{	
		resourceManager.start=false;
		
		super.stop();
	}

	public void initInput()
	{
		moveLeft=new GameAction("moveLeft");
		moveRight=new GameAction("moveRight");
		jump=new GameAction("jump",GameAction.DETECT_INITIAL_PRESS_ONLY);
		pause=new GameAction("pause",GameAction.DETECT_INITIAL_PRESS_ONLY);
		exit=new GameAction("exit",GameAction.DETECT_INITIAL_PRESS_ONLY);
		
		
		inputManager=new InputManager(s.getFullScreenWindow());
		//inputManager.setCursor(InputManager.INVISIBLE_CURSOR);

		inputManager.mapToKey(moveLeft,KeyEvent.VK_LEFT);
		inputManager.mapToKey(moveRight,KeyEvent.VK_RIGHT);
		inputManager.mapToKey(jump,KeyEvent.VK_SPACE);
		inputManager.mapToKey(exit,KeyEvent.VK_ESCAPE);
		inputManager.mapToKey(pause,KeyEvent.VK_P);
	}

	public void invisibleCursor()
	{
		//inputManager=new InputManager(s.getFullScreenWindow());
		inputManager.setCursor(InputManager.INVISIBLE_CURSOR);

	}

	//Tests whether the game is paused or not
	public boolean isPaused()
	{
		return paused;
	}

	//sets the paused state
	public void setPaused(boolean p)
	{
		if(paused!=p)
		{
			this.paused=p;
			inputManager.resetAllGameActions();
		}
	}
	
	String username;
	String highscore;

	public void setId(String username,String highscore)
	{
		this.username=username;
		this.highscore=highscore;
		
	}

	

	private void checkInput(long elapsedTime)
	{
		if(pause.isPressed())
		{
			setPaused(!isPaused());
		}

		if(exit.isPressed())
		{
			stop();
		}

		if(!isPaused())
		{
			Player player=(Player)map.getPlayer();
			invisibleCursor();
			if(player.isAlive())
			{
				float velocityX=0;
				if(moveLeft.isPressed())
				{
					velocityX-=player.getMaxSpeed();
				}
				if(moveRight.isPressed())
				{
					velocityX+=player.getMaxSpeed();
				}
				if(jump.isPressed())
				{
					player.jump(false);
				}
				player.setVelocityX(velocityX);
			}
		}
	}

	public void draw(Graphics2D g)
	{
		//if(gameOver!=true)
		renderer.draw(g, map, s.getWidth(), s.getHeight());
		if(isPaused())
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",1,50));
			g.drawString("PRESS P TO START",s.getWidth()/2-50,s.getHeight()/2);
		}
		if(resourceManager.start==false)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",1,50));
			g.drawString("GAME OVER! Press p to restart",s.getWidth()/2-50,s.getHeight()/2);
		}
		if(start==true)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",1,50));
			g.drawString("STARTED! PRESS P TO PAUSE",s.getWidth()/2-300,s.getHeight()/2);
			
		}
			
	}

	//Gets the current map
	public TileMap getMap()
	{
		return map;
	}

	/*
	  Gets the tile that a Sprite collides with. Only the Sprite's X or Y should be changed, not both.
		Returns null if no collision is detected
	*/
	public Point getTileCollision(Sprite sprite,float newX, float newY)
	{
		float fromX=Math.min(sprite.getX(),newX);
		float fromY=Math.min(sprite.getY(),newY);
		float toX=Math.max(sprite.getX(),newX);
		float toY=Math.max(sprite.getY(),newY);

		//get the tile locations
		int fromTileX=TileMapRenderer.pixelsToTiles(fromX);
		int fromTileY=TileMapRenderer.pixelsToTiles(fromY);
		int toTileX=TileMapRenderer.pixelsToTiles(toX + sprite.getWidth() - 1);
		int toTileY=TileMapRenderer.pixelsToTiles(toY + sprite.getHeight() - 1);

		//check each tile for a collision
		for(int x=fromTileX; x<=toTileX; x++)
		{
			for(int y=fromTileY; y<=toTileY; y++)
			{
				if(x<0 || x>=map.getWidth() || map.getTile(x,y)!=null)
				{
					//collision found, return tile
					pointCache.setLocation(x,y);
					return pointCache;
				}
			}
		}
		//no collision found
		return null;
	}

	/*
	Checks if two Sprites collide with one another. Returns false if 
	the two Sprites are the same. Returns false if one of the Sprites
	is a Creature that is not alive
	*/
	public boolean isCollision(Sprite s1, Sprite s2)	
	{
		//if the Sprites are the same, return false
		if(s1==s2)
		{
			return false;
		}

		//if one of the sprites is a dead creature, return false
		if(s1 instanceof Creature && !((Creature)s1).isAlive())
		{
			return false;
		}
		if(s2 instanceof Creature && !((Creature)s2).isAlive())
		{
			return false;
		}

		//get the pixel locations of the Sprites
		int s1x=Math.round(s1.getX());
		int s1y=Math.round(s1.getY());
		int s2x=Math.round(s2.getX());
		int s2y=Math.round(s2.getY());

		//check if the two sprites' boundaries intersect
		return(s1x < s2x + s2.getWidth() && s2x < s1x + s1.getWidth() && s1y < s2y + s2.getHeight() && s2y < s1y + s1.getHeight());
	}

	/*
	Gets the Sprite that collides with the specified Sprite,
	or null if no Sprite collides with the specified Sprite.
	*/
	public Sprite getSpriteCollision(Sprite sprite)
	{
		//run through the list of sprites
		Iterator<?> i=map.getSprites();
		while(i.hasNext())
		{
			Sprite otherSprite=(Sprite)i.next();
			if(isCollision(sprite,otherSprite))
			{
				//collision found, return the other goddamn sprite!!!!!
				return otherSprite;
			}
		}
		//no collision found bro! go ahead
		return null;
	}

	//Updates Animation,position, and velocity of all Sprites in the current map.
	public void update(long timePassed)
	{
		Creature player=(Creature)map.getPlayer();
		/*if(timePassed>50)
		{
			start=false;
		}*/
		//player is dead!! Start the map over
		if(player.getState()==Creature.STATE_DEAD)
		{
			resourceManager.start=false;
			if(pause.isPressed())			
			map=resourceManager.reloadMap();
			return;
		}


		//get the inputs!!
		checkInput(timePassed);
	
		//update the bloody player!
		if(!isPaused())
		{
			updateCreature(player,timePassed);
			player.update(timePassed);

			//update other sprites
			Iterator<?> i=map.getSprites();
			while(i.hasNext())
			{
				Sprite sprite=(Sprite)i.next();
				if(sprite instanceof Creature)
				{
					Creature creature=(Creature)sprite;
					if(creature.getState()==Creature.STATE_DEAD)
					{
						i.remove();
					}
					else
					{
						updateCreature(creature,timePassed);
					}
				}
				//normal update
				sprite.update(timePassed);
			}
		}
	}

	/*
	Updates the creature, applying gravity for creatures that aren't
	flying, and check collisions.
	*/
	private void updateCreature(Creature creature,long timePassed)
	{
		//apply gravity
		if(!creature.isFlying())	
		{
			creature.setVelocityY(creature.getVelocityY() + GRAVITY*timePassed);
		}

		//change x
		float dx=creature.getVelocityX();
		float oldX=creature.getX();
		float newX=oldX+(dx*timePassed); //using formula speed x time
	
		//Check if creature has collided with a tile
		Point tile=getTileCollision(creature,newX,creature.getY());
		if(tile==null)
		{
			creature.setX(newX);
		}
		else
		{
			//line up with the tile boundary
			if(dx>0)				
			{
				creature.setX(TileMapRenderer.tilesToPixels(tile.x)-creature.getWidth());
			}
			else if(dx<0)
			{
				creature.setX(TileMapRenderer.tilesToPixels(tile.x + 1));
			}
			creature.collideHorizontal();
		}
		if(creature instanceof Player)
		{
			checkPlayerCollision((Player)creature,false);
		}

		//change y
		float dy = creature.getVelocityY();
        	float oldY = creature.getY();
	        float newY = oldY + (dy*timePassed);
        	tile = getTileCollision(creature, creature.getX(), newY);
	        if (tile == null) 
		{
            		creature.setY(newY);
        	}
        	else 
		{
            		// line up with the tile boundary
            		if (dy > 0) 
			{
                		creature.setY(TileMapRenderer.tilesToPixels(tile.y) - creature.getHeight());
			}
                	else if(dy < 0) 
			{
                		creature.setY(TileMapRenderer.tilesToPixels(tile.y + 1));
			}
               		creature.collideVertical();
	        }
        	if (creature instanceof Player) 
		{
         		boolean canKill = (oldY < creature.getY());
        		checkPlayerCollision((Player)creature, canKill);
	        }
	}

	
	//Check for Player collision with other sprites
	public void checkPlayerCollision(Player player, boolean canKill)
	{
		if(!player.isAlive())
		{
			return;
		}
		
		//check for player collision with other sprites
		Sprite collisionSprite=getSpriteCollision(player);	
		if(collisionSprite instanceof PowerUp)
		{
			acquirePowerUp((PowerUp)collisionSprite);
		}
		else if(collisionSprite instanceof Creature)
		{
			Creature badguy=(Creature)collisionSprite;
			if(canKill)
			{
				//kill the badguy and maker player bounce
				badguy.setState(Creature.STATE_DYING);
				player.setY(badguy.getY()-player.getHeight());
				player.jump(true);
			}	
			else
			{
				//player dies
				//scoreSet();
				//newLogin n1=new newLogin();
				//n1.scoreSet();
				int counter=count;
				//HighScores.checkScore(username,highscore,counter);
				
				//System.out.println(HighScores.userid);
				player.setState(Creature.STATE_DYING);	
				gameOver=true;
		
				//resourceManager.start=false;
				//int newscore=count;
				checkHighScore(count);
				resetScore();
				score.setText(String.valueOf(count));
				//score.setText("0");
				//scoreSet();
			}
		}
	}
	
	public void checkHighScore(int count) {
		// TODO Auto-generated method stub
		//String sscore=newLogin.highscore;
		//this.count=count;
		//String sscore=highscore;
		//int highscore=Integer.parseInt(sscore);
		//createfile f=new createfile();
		//if(count>highscore)
		//{
			createfile2 f2=new createfile2();
			f2.openFile();
			f2.addRecords(count);
			f2.closeFile();
			
			//return count;
		//}
		/*createfile2 f2=new createfile2();
		f2.openFile();
		f2.addRecords(highscore);
		f2.closeFile();
		*/
		//return highscore;
		
	}
	//Gives the player the specified powerup and removes it from the map
	public void acquirePowerUp(PowerUp powerUp)
	{		

		//remove it from the map
		map.removeSprite(powerUp);
		if(powerUp instanceof PowerUp.Star)
		{	
			//give player points here
			count++;
			score.setText(String.valueOf(count));
		
			//updateScore(count);
			
		}

		else if(powerUp instanceof PowerUp.Music)
		{
			//change the music
		}
		
		else if(powerUp instanceof PowerUp.Goal)
		{
			map=resourceManager.loadNextMap();
		}

	}

	public void checkSystemInput()
	{
		if(pause.isPressed())
		{
			setPaused(!isPaused());
		}
		if(exit.isPressed())
		{
			stop();
		}
	}

	/* public void draw(Graphics g)
	{
		Graphics2D g2=(Graphics2D)g;
		g2.drawString(score.getText(),20,0);
	} */
	
	public void run()
	{
		try	
		{
			init();	
			gameLoop();
		}catch(Exception ex){ ex.printStackTrace(); }
		finally
		{
			s.restoreScreen();
		}
	}
	
	/* public void setScreen()
	{
		w=s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		running=true;
	}
	public Window getWindow()
	{
		return s.getFullScreenWindow();
	}	*/
	
	public void resetScore()
	{
		if(count!=0)
		{
			count=0;
			//score.setText(String.valueOf(count));
			//score.setText("0");
		}
		else 
		{
			count=0;

		}
		
	}
}




























