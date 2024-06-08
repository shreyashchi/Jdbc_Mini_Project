package mini_projects_jdbc;
import java.sql.*;
import java.util.*;
public class ATM {
	public static void main(String[] args)throws ClassNotFoundException,SQLException
	{
		int ch,pin,balance;
		String url,user,pass,name;
		PreparedStatement ps;
		ResultSet rs;
		
		url="jdbc:mysql://localhost:3306/shreyash";
		user="root";
		pass="root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection(url,user,pass);
		
		
		System.out.println("ATM");
		System.out.println("*****************************");
		System.out.println("1:new pin");
		System.out.println("2:change pin");
		System.out.println("3:withdraw");
		System.out.println("4:deposite");
		System.out.println("5:check balance");
		System.out.println("***************************");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your choice");
		ch=sc.nextInt();
		
		switch(ch)
		{		
		case 1:
			ps=conn.prepareStatement("insert into account_holder values(?,?,?)");
			System.out.println("enter the new pin");
			pin=sc.nextInt();
			System.out.println("enter yor name");
			name=sc.next();
			System.out.println("enter the balance");
			balance=sc.nextInt();
			
			ps.setInt(1,pin);
			ps.setString(2,name);
			ps.setInt(3,balance);
			
			int i=ps.executeUpdate();
			System.out.println(i+"insert successful");
			break;
			
		case 2:
		
			System.out.println("enter the old pin");
			pin=sc.nextInt();
			
			ps=conn.prepareStatement("select name from account_holder where pin=?");
			ps.setInt(1,pin);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				String aname=rs.getString("name");
			
					System.out.println("new the  pin");
					int new_pin=sc.nextInt();
					PreparedStatement p = conn.prepareStatement("update account_holder SET pin=? where name=?");
				
					p.setInt(1,new_pin);
					p.setString(2,aname);
					p.executeUpdate();
					System.out.println("update successful");
			
			}
			break;
			
		case 3:
			
			System.out.println("enter the pin");
			pin=sc.nextInt();
			ps=conn.prepareStatement("select balance from account_holder where pin=?");
			ps.setInt(1,pin);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				balance=rs.getInt("balance");
				System.out.println("your balance is"+balance);
				System.out.println("enter the amount you want");
				int withdraw=sc.nextInt();
				if(balance>=withdraw)
				{
					balance=balance-withdraw;
					System.out.println("amount is"+balance);
					PreparedStatement p=conn.prepareStatement("update account_holder SET balance=? where pin=?");
					p.setInt(1, balance);
					p.setInt(2, pin);
					p.executeUpdate();
					System.out.println("update successful"+ balance);
				}
				else
				{
					System.out.println("balance is insuffisient"+ balance);
				}		
		}
			break;
		case 4:

			System.out.println("enter the pin");
			pin=sc.nextInt();
			ps=conn.prepareStatement("select balance from account_holder where pin=?");
			ps.setInt(1,pin);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				balance=rs.getInt("balance");
				System.out.println("your balance is"+balance);
				System.out.println("enter the amount you want");
				int deposite=sc.nextInt();
					balance=balance+deposite;
					System.out.println("amount is"+balance);
					PreparedStatement p=conn.prepareStatement("update account_holder SET balance=? where pin=?");
					p.setInt(1, balance);
					p.setInt(2, pin);
					p.executeUpdate();
					System.out.println("update successful"+ balance);				
		}
			break;
		case 5:
			System.out.println("enter the pin");
			pin=sc.nextInt();
			ps=conn.prepareStatement("select balance from account_holder where pin=?");
			ps.setInt(1,pin);
			rs=ps.executeQuery();
			while(rs.next())
			{
				balance=rs.getInt("balance");
				System.out.println("your balance is"+balance);
			}
}
}
}

