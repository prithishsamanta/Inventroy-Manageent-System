package Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import Project.orders.*;
import Project.products.*;
import Project.users.*;


public class Main {
	private Connection con;
	
	public Main(Connection con) {
		this.con = con;
	}
	
	public void run() throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("------- HELLO!! Please Login With YOur Credentials -------");
			
		//Login with username and password
		ArrayList access = new ArrayList(new Credentials(con).checkAccess());
			
		if((Boolean) access.get(0) == false)
			return;
		
		System.out.println(" \n You have Logged In \n");
		String name = (String) access.get(1);
		String level = (String) access.get(3);
		int id = (Integer) access.get(2);
			
		int operation = 0;
		System.out.println("------------------ Hello " + name + " ------------------");
		
		while(true) {
			
			System.out.println("------- What Would You Like To Do? -------");
			
			if(level.equals("level1")) {
				//Access for normal users
				//View and add orders
				
				System.out.println("1. View Order");
				System.out.println("2. Add Order");
				System.out.println("3. Exit\n");
				
				operation = sc.nextInt();
				
				switch(operation) {
					case 1:
						Order od = new Order(con, id);
						od.viewOrderCustomer();
						break;
					case 2:
						AddOrder add = new AddOrder(con, id);
						add.addOrder();
						break;
					case 3:
						System.out.println("------- You Have Logged Out, Have a Nice Day!! --------");
						return;
					default: 
						System.out.println("------- Not Valid Operation --------");
				}
			}
			else if(level.equals("level2")) {
				//Access for workers
				//View and Update orders

				System.out.println("1. View Order");
				System.out.println("2. Update Order");
				System.out.println("3. Add Order");
				System.out.println("4. Exit\n");
				
				operation = sc.nextInt();
				
				switch(operation) {
					case 1:
						Order od = new Order(con, id);
						od.viewOrder();
						break;
					case 2:
						UpdateOrder update = new UpdateOrder(con, id);
						update.updateOrder();
						break;
					case 3:
						AddOrder add = new AddOrder(con, id);
						add.addOrder();
						break;
					case 4:
						System.out.println("------- You Have Logged Out, Have a Nice Day!! --------");
						return;
					default: 
						System.out.println("------- Not Valid Operation --------");
				}
			}
			else {
				//Access for Admins
				//View, Update and Delete orders
				
				System.out.println("1. View Order");
				System.out.println("2. Update Order");
				System.out.println("3. Delete Order");
				System.out.println("4. Add Products");
				System.out.println("5. Track History");
				System.out.println("6. View Products");
				System.out.println("7. Add User");
				System.out.println("8. Exit\n");
				
				operation = sc.nextInt();
				
				switch(operation) {
					case 1:
						Order od = new Order(con, id);
						od.viewOrder();
						break;
					case 2:
						UpdateOrder update = new UpdateOrder(con, id);
						update.updateOrder();
						break;
					case 3:
						DeleteOrder delete = new DeleteOrder(con, id);
						delete.deleteOrder();
						break;
					case 4:
						Products prod = new Products(con);
						prod.addProducts();
						break;
					case 5:
						//Tracking History Code
						TrackHistory track = new TrackHistory(con);
						track.trackHistory();
						break;
					case 6:
						//View Products
						Products prod1 = new Products(con);
						prod1.viewProduct();
						break;
					case 7:
						//Add Users
						AddUsers user = new AddUsers(con);
						user.addUsers();
						break;
					case 8:
						System.out.println("------- You Have Logged Out, Have a Nice Day!! --------");
						return;
					default:
						System.out.println("------- Not Valid Operation --------");
				}
			}
			
		}
	}
}