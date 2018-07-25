import java.io.*;
import java.lang.*;
import java.util.*;

public class createfile{

	private Formatter x;
	
	public void openFile() {
		try{
			x = new Formatter("chinese.txt");
		}
		catch(Exception e) {
			System.out.println("you have an error");
		}
	}
	
	/*public void addRecords() {
		x.format("%s%s%s", "20", "bucky", "roberts connection is null");
	}
	*/

	public void addRecords(String score) {
		x.format("%s",score);
	}
	
	public void closeFile() {
		x.close();
	}
}

