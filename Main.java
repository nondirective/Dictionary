package com.nondirective;

import java.util.regex.*;
import java.util.Scanner;
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		Scanner input = project_utils.getSacnner();
		{
		String user = null;
		String password = null;
		int count = 3;
		while(true){
			System.out.print("User name:");
			user = input.nextLine();
			System.out.print("Password:");
			password = input.nextLine();
			if(!check_user(user, password)) {
				count-=1;
				System.out.println("user name or password wrong,please try if again,you are remain "+count+" time chance.");
			}else {
				break;
			}
			}
		}
		
		Connection cnnt = project_utils.getConnection();
		
		while(true)
		{
		System.out.println("1.Word query.");
		System.out.println("2.Insert word.");
		System.out.println("3.Quit.");
		System.out.print("please input a number to choose item:");
		int num = 0;
		while(true) {
			num = input.nextInt();
//			input.next();
			if(num>0&&num<=3)break;
			System.out.println("please input 1-3.");
		}
		switch(num) {
		case 1:
			System.out.println("1.query by id.");
			System.out.println("2.query by chinese.");
			System.out.println("3.query by english");
			System.out.println("4.query all");
			int num_query = 0;
			while(true) {
				num_query = input.nextInt();
//				input.next();
				if(num_query>0&&num_query<=4)break;
				System.out.println("please input 1-4.");
			}
			PreparedStatement ps_query = statementManager.getQueryPreparedStatement(num_query, cnnt);
			if(!(num_query==1)) {
				System.out.print("please input query instruct:");
				String str = input.nextLine();
				Pattern p = Pattern.compile("[0-9]*");
				try {
					if(p.matcher(str).matches()) //true=>number
						ps_query.setInt(1, Integer.parseInt(str));
					else
						ps_query.setString(1, str);
					ResultSet rs = ps_query.executeQuery();
					
					 while(rs.next())
					 {
						 System.out.println("id:"+rs.getInt(1)+" english:"+rs.getString(1)+" chinese:"+rs.getString(2));
					 }
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					ResultSet rs =ps_query.executeQuery();
					while(rs.next())
					{
						System.out.println("id:"+rs.getInt(1)+" english:"+rs.getString(1)+" chinese:"+rs.getString(2));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
			
		case 2:
			System.out.println("please input insert instruct.");
			System.out.print("id:");
			int id = input.nextInt();
			input.next();
			System.out.print("english:");
			String english = input.nextLine();
//			input.next();
			System.out.print("chinese:");
			String chinese = input.nextLine();
			PreparedStatement ps_insert;
			ps_insert = statementManager.getInsertPreparedStatement(cnnt);
			try {
				ps_insert.setInt(1, id);
				ps_insert.setString(2, english);
				ps_insert.setString(3, chinese);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int status = -1;
			try {
				status =ps_insert.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println(status);
			}
			break;
		case 3:System.exit(0);
		default:
			System.exit(0);
		}
		}
		
	}
	
	private static boolean check_user(String user,String password) {
		if(project_utils.getLocal_user().equals(user)&&project_utils.getLocal_password().equals(password))
			return true;
		else
			return false;
	}
}
