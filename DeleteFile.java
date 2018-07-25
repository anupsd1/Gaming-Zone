import java.io.*;

public class DeleteFile {
	public void del(String name)
	{
		File f=new File(name);
		if(f.exists())
			f.delete();
	}
}
