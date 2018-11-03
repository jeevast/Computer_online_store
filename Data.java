import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Data {
	
	public static void main(String[] args)
	{
	try
	{
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","jeeva","joker13"); 
		Statement stmt=con.createStatement();
		String sql = "select cartid from cart where cid = 5";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		System.out.println(rs.getInt(1));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}

}
