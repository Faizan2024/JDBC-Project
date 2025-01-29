package com.JDBC.Shop;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class ShopCustomerProject{
	static Scanner sc = new Scanner(System.in);
	static Connection con;
	static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","Kuch@1mysql");
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {	
		// TODO Auto-generated method stub
		System.out.println("\n\t\t\t----------Welcome To Customer Management System----------\n");
        boolean flag = true;
        while (flag) {
        	System.out.println("\n\t\t\t----------Select the number for operation----------");
            System.out.println("\n\t\t\t----------Select 1 to insert the record----------");
            System.out.println("\n\t\t\t----------Select 2 to read the record----------");
            System.out.println("\n\t\t\t----------Select 3 to update the record----------");
            System.out.println("\n\t\t\t----------Select 4 to delete the record----------");
            System.out.println("\n\t\t\t----------Select 5 to read all the records----------");
            System.out.print("\n\t\t\tEnter the choice: ");
            int num = sc.nextInt();
			
			switch(num) {
			case 1:
				insert();
				break;
			case 2:
				read();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				allRecords();
				break;
			default:
				System.out.println("\n\n\t\t\tInvalid Choice!\tPlease Try Again!\n\n");
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				flag = false;
			}
		}
	}
	
	public static void insert() {
		String query = "insert into customer values(?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.print("\t\t\tEnter the id: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			sc.nextLine();
			System.out.print("\t\t\tEnter the name: ");
			String name = sc.nextLine();
			ps.setString(2, name);
			System.out.print("\t\t\tEnter the address: ");
			String address = sc.nextLine();
			ps.setString(3, address);
			System.out.print("\t\t\tEnter the contact number: ");
			int contact = sc.nextInt();
			ps.setInt(4, contact);
			
			int nora = ps.executeUpdate();
			
			if(nora != 0) {
				System.out.println("\n\t\t\tRecord has been inserted succesfully!\n\n\n");
			} else {
				System.out.println("\n\t\t\tRecord has not been inserted succesfully!\n\n\n");
			}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void read() {
		String query = "select * from customer where c_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.print("\t\t\tEnter the customer id: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("\n\t\t\t"+rs.getInt(1)+ " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ "\n\n\n");
			} else {
				System.out.println("\n\t\t\tInvalid customer Id!\n\n\n");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void update() {
		String query = "update customer set c_address = ?, c_contact = ? where c_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.print("\t\t\tEnter the customer address: ");
			sc.nextLine();
			String address = sc.nextLine();
			ps.setString(1, address);
			System.out.print("\t\t\tEnter the customer contact number: ");
			int contact = sc.nextInt();
			ps.setInt(2, contact);
			System.out.print("\t\t\tEnter the customer id: ");
			int id = sc.nextInt();
			ps.setInt(3, id);
			
			
			int nora = ps.executeUpdate();
			
			if(nora != 0) {
				System.out.println("\n\t\t\tRecord has been updated succesfully!\n\n\n");
			} else {
				System.out.println("\n\t\t\tRecord has not been updated succesfully! or Invalid Customer Id\n\n\n");
			}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void delete() {
		String query = "delete from customer where c_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.print("\t\t\tEnter the customer id: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			
			int nora = ps.executeUpdate();
						
			if(nora != 0) {
				System.out.println("\n\t\t\tRecord deleted successfully!\n\n\n");
			} else {
				System.out.println("\n\t\t\tInvalid customer Id!\n\n\n");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void allRecords() {
		String query = "select * from customer";
		System.out.println();
		try {
			Statement ps = con.createStatement();
					
			ResultSet rs = ps.executeQuery(query); 
						
			while(rs.next()) {
				System.out.println("\t\t\t"+rs.getInt(1) + " "
						+ rs.getString(2) + " " + rs.getString(3)
						+ " " + rs.getInt(4));
			} 
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}


