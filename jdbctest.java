import java.io.*;
import java.sql.*;
class jdbctest 
{
	public static void main(String args[]) throws Exception
	{
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//GameDB.mdb");
		/*Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from `Game Mario` where Username='"+user+"'");
		while (rs.next())
		{
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println("3="+rs.getString(3));
			System.out.println("4="+rs.getString(4));
			System.out.println("5="+rs.getString(5));
		}
		*/
		String userid="Anup";
		int count=70;
		PreparedStatement pst=con.prepareStatement("update `Game Mario` set Score='"+count+"' where Username='"+userid+"'");
		int x=pst.executeUpdate();
		if(x>0)
			System.out.println("Updated");
	}			
}