package mini_projects_jdbc;
import java.sql.*;
import java.util.*;
public class authentication_sytem
{
public static void main(String[] args)throws ClassNotFoundException,SQLException
{
	PreparedStatement ps;
	ResultSet rs;
	
	String url,user,pass;
	int ch,password,contact_no,confirm_pass;
	String email_id,name,address;
	url="jdbc:mysql://localhost:3306/shreyash";
	user="root";
	pass="root";
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn=DriverManager.getConnection(url,user,pass);
	System.out.println("Authentication system");
	System.out.println("***************************");
	System.out.println("1:Registration");
	System.out.println("2:login");
	System.out.println("3:exit");
	
	Scanner sc=new Scanner(System.in);
	System.out.println("enter your choice");
	ch=sc.nextInt();	
	
	switch(ch)
	{
	case 1:
		System.out.println("enter email id");
		email_id=sc.next();
		ps=conn.prepareStatement("select email_id from user");
		rs=ps.executeQuery();
		while(rs.next())
		{
			String nemail=rs.getString("email_id");
			if(email_id==nemail)
			{
				System.out.println("email_id already exist");
				break;
			}
			else
			{
			
				ps=conn.prepareStatement("insert into user(email_id,name,password,address,contact_no) values(?,?,?,?,?)");
				
				System.out.println("enter your name");
				name=sc.next();
				System.out.println("enter your password");
				password=sc.nextInt();
				System.out.println("enter confirm password");
				confirm_pass=sc.nextInt();
				if(password==confirm_pass)
				{
					
				System.out.println("enter yor address");
				address=sc.next();
				System.out.println("enter your contact no");
				contact_no=sc.nextInt();
		
				ps.setString(1,email_id);
				ps.setString(2, name);
				ps.setInt(3,password);
				ps.setString(4, address);
				ps.setInt(5,contact_no);
				
				int i=ps.executeUpdate();
				System.out.println(i +"update successfilly");
				}
				else {
					System.out.println("password does not match");
				}
			}
		}
		break;
	case 2:
		System.out.println("enter email id");
		email_id=sc.next();
		ps=conn.prepareStatement("select email_id from user");
		rs=ps.executeQuery();
		while(rs.next())
		{
			String nemail=rs.getString("email_id");
			if(nemail.equals(email_id))
			{
				System.out.println("login successfully");
				break;
			}
			else
			{
				System.out.println("email doesnt exist");
				break;
			}
		}
		
		
	}
}
}
