package Project.orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateOrder {
	private Connection con;
	public int CustID;
	
	public UpdateOrder(Connection con, int id) {
		this.con = con;
		this.CustID = id;
	}
	
	public void updateOrder() throws SQLException {
		Statement stmt = con.createStatement();
        
        // Updating database
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the Order ID: ");
		int OrderID = sc.nextInt();
		
		int ProductID = 0;
		int originalQuant = 0;
		
		String q1 = "select * from Orders WHERE ORDER_ID = " + OrderID + " ";
        ResultSet rs = stmt.executeQuery(q1);
        if (rs.next())
        {
            ProductID = rs.getInt(2);
            originalQuant = rs.getInt(6);
        }
        else
        {
            System.out.println("No Such Order ID Exists \n");
            return;
        } 
        
		System.out.println("Enter the quantity you need?");
		int newQuant = sc.nextInt();
		if(newQuant < 0) {
			System.out.println("Invalid Input \n");
			return;
		}
		int Cost = 0;
		int Quant = 0;
		
		String q2 = "select * from Product WHERE PRODUCT_ID = " + ProductID + " ";
        ResultSet rs2 = stmt.executeQuery(q2);
        if (rs2.next())
        {
            Cost = rs2.getInt(4);
            Quant = rs2.getInt(3);
        }
        else
        {
            System.out.println("No Such Product ID Exists \n");
            return;
        }
		
		int newTotal = Cost * newQuant;
		Quant = Quant + (originalQuant - newQuant);
		
		if(Quant < 0) {
			System.out.println("Insufficient Stock, Update Failed \n");
			return;
		}
		
		String q3 = "UPDATE Orders SET Quantity = " + newQuant + ", Total_Price = " + newTotal + " WHERE Order_ID = " + OrderID + " AND Product_ID = " + ProductID;
		int y = stmt.executeUpdate(q3);
		
		String q4 = "UPDATE Product SET Quantity = " + Quant + " WHERE Product_ID = " + ProductID;
		
        int x = stmt.executeUpdate(q4);
         
        if (x > 0)           
            System.out.println("Record Successfully Updated");           
        else           
            System.out.println("ERROR OCCURRED");
        
        System.out.println("\n");
	}
}
