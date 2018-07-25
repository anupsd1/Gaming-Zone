import java.io.*;
import java.lang.*;
import java.util.*;

public class createfile2{

	private Formatter x;
	
	public void openFile() {
		try{
			x = new Formatter("chinese2.txt");
		}
		catch(Exception e) {
			System.out.println("you have an error");
		}
	}
	
	public void addRecords(int count) {
		x.format("%d%n", count);
	}
	
	public void closeFile() {
		x.close();
	}
}

