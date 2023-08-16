package Project.users;


import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Credentials {
	private int U_ID;
	private String password;
	private Connection con;
	private int User;
	private ResultSet rs;
	public Credentials(Connection con) {
		this.con = con;
	}
	
	public ArrayList checkAccess(){
		Scanner sc = new Scanner(System.in);
		int i = 1;
				
		while(true) {
			try {
				System.out.println("------- Please Enter Your Username and Password --------");
				
				PreparedStatement prepStmt = con.prepareStatement("select CUST_PASS, CUST_STATUS, CUST_NAME from usertbl where CUST_ID = ?");
			    
				String check_pass = "";
				String status = "";
				String name = "";
				try {
			    System.out.println("Username: ");
				User = sc.nextInt();
				prepStmt.setInt(1, User);
				
				rs = prepStmt.executeQuery();
				
				while (rs.next()) {
	                check_pass = rs.getString("CUST_PASS");
	                status = rs.getString("CUST_STATUS");
	                name = rs.getString("CUST_NAME");
	                System.out.println("Status: " + status + ", Name: " + name);
	            }
				}
				catch(InputMismatchException ie)
				{
					System.out.println("user id should be 4 digit no");
				}
				
				System.out.println("Password: ");
				password = sc.next();
			    
				List grant = new ArrayList();
				
				if(password.equals(check_pass)) {
					//Write Code for granting access
					
//					System.out.println("enter");
					
					grant.add(true);
					grant.add(name);
					grant.add(User);
					
					if(status.equals("level1")) {
						grant.add("level1");
						return (ArrayList) grant;
					}
					else if(status.equals("level2")) {
						grant.add("level2");
						return (ArrayList) grant;
					}
					else {
						grant.add("level3");
						return (ArrayList) grant;
					}
					
				}
				else {
					//Code for access denied
					if(i > 3) {
						grant.add(false);
						System.out.println("Your User ID and Password are incorrect. Logged Out...");
						return (ArrayList) grant;
					}
	
					System.out.println("Your User ID and Password are incorrect. Please login again...");
					i++;
				}
			}
			catch(InputMismatchException ec) {
				System.out.println("Invalid Input");
				List grant = new ArrayList();
				grant.add(false);
				System.out.println("You are logged out");
				return (ArrayList) grant;
			}
			catch(Exception e) {
				System.out.println(e);
				List grant = new ArrayList();
				grant.add(false);
				System.out.println("You are logged out");
				return (ArrayList) grant;
			}
		}
	}
}
