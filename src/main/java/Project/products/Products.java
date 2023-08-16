package Project.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Products {
	private Connection con;
	public String PName; 
	public int Quant;
	public int Price;
	
	public Products(Connection con) {
		this.con = con;
	}
	
	public void addProducts() throws SQLException {
		Statement stmt = con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter Product Name: ");
		PName = sc.nextLine();
		System.out.println("Enter Price: ");
		Price = sc.nextInt();
		System.out.println("Enter Quantity: ");
		Quant = sc.nextInt();
		
		if(Price < 0 || Quant < 0) {
			System.out.println("Invalid Input \n");
			return;
		}
		
		PreparedStatement prepstmt = con.prepareStatement("insert into Product( Product_id, Product_Name, Quantity, Cost) values( prod_seq.NEXTVAL, ?, ?, ?)");   
		prepstmt.setString(1, PName);
		prepstmt.setInt(2, Price);
		prepstmt.setInt(3, Quant);
		
		
        int result = prepstmt.executeUpdate();
        
        if (result > 0)           
            System.out.println("Product Added Succesfully");           
        else           
            System.out.println("Error In Adding New Item");
        System.out.println("\n");
	}
	
	public void viewProduct() throws SQLException{
		
		try {
			String sql = "SELECT * FROM product WHERE product_id = ?";
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("\nEnter the Product ID: ");
			int prodID = sc.nextInt();
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        preparedStatement.setInt(1, prodID);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	
	        // Process the query results
	        if (resultSet.next()) {
	            int ProductId = resultSet.getInt("product_id");
	            String productName = resultSet.getString("product_name");
	            int price = resultSet.getInt("cost");
	            int quantity = resultSet.getInt("quantity");
	
	            // Print the retrieved product details
	            System.out.println("\nProduct ID: " + ProductId);
	            System.out.println("Product Name: " + productName);
	            System.out.println("Price: " + price);
	            System.out.println("Description: " + quantity);
	        } else {
	            System.out.println("No product found with the given ID.");
	        }
		}
		catch(Exception ec) {
			System.out.println("Invalid Input");
		}
        
        System.out.println("\n");

	}
}
