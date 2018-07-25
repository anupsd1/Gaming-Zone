import java.lang.reflect.Constructor;



public abstract class PowerUp extends Sprite 
{
	public PowerUp(Animation anim)
	{
		super(anim);
	}

	public Object clone()
	{
		//use reflection to create the correct subclass
		Constructor<?> constructor=getClass().getConstructors()[0];
		try
		{
		//return (constructor.newInstance(new Object[] {(Animation)a.clone()}));
		return constructor.newInstance(new Object[] {(Animation)a.clone()});
		}
		catch(Exception ex)
		{
			//should never happen
			ex.printStackTrace();
			return null;
		}
	}

	//A start PowerUp.Gives player points
	public static class Star extends PowerUp
	{
		public Star(Animation anim)
		{
			super(anim);
		}
	}
		
	//A music PowerUp.Changes the game music
	public static class Music extends PowerUp
	{
		public Music(Animation anim)
		{
			super(anim);
		}
	}

	//A Goal PowerUp. Advances to the next map.
	public static class Goal extends PowerUp
	{
		public Goal(Animation anim)
		{
			super(anim);
		}
	}

	/*public void acquirePowerUp(PowerUp powerUp)
	{
		//remove it from map
		map.removeSprite(powerUp);

		if(powerUp instanceof PowerUp.Star)		
		{
			//do something here,like give the player points
		}else if(powerUp instanceof PowerUp.Music)
		{
			//change the music
		}else if(powerUp instanceof PowerUp.Goal)		
		{
			//advance to next map
			map=resourceManager.loadNextMap();
		}
		else
		{}
	}*/
}





















