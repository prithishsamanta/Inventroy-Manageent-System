package Project.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteOrder {
	private Connection con;
	public int CustID;
	
	public DeleteOrder(Connection con, int id) {
		this.con = con;
		this.CustID = id;
	}
	
	public void deleteOrder() throws SQLException {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("\nEnter Order ID: ");
	    int orderID = sc.nextInt();

	    String query = "DELETE FROM Orders WHERE Order_ID = ?";
	    	
	    PreparedStatement preparedStatement = con.prepareStatement(query);
	    preparedStatement.setInt(1, orderID);
	        
	    int result = preparedStatement.executeUpdate();

	    if (result > 0) {
	        System.out.println("Order Successfully Deleted");
	    } 
	    else {
	        System.out.println("No order found with the specified ID");
	    }
	    
	    System.out.println("\n");
	}
}
