package Project.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Order {
	private Connection con;
	public int CustID;
	
	public Order(Connection con, int id) {
		this.con = con;
		this.CustID = id;
	}

	public void viewOrderCustomer() throws SQLException {
		PreparedStatement prepStmt = con.prepareStatement("select order_id, product_id, order_date, total_price, quantity from Orders where cust_id = ?");
		
		System.out.println(CustID);
		prepStmt.setInt(1, CustID);
        // SELECT query
		ResultSet rs = prepStmt.executeQuery();
		
		System.out.println("Order_ID   Product_ID  Order_Date  Total_Price  Quantity");
		
        while (rs.next())
        {
        	System.out.println(rs.getInt("ORDER_ID") +"   "+rs.getInt("PRODUCT_ID")+"   "+rs.getDate("ORDER_DATE")+"  "+rs.getInt("TOTAL_PRICE")+"   "+rs.getInt("QUANTITY"));
        }
        
        System.out.println("\n");
        
	}
	
	public void viewOrder() throws SQLException {
		Statement stmt = con.createStatement();		
		
        // SELECT query
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the Order ID: ");
		int OrderID = sc.nextInt();
		
        String q1 = "select * from Orders WHERE Order_ID = " + OrderID + " ";
        ResultSet rs = stmt.executeQuery(q1);
        if (rs.next())
        {
            System.out.println("\nOrder Id: " + rs.getInt(1));
            System.out.println("Product Id: " + rs.getInt(2));
            System.out.println("Customer Id: " + rs.getInt(3));
            System.out.println("Order Date: " + rs.getDate(4));
            System.out.println("Total Price: " + rs.getString(5));
            System.out.println("Quantity: " + rs.getString(6));
        }
        else {
        	System.out.println("This Order Doesn't Exist");
        }
        
        System.out.println("\n");
        
	}
}