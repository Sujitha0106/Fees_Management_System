import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Student extends Fees {
	Scanner sc=new Scanner(System.in);
	static Student s1=new Student();
	static Connection con=null;
	public Connection dbConnect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/FEES_MANAGEMENT_SYSTEM","root","Sujitha@02");
		return con;
		}
	void viewStudentDetails(String Roll_no) throws Exception{
		String Roll=Roll_no;
		Statement s=con.createStatement();
		String sql="select * From STUDENT_DETAILS where  Student_Details.ROLL_NO='"+Roll+"'";
		System.out.println("                                         Student Details                                      ");
		ResultSet res=s.executeQuery(sql);
		
		while(res.next()) {
			System.out.println("_____________________________________________________________");
			System.out.println("Roll number: "+res.getString(1)+"\nName: "+res.getString(2)+"\nDate of Birth: "+res.getString(3)+"\nDepartment: "+res.getString(4)+"\nPhone No: "+res.getString(5)+"\nE-Mail Id: "+res.getString(6)+ "\nQuota(GQ/MQ): "+res.getString(7)+"\nHosteller: "+res.getString(8)+"\nDay scholar: "+res.getString(9)+"\nStopping: "+res.getString(10)+"\nDistance: "+res.getInt(11));	
			System.out.println("\n_____________________________________________________________");
		}
	}
	void StudentLogin() throws Exception{
		System.out.println("************Student Login****************");
		System.out.println("Enter the user name:");
		String name=sc.next();
		sc.nextLine();
		System.out.println("Enter the password:");
		String password=sc.next();
		sc.nextLine();
		int count=0;
		Statement s=con.createStatement();
		String sql1="select Roll_no from Student_details";
		ResultSet res1=s.executeQuery(sql1);
		while(res1.next()) {
			if(password.equalsIgnoreCase(res1.getString(1))) {
				count++;
			}
		}
		if(count==1) {
		String sql="select name from student_details where roll_no='"+password+"'";
		ResultSet res=s.executeQuery(sql);
		while(res.next()) {
			if(name.equalsIgnoreCase(res.getString(1))) {
				System.out.println("Login Successfully...........!!!");
				System.out.println("*************************************************************");
				System.out.println("\n              1.View Student Details");
				System.out.println("\n              2.View Fees details");
				System.out.println("\n              3.Exit");
				System.out.println("\n**********************************************************");
				System.out.println("\n Enter your choice:");
				int num=sc.nextInt();
				while(num!=3) {
				switch(num) {
				case 1:	
					s1.viewStudentDetails(password);
					break;
				case 2:
				    s1.viewFeesDetails(password);
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
				 s1.StudentLogin();
			}
		}
		}
		else {
			System.out.println("Invalid password......Try Again..");
			s1.StudentLogin();
		}
	}
}