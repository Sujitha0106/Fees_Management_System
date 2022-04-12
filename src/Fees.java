import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fees {
	void viewFeesDetails(String Roll_no) throws Exception{
		String Roll=Roll_no;
		Connection_DBEstablishment db=new Connection_DBEstablishment();
		Connection con=db.DB_CONN();
		Statement s=con.createStatement();
		
		String sql="select HOSTELLER from STUDENT_DETAILS  where  ROLL_NO='"+Roll+"'";
		ResultSet res1=s.executeQuery(sql);
		String hostel="";
		while(res1.next()) {
			hostel=res1.getString(1);
			}
		if(hostel.equalsIgnoreCase("yes")) {
			String sql1="select a.roll_no,a.name,a.total_fees as academic_fees,h.hostel_fees,a.total_fees+h.hostel_fees as Total_fees,a.paid_fees+h.paid_fees as Paid_fees,a.balance+h.balance as Balance from academic_details a join hostel_fees_details h on a.roll_no=h.roll_no and a.roll_no='"+Roll+"'";
			ResultSet res=s.executeQuery(sql1);
			System.out.println("                                         Fees  Details                                      ");
		
		
		while(res.next()) {
			System.out.println("_____________________________________________________________");
			System.out.println("Roll number: "+res.getString(1)+"\nName: "+res.getString(2)+"\nAcademic Fees: "+res.getInt(3)+"\nHostel Fees: "+res.getInt(4)+"\nTotal Fees: "+res.getInt(5)+"\nPaid status: "+res.getInt(6)+"\nBalance: "+res.getInt(7));	
			System.out.println("\n_____________________________________________________________");
		}
		}
		else {
			String sql2="select day_scholar from STUDENT_DETAILS  where  ROLL_NO='"+Roll+"'";
			ResultSet res2=s.executeQuery(sql2);
			String day_scholar="";
			while(res2.next()) {
				day_scholar=res2.getString(1);
			}
			if(day_scholar.equalsIgnoreCase("cbus")) {
				String sql3="select a.roll_no,a.name,a.total_fees as academic_fees,b.bus_fees,a.total_fees+b.bus_fees as Total_fees,a.paid_fees+b.paid_fees as Paid_fees,a.balance+b.balance as Balance from academic_details a join bus_fees_details b on a.roll_no=b.roll_no and a.roll_no='"+Roll+"'";
				ResultSet res3=s.executeQuery(sql3);
				System.out.println("                                         Fees  Details                                      ");
			
			
			while(res3.next()) {
				System.out.println("_____________________________________________________________");
				System.out.println("Roll number: "+res3.getString(1)+"\nName: "+res3.getString(2)+"\nAcademic Fees: "+res3.getInt(3)+"\nBus Fees: "+res3.getInt(4)+"\nTotal Fees: "+res3.getInt(5)+"\nPaid Fees: "+res3.getInt(6)+"\nBalance: "+res3.getInt(7));	
				System.out.println("\n_____________________________________________________________");
			}
			}
			else {
				String sql4="select * from academic_details where roll_no='"+Roll+"'";
				ResultSet res4=s.executeQuery(sql4);
				System.out.println("                                         Fees  Details                                      ");
			
			
			while(res4.next()) {
				System.out.println("_____________________________________________________________");
				System.out.println("Roll number: "+res4.getString(1)+"\nName: "+res4.getString(2)+"\nAcademic Fees: "+res4.getInt(3)+"\nPaid Fees: "+res4.getInt(4)+"\nBalance: "+res4.getInt(5));	
				System.out.println("\n_____________________________________________________________");
			}
			}
		}
	}
}
