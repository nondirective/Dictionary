package com.nondirective;

import java.sql.*;

import java.util.Scanner;

public class project_utils {
	private project_utils() {}
	
	private static Scanner input = null;
	private static String local_user = "nondirective";
	public static String getLocal_user() {return local_user;}
	public static String getLocal_password() {return local_password;}

	private static String local_password = "821206";
	
	public static Scanner getSacnner() {
		if(input == null) input = new Scanner(System.in);
		return input;
	}
	
	static { 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ce) {
			System.out.println(ce.toString());
		}
	}
	
	private static java.sql.Connection cnnt = null;
	private static String url = "jdbc:mysql://localhost/dictionary";
	private static String user = "root";
	private static String password = "";
	
	public static Connection getConnection() {
		if(cnnt == null) {
			try {
				cnnt = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnnt;
	}
	
	public void free(ResultSet rs,Statement sm,Connection cnnt) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(sm!=null) {
					try {
						sm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						if(cnnt!=null) {
							try {
								cnnt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public void free(PreparedStatement ps,Connection cnnt) {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(cnnt!=null) {
					try {
						cnnt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void free(ResultSet rs,PreparedStatement ps,Connection cnnt) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					if(cnnt!=null) {
						try {
							cnnt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	

}
