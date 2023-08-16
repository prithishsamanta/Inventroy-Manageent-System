package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Connect_Mvn {
	public static void main(String args[]) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "pass");
			
			//what to do? call main function
			Main connect = new Main(con);
			connect.run();
						
			//Shutting down system
			con.close();
		}
		catch(Exception ec) {
			System.out.println(ec);
		}
	}
}

