import java.io.IOException;
import java.sql.*;
public class HighScores {
	static String userid;
	static String highscore;
	/*public HighScores(String userid,String highscore)
	{
		HighScores.userid=userid;
		HighScores.highscore=highscore;
	}*/
	public static void checkScore(String username, String highscore, int count)
	{
		HighScores.userid=username;
		HighScores.highscore=highscore;
		/*createfile4 g2= new createfile4();
		g2.openFile();
		g2.addRecords();
		g2.closeFile(); */
		//highscore="20";
		
		int score=Integer.parseInt(highscore);
		if(count>score)
		{
			try
			{
				createfile3 g21= new createfile3();
				g21.openFile();
				g21.addRecords();
				g21.closeFile();

				System.out.println("Loading drivers and creating connection");
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Program Files//Java//jdk1.8.0_131//bin//GameDB.mdb");
				if(con!=null)
				{
					createfile2 g= new createfile2();
					g.openFile();
					//g.addRecords();
					g.closeFile();

					//Statement st=con.createStatement();
					System.out.println("Before preparestament");
					PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
					int x=pst.executeUpdate();
					if(x>0)
						System.out.println("Updated");
					pst.close();
					con.close();
				}
				else
				{
					createfile g5= new createfile();
					g5.openFile();
					//g5.addRecords();
					g5.closeFile();

				}
			}catch(Exception ex) {}
		}
	}
	
	/*public static void checkHighScore(int count) throws IOException, ClassNotFoundException, SQLException
	{
		java.io.File dbFile=java.io.File.createTempFile("tempdb",".mdb");
		dbFile.deleteOnExit();
		java.nio.file.Files.copy(HighScores.class.getResourceAsStream("/GameDB.mdb"), dbFile.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection con=DriverManager.getConnection("jdbc:ucanaccess://C://Program Files//Java//jdk1.8.0_131//bin//GameDB.mdb");	
		Statement st=con.createStatement();
		System.out.println("Before preparestament");
		PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
		int x=pst.executeUpdate();
		if(x>0)
			System.out.println("Updated");
		pst.close();
		con.close();

	}*/
}
