package com.nondirective;

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
			input.next();
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
				input.next();
				if(num_query>0&&num_query<=4)break;
				System.out.println("please input 1-4.");
			}
			PreparedStatement ps = statementManager.getQueryPreparedStatement(num_query, cnnt);
			
		case 2:
			
		case 3:System.exit(0);
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
