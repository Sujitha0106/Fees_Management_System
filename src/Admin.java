import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Admin extends  Accounting {
	Scanner sc=new Scanner(System.in);
	static Admin a1=new Admin();
	static Connection con=null;
	public Connection dbConnect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/FEES_MANAGEMENT_SYSTEM","root","Sujitha@02");
		return con;
		}
	void viewDetails() throws Exception{
		Statement s=con.createStatement();
		String sql="select * from STUDENT_DETAILS ";
		System.out.println("                                         Student Details                                      ");
		ResultSet res=s.executeQuery(sql);
		
		while(res.next()) {
			System.out.println("_____________________________________________________________");
			System.out.println("Roll number: "+res.getString(1)+"\nName: "+res.getString(2)+"\nDate of Birth: "+res.getString(3)+"\nDepartment: "+res.getString(4)+"\nPhone No: "+res.getString(5)+"\nE-Mail Id: "+res.getString(6)+ "\nQuota(GQ/MQ): "+res.getString(7)+"\nHosteller: "+res.getString(8)+"\nDay scholar: "+res.getString(9)+"\nStopping: "+res.getString(10)+"\nDistance: "+res.getInt(11));	
			System.out.println("\n_____________________________________________________________");
		}
	}
	void addDetails() throws Exception{

		Statement s=con.createStatement();
		System.out.println("****************************************");
		System.out.println("Enter the roll number:");
		String ROLL_NO=sc.next();
		sc.nextLine();
		System.out.println("Enter the name:");
		String NAME=sc.next();
		sc.nextLine();
		System.out.println("Enter the date of birth(yyyy/mm/dd):");
		String DOB=sc.next();
		sc.nextLine();
		System.out.println("Enter the department:");
		String DEPARTMENT=sc.next();
		sc.nextLine();
		System.out.println("Enter the phone number:");
		String PHONE_NO=sc.next();
		sc.nextLine();
		System.out.println("Enter the email id:");
		String EMAIL_ID=sc.next();
		sc.nextLine();
		System.out.println("Enter the quota(GQ/MQ):");
		String QUOTA=sc.next();
		sc.nextLine();
		int TOTAL_FEES;
			if(QUOTA.equalsIgnoreCase("GQ")) {
				 TOTAL_FEES=85000;
			}
			else {
				 TOTAL_FEES=140000;
			}
			int BALANCE=TOTAL_FEES;
			String Hosteller,Day_Scholar="No",Stopping="-";
			System.out.println("HOSTELLER/DAY SCHOLAR:");
			String str=sc.next();
			int Distance=0;
			sc.nextLine();
			if(str.equalsIgnoreCase("Hosteller")) {
				Hosteller="Yes";
			}
			else {
				Hosteller="No";
				System.out.println("IF YOU ARE A DAY SCHOLAR(CBUS/OWN TRANSPORT FACILITY):");
				Day_Scholar=sc.next();
				sc.nextLine();
				if(Day_Scholar.equalsIgnoreCase("cbus")) {
				System.out.println("Enter the Stopping:");
				Stopping=sc.next();
				sc.nextLine();
				
				System.out.println("Enter the distance:");
			 Distance=sc.nextInt();
				}
			}
			
			
		String sql="insert into STUDENT_DETAILS values('"+ROLL_NO+"','"+NAME+"','"+DOB+"','"+DEPARTMENT+"','"+PHONE_NO+"','"+EMAIL_ID+"','"+QUOTA+"','"+Hosteller+"','"+Day_Scholar+"','"+Stopping+"','"+Distance+"')";
		String sql2="insert into ACADEMIC_DETAILS(ROLL_NO,NAME,TOTAL_FEES,BALANCE) values('"+ROLL_NO+"','"+NAME+"','"+TOTAL_FEES+"','"+BALANCE+"')";
		int res=s.executeUpdate(sql);
		int res1=s.executeUpdate(sql2);
		int TOTAL_FEES1=80000;
		int BALANCE1=TOTAL_FEES1;
		int TOTAL_FEES2=Distance*2000;
		int BALANCE2=TOTAL_FEES2;
		if(Hosteller.equalsIgnoreCase("yes")) {
			String sql3="insert into HOSTEL_FEES_DETAILS(ROLL_NO,NAME,HOSTEL_FEES,BALANCE) values('"+ROLL_NO+"','"+NAME+"','"+TOTAL_FEES1+"','"+BALANCE1+"')";
			int res2=s.executeUpdate(sql3);
		}
		else {
			if(Day_Scholar.equalsIgnoreCase("Cbus")) {
				String sql4="insert into BUS_FEES_DETAILS(ROLL_NO,NAME,BUS_FEES,BALANCE) values('"+ROLL_NO+"','"+NAME+"','"+TOTAL_FEES2+"','"+BALANCE2+"')";
				int res3=s.executeUpdate(sql4);
			}
		}
				
		if(res==1&& res1==1) {
		System.out.println("Inserted successfully ");
		}
		else {
			System.out.println("Not inserted. Try again..!!!");
		}
	}
	void AdminLogin() throws Exception{
		System.out.println("************Admin Login****************");
		System.out.println("Enter the user name:");
		String name=sc.next();
		sc.nextLine();
		System.out.println("Enter the password:");
		String password=sc.next();
		sc.nextLine();
		int count=0;
		Statement s=con.createStatement();
		String sql1="select Admin_id from Admin";
		ResultSet res1=s.executeQuery(sql1);
		while(res1.next()) {
			if(password.equalsIgnoreCase(res1.getString(1))) {
				count++;
			}
		}
		if(count==1) {
		String sql="select ADMIN_NAME from ADMIN where ADMIN_ID='"+password+"'";
		ResultSet res=s.executeQuery(sql);
		while(res.next()) {
			if(name.equalsIgnoreCase(res.getString(1))) {
				System.out.println("Login Successfully...........!!!");
				System.out.println("*************************************************************");
				System.out.println("\n              1.Add New Student");
				System.out.println("\n              2.View Student details");
				System.out.println("\n              3.Update Fees details");
				System.out.println("\n              4.Exit");
				System.out.println("\n**********************************************************");
				System.out.println("\n Enter your choice:");
				int num=sc.nextInt();
				while(num!=4) {
				switch(num) {
				case 1:	
					a1.addDetails();
					break;
				case 2:
					a1.viewDetails();
					break;
				case 3:
					a1.call();
					
					break;

				default:
					System.out.println("Invalid Input");
				}
				System.out.println("Enter your choice:");
				num=sc.nextInt();
				}
				System.out.println("Successfully Logged Out");
			}
			else {
				System.out.println("Invalid Username..Try Again..");
				 a1.AdminLogin();
			}
		}
		}
		else {
			System.out.println("Enter the correct password......");
			 a1.AdminLogin();
		}
	}
	
}