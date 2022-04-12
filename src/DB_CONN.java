import java.sql.*;
class Connection_DBEstablishment{
	static Connection con=null;
	public Connection DB_CONN() throws Exception{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/FEES_MANAGEMENT_SYSTEM","root","Sujitha@02");//static class name.method name([parameters);
	
	return con;
	}
}
public class DB_CONN {
		public static void main(String[] args)throws Exception {
				Connection_DBEstablishment c=new Connection_DBEstablishment();
				Connection con=c.DB_CONN();
				
	     }
}

