import java.io.*;
import java.lang.*;
import java.util.*;

public class createfile4{

	private Formatter x;
	
	public void openFile() {
		try{
			x = new Formatter("chinese4.txt");
		}
		catch(Exception e) {
			System.out.println("you have an error");
		}
	}
	
	public void addRecords() {
		x.format("%s%s%s", "20", "bucky", "roberts before if");
	}
	
	public void closeFile() {
		x.close();
	}
}
