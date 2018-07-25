import java.awt.*;
import javax.swing.*;

public class Sprite implements Cloneable
{
	protected Animation a;
	private float x;
	private float y;
	private float velocityX;
	private float velocityY;
	
	//Constructor
	public Sprite(Animation a)
	{
		this.a=a;
	}

	//change position
	public void update(long timePassed)
	{
		x += velocityX*timePassed;
		y += velocityY*timePassed;
		a.update(timePassed); 
	}

	//get sprite x position
	public float getX()
	{
		return x;
	}

	//get sprite y position
	public float getY()
	{
		return y;
	}

	//set sprite x position
	public void setX(float x)
	{
		this.x=x;
	}

	//set sprite y position
	public void setY(float y)
	{
		this.y=y;
	}

	//get sprite width
	public int getWidth()
	{
		return a.getImage().getWidth(null);
	}

	//get sprite height
	public int getHeight()
	{
		return a.getImage().getHeight(null);
	}

	//get horizontal velocity
	public float getVelocityX()
	{
		return velocityX;
	}

	//get vertical velocity
	public float getVelocityY()
	{
		return velocityY;
	}

	//set horizontal velocity
	public void setVelocityX(float velocityX)
	{
		this.velocityX=velocityX;
	}

	//set vertical velocity
	public void setVelocityY(float velocityY)
	{
		this.velocityY=velocityY;
	}

	//get sprite/image 
	public Image getImage()
	{
		return a.getImage();
	}

	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	
}
