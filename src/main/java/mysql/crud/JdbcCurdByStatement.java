package mysql.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mysql.util.JdbcUtil;

public class JdbcCurdByStatement {

	private static Connection conn = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		JdbcCurdByStatement.conn = conn;
	}

	public static Statement getSt() {
		return st;
	}

	public static void setSt(Statement st) {
		JdbcCurdByStatement.st = st;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		JdbcCurdByStatement.rs = rs;
	}

	public static void insert(String value){
		try {
			//��ȡһ�����ݿ�����
			conn = JdbcUtil.getConnection();
			//ͨ��conn�����ȡ����ִ��SQL�����Statement����
			st = conn.createStatement();
			//Ҫִ�е�sql
			String sql = "insert into user(ID,name,pwd) values(6,'test','456')";
			//ִ�в������
			int num = st.executeUpdate(sql);
			if(num>0){
				System.out.println("����ɹ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String args[]){
		JdbcCurdByStatement.insert("hello");
	}
}
