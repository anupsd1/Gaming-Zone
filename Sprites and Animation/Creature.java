import java.lang.reflect.Constructor;
import java.lang.*;
//CLONING ERRORS!!

public abstract class Creature extends Sprite
{
	//Amount of time to go from STATE_DYING to STATE_DEAD
	private static final int DIE_TIME=1000;
	
	public static final int STATE_NORMAL=0;
	public static final int STATE_DYING=1;	
	public static final int STATE_DEAD=2;

	protected Animation left;
	protected Animation right;
	protected Animation deadLeft;
	protected Animation deadRight;
	private int state;
	private long stateTime;

	//Creates a new Creature with specified Animations
	public Creature(Animation left, Animation right, Animation deadLeft, Animation deadRight)
	{
		super(right);
		this.left=left;
		this.right=right;
		this.deadLeft=deadLeft;
		this.deadRight=deadRight;
		state=STATE_NORMAL;
	}
	
	public Object clone() 
	{
		
		//use reflection to create the correct subclass
		Constructor constructor=getClass().getConstructors()[0];
		try
		{
			return constructor.newInstance(new Object[] {
				((Animation)left).clone(),
				((Animation)right).clone(),
				((Animation)deadLeft).clone(),
				((Animation)deadRight).clone()
			});
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	//Gets maximum speed of this creature
	public float getMaxSpeed()
	{
		return 0;
	}

	//Wakes up the creature when the Creature first appears on screen. Normally, the creature starts moving left.
	public void wakeUp()
	{
		if(getState()==STATE_NORMAL && getVelocityX()==0)
		{
			setVelocityX(-getMaxSpeed());
		}
	}

	//Gets the state of this creature. 
	public int getState()
	{
		return state;
	}

	//sets the state of this creature
	public void setState(int state)
	{
		if(this.state != state)
		{
			this.state=state;
			stateTime=0;
			if(state==STATE_DYING)
			{
				setVelocityX(0);
				setVelocityY(0);
			}
		}
	}

	//Checks if the creature is alive
	public boolean isAlive()
	{
		return (state==STATE_NORMAL);
	}

	//check if this crature is flying
	public boolean isFlying()
	{
		return false;
	}

	//Called before update() if the creature collided with a tile horizontally
	public void collideHorizontal()
	{
		setVelocityX(-getVelocityX());
	}

	//Called before update() if the creature collided with a tile vetically
	public void collideVertical()
	{
		setVelocityY(0);
	}

	//updates the animation for this creature
	public void update(long elapsedTime)
	{
		//select the correct animation
		Animation newAnim=a;
		if(getVelocityX()<0)
		{
			newAnim=left;
		}
		else if(getVelocityX()>0)
		{
			newAnim=right;
		}
		if(state==STATE_DYING && newAnim==left)
		{
			newAnim=deadLeft;
		}
		else if(state==STATE_DYING && newAnim==right)
		{
			newAnim=deadRight;
		}

		//Update the animation
		if(a!=newAnim)
		{
			a=newAnim;
			a.start();
		}
		else
		{
			a.update(elapsedTime);
		}

		//update to "dead" state
		stateTime+=elapsedTime;
		if(state==STATE_DYING && stateTime>=DIE_TIME)
		{
			setState(STATE_DEAD);
		}
	}

	
}



















