import java.io.*;
import java.util.*;

public class ReadFile {
	private Scanner x;
	
	public boolean openFile(String name)
	{
		try
		{
			//x=new Scanner(new File("chinese.txt"));
			x=new Scanner(new File(name));
			return true;
		}
		catch(Exception ex) { return false; }
		
	}
	
	public String readFile()
	{
		String val=null;
		while(x.hasNext())
		{
			val=x.next();
		}
		return val;
	}
	
	public void closeFile()
	{
		x.close();
	}
}
