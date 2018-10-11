package com.nondirective;

import java.sql.*;

public class statementManager {
	private static PreparedStatement ps1 = null;
	private static PreparedStatement ps2 = null;
	private static PreparedStatement ps3 = null;
	private static PreparedStatement ps4 = null;
	private static PreparedStatement ps5 = null;
	//查询对象
	private static final String queryEnglishChineseById = "select id,english,chinese from word where id=?";
	//1
	private static final String queryEnglishByChinese = "select id,english,chinese from word where chinese=?";
	//2
	private static final String queryChineseByEnglish = "select id,english,chinese from word where english=?";
	//3
	private static final String queryAll = "select * from word";
	//4
	//插入对象
	private static final String INSERT = "insert into word (id,english,chinese) values (?,?,?)";
	//5
	public static PreparedStatement getQueryPreparedStatement(int item,Connection cnnt) {
		try {
			switch(item) {
				case 1:
					if(ps1==null)ps1 = cnnt.prepareStatement(queryEnglishChineseById);
					
				case 2:
					if(ps2==null)ps2 = cnnt.prepareStatement(queryEnglishByChinese);
					return ps1;
				case 3:
					if(ps3==null)ps2 = cnnt.prepareStatement(queryChineseByEnglish);
					return ps3;
				case 4:
					if(ps4==null)ps4 = cnnt.prepareStatement(queryAll);
					return ps4;
			}
		}catch(SQLException se)
		{
			System.out.println(se.getMessage());
		}
		return null;
	}
	
	public static PreparedStatement getInsertPreparedStatement(Connection cnnt) {
		try {
			if(ps5==null)ps5=cnnt.prepareStatement(INSERT);
			return ps5;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
