package Project.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddUsers {
	private Connection con;
	
	public AddUsers(Connection con) {
		this.con = con;
	}
	
	public void addUsers() throws SQLException{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter Name: ");
		String name = sc.nextLine();
		System.out.println("Enter Email: ");
		String email = sc.nextLine();
		System.out.println("Enter Phone Number: ");
		long phone = sc.nextLong();
		System.out.println("Enter User Status: ");
		String status = sc.next();
		System.out.println("Enter Your Password: ");
		String password = sc.next();
		
        // Prepare and execute the SQL insert statement
        String query = "INSERT INTO usertbl VALUES ( user_seq.NEXTVAL, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setLong(3, phone);
        preparedStatement.setString(4, status);
        preparedStatement.setString(5, password);
        
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the insertion was successful
        if (rowsAffected > 0) {
            System.out.println("New user record inserted successfully.");
        } else {
            System.out.println("Failed to insert new user record.");
        }
        
        System.out.println("\n");
        return;
    
	}
}
