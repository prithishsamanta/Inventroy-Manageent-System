package Project.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TrackHistory {
	private Connection con;
	
	public TrackHistory(Connection con) {
		this.con = con;
	}
	
	public void trackHistory() throws SQLException {
		try {
			
			String trackingQuery = "SELECT U.cust_id AS user_id, U.cust_name AS user_name, O.product_id  AS product_id, O.total_price AS total_price , P.product_name AS product_name, O.quantity as quantity "
	                + "FROM usertbl U "
	                + "JOIN ORDERS O ON O.cust_id = O.cust_id "
	                + "JOIN PRODUCT P ON O.product_id = P.Product_id";
			
			PreparedStatement prepstmt = con.prepareStatement(trackingQuery);
	        ResultSet rs = prepstmt.executeQuery();
	        System.out.println("\nUser ID\t\tUser Name\tProduct ID\tTotal Price\tProduct Name\tQuantity");
	        
	        while (rs.next()) {
	        	int userID = rs.getInt("user_id");
	            String userName = rs.getString("user_name");
	            int prodId = rs.getInt("product_id");
	            int totalPrice = rs.getInt("total_price");
	            String productName = rs.getString("product_name");
	            int quantity = rs.getInt("quantity");
	            System.out.println(userID + "\t\t" + userName + "\t\t" + prodId + "\t\t" + totalPrice + "\t\t" + productName + "\t\t" + quantity);
	        }
		}
		catch(Exception ec) {
			System.out.println("Invalid Input");
		}
		
        System.out.println("\n");
	}
}
