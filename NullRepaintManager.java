import javax.swing.*;

public class NullRepaintManager extends RepaintManager
{
	public static void install()
	{
		RepaintManager repaintManager=new NullRepaintManager();
		repaintManager.setDoubleBufferingEnabled(false);
		RepaintManager.setCurrentManager(repaintManager);
	}

	public void addInvalidComponent(JComponent c)
	{
		//do nothing
	}

	public void addDirtyRegion(JComponent c,int x,int y)
	{
		//do nothing
	}

	public void markCompletelyDirty(JComponent c)	
	{
		//do nothing
	}

	public void paintDirtyRegions()
	{
		//do nothing
	}
}