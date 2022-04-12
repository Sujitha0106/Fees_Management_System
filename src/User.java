import java.sql.*;
import java.util.*;


public class User{
	public static void main (String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		Student s=new Student();
		Admin a=new Admin();
		System.out.println("************Fees Management System****************");
		System.out.println("\n              1.Admin Login");
		System.out.println("\n              2.Student Login");
		System.out.println("\n               3.Exit");
		System.out.println("\n**********************************************************\n");
		System.out.println("Enter Your Choice:");
		int n=sc.nextInt();
		switch(n) {
		case 1:
			a.dbConnect();
			a.AdminLogin();
			break;
		case 2:
			s.dbConnect();
			s.StudentLogin();
			break;
		case 3:
			break;
		default:
			System.out.println("Invalid Input");
		}
		
	}
}