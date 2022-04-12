import java.util.*;
import java.sql.*;
class Accounting  {
	public void call() throws Exception{
		Scanner sc=new Scanner(System.in);
		Connection_DBEstablishment db=new Connection_DBEstablishment();
		Connection con=db.DB_CONN();
		System.out.println("****************************************");
		System.out.println("Enter the roll number:");
		String roll_no=sc.next();
		sc.nextLine();
		System.out.println("Enter the name:");
		String name=sc.next();
		sc.nextLine();
		Accounting  u=new Accounting();
		//u.AddfeesDetails(con);
		System.out.println("************Fees Updation ****************");
		System.out.println("\n              1.Academic fees");
		System.out.println("\n              2.Bus fees");
		System.out.println("\n              3.Hostel fees");
		System.out.println("\n              4.Exit");
		System.out.println("\n**********************************************************\n");
		System.out.println("Enter Your Choice:");
		int n=sc.nextInt();
		switch(n) {
		case 1:
			u.AddfeesDetails(con,roll_no,name);
			break;
		case 2:
			u.AddbusfeesDetails(con,roll_no,name);
			break;
		case 3:
			u.AddhostelfeesDetails(con,roll_no,name);
			break;
		default:
			System.out.println("Invalid Input");
		}
	}
	void AddfeesDetails(Connection con,String ROLL_NO,String NAME) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		Statement s=con.createStatement();
		
		int balance=0,paid_fees=0,total_fees=0;
		String sql1="select TOTAL_FEES,PAID_FEES,BALANCE from ACADEMIC_DETAILS where ROLL_NO='"+ROLL_NO+"' AND NAME='"+NAME+"'";
		ResultSet res1=s.executeQuery(sql1);
		while(res1.next()) {
			total_fees=res1.getInt(1);
			paid_fees=res1.getInt(2);
			balance=res1.getInt(3);
			}
		System.out.println("Enter the pay Amount:");
		int Amount=sc.nextInt();
		balance=balance-Amount;
		paid_fees=paid_fees+Amount;
	String sql2="update ACADEMIC_details set paid_fees='"+paid_fees+"',balance='"+balance+"'where roll_no='"+ROLL_NO+"'";
	int res=s.executeUpdate(sql2);
	if(res>=1) {
	System.out.println("Updated Successfully...!!!");
	}
	else {
		System.out.println("Try Again");
	}
}
	void AddbusfeesDetails(Connection con,String ROLL_NO,String NAME) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		Statement s=con.createStatement();
		
		int balance=0,paid_fees=0,total_fees=0;
		String sql1="select BUS_FEES,PAID_FEES,BALANCE from BUS_FEES_DETAILS where ROLL_NO='"+ROLL_NO+"' AND NAME='"+NAME+"'";
		ResultSet res1=s.executeQuery(sql1);
		while(res1.next()) {
			total_fees=res1.getInt(1);
			paid_fees=res1.getInt(2);
			balance=res1.getInt(3);
			}
		System.out.println("Enter the pay Amount:");
		int Amount=sc.nextInt();
		balance=balance-Amount;
		paid_fees=paid_fees+Amount;
	String sql2="update bus_fees_details set paid_fees='"+paid_fees+"',balance='"+balance+"'where roll_no='"+ROLL_NO+"'";
	int res=s.executeUpdate(sql2);
	if(res>=1) {
	System.out.println("Updated Successfully...!!!");
	}
	else {
		System.out.println("Try Again");
	}
}
	void AddhostelfeesDetails(Connection con,String ROLL_NO,String NAME) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		Statement s=con.createStatement();
		
		int balance=0,paid_fees=0,total_fees=0;
		String sql1="select HOSTEL_FEES,PAID_FEES,BALANCE from HOSTEL_FEES_DETAILS where ROLL_NO='"+ROLL_NO+"' AND NAME='"+NAME+"'";
		ResultSet res1=s.executeQuery(sql1);
		while(res1.next()) {
			total_fees=res1.getInt(1);
			paid_fees=res1.getInt(2);
			balance=res1.getInt(3);
			}
		System.out.println("Enter the pay Amount:");
		int Amount=sc.nextInt();
		balance=balance-Amount;
		paid_fees=paid_fees+Amount;
	String sql2="update hostel_fees_details set paid_fees='"+paid_fees+"',balance='"+balance+"'where roll_no='"+ROLL_NO+"'";
	int res=s.executeUpdate(sql2);
	if(res>=1) {
	System.out.println("Updated Successfully...!!!");
	}
	else {
		System.out.println("Try Again");
	}
}
}

