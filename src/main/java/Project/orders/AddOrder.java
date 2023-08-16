package Project.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddOrder {
	private Connection con;
	public int ProdID;
	public int CustID; 
	public int NewQuant;
	public int TotalPrice;
	
	public AddOrder(Connection con, int id) {
		this.con = con;
		this.CustID = id;
	}
	
	public void addOrder() throws SQLException {
		try {
		    Scanner sc = new Scanner(System.in);
		    System.out.println("\nEnter Product ID: ");
		    int prodID = sc.nextInt();
		    System.out.println("Enter Quantity: ");
		    int newQuant = sc.nextInt();
		    
		    if(newQuant < 0) {
		    	System.out.println("Invalid Input \n");
		    	return;
		    }
		    
		    String fetch = "SELECT quantity, cost FROM Product WHERE Product_ID = ?";
		    
		    PreparedStatement ps = con.prepareStatement(fetch);
		    ps.setInt(1, prodID);
		    ResultSet rs = ps.executeQuery();
		    
		    int cost = 0;
		    int quant = 0; // Initialize with a default value
		    if (rs.next()) { // Check if there's a row in the result set
		        quant = rs.getInt("quantity");
		        cost = rs.getInt("cost");
		    } else {
		        System.out.println("Product not found \n");
		        return;
		    }
		    
		    System.out.println("Available Quantity: " + quant);
		    System.out.println("Cost: " + cost);
		    
		    // Check inventory and reduce quantity
		    if (quant >= newQuant) {
		        int totalPrice = newQuant * cost;
		        
		        PreparedStatement prepstmt = con.prepareStatement(
		            "INSERT INTO Orders(order_id, product_id, cust_id, total_price, quantity) VALUES(order_seq.NEXTVAL, ?, ?, ?, ?)"
		        );  
		        prepstmt.setInt(1, prodID);
		        prepstmt.setInt(2, CustID); // You need to define CustID somewhere
		        prepstmt.setInt(3, totalPrice);
		        prepstmt.setInt(4, newQuant);
		        
		        int result = prepstmt.executeUpdate();
		        
		        Statement stmt = con.createStatement();
		        quant = quant - newQuant;
				String q4 = "UPDATE Product SET Quantity = " + quant + " WHERE Product_ID = " + prodID;
				int x = stmt.executeUpdate(q4);
		        
		        if (result > 0) {
		            System.out.println("Order Confirmed");
		        } else {
		            System.out.println("Order Failed");
		        }
		    } 
		    else {
		        System.out.println("Insufficient Stock, Order Failed");
		    }
	    
		}
		catch(Exception ex) {
			System.out.println("Invalid Input");
		}
	    
	    System.out.println("\n");
	    return;
	}

}
