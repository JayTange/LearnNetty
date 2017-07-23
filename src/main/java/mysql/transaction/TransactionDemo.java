package mysql.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import mysql.util.JdbcUtil;

/**
 * JDBC��ʹ��������ģ��ת�� 
    create table account(
        id int primary key auto_increment,
        name varchar(40),
        money float
    );
    insert into account(name,money) values('A',1000);
    insert into account(name,money) values('B',1000);
    insert into account(name,money) values('C',1000);
 * @author tangj
 *
 */
public class TransactionDemo {

	static Connection conn = null;
	static PreparedStatement st = null;
	static ResultSet rs = null;
	static Savepoint sp = null;
	
	public static void payAToB(){
		try {
			conn = JdbcUtil.getConnection();
			//�������ݿ�����
			conn.setAutoCommit(false);
			String sql = "update account set money=money-100 where name ='A'";
			st = conn.prepareStatement(sql);
			st.executeUpdate();
			String sql2 = "update account set money=money+100 where name ='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			//���������SQLִ��Update���ɹ�֮���֪ͨ���ݿ��ύ����(commit)
			conn.commit();
			System.out.println("ִ�гɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(conn, st, rs);
		}
	}
	/**
	 * ģ��ת�˹����г����쳣������һ����SQLִ��ʧ��ʱ�ֶ�֪ͨ���ݿ�ع�����
	 */
	public static void rollBackE(){
		try {
			conn = JdbcUtil.getConnection();
			//�������ݿ�����
			conn.setAutoCommit(false);
			String sql = "update account set money=money-100 where name ='A'";
			st = conn.prepareStatement(sql);
			st.executeUpdate();
			int x=1/0;
			String sql2 = "update account set money=money+100 where name ='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			//���������SQLִ��Update���ɹ�֮���֪ͨ���ݿ��ύ����(commit)
			conn.commit();
			System.out.println("ִ�гɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.release(conn, st, rs);
		}
	}
	/**
	 * �����쳣���ݻع�
	 */
	public static void rollBack(){
		try {
			conn = JdbcUtil.getConnection();
			//�������ݿ�����
			conn.setAutoCommit(false);
			String sql = "update account set money=money-100 where name ='A'";
			st = conn.prepareStatement(sql);
			st.executeUpdate();
			int x=1/0;
			String sql2 = "update account set money=money+100 where name ='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			//���������SQLִ��Update���ɹ�֮���֪ͨ���ݿ��ύ����(commit)
			conn.commit();
			System.out.println("ִ�гɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("�����ѻع�");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			JdbcUtil.release(conn, st, rs);
		}
	}
	
	public static void rollBackPoint(){
		try {
			conn = JdbcUtil.getConnection();
			//�������ݿ�����
			conn.setAutoCommit(false);
			String sql = "update account set money=money-100 where name ='A'";
			st = conn.prepareStatement(sql);
			st.executeUpdate();
			
			//��������ع���
			sp = conn.setSavepoint();
			
			String sql2 = "update account set money=money+100 where name ='B'";
			st = conn.prepareStatement(sql2);
			st.executeUpdate();
			
			//�쳣��ʼ
			int x=1/0;
			String sql3 =  "update account set money = money+100 where nam='C'";
			st = conn.prepareStatement(sql3);
			st.executeUpdate();
			
			conn.commit();
			System.out.println("ִ�гɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				/**
				 * �ܹ�������sql��䣬�쳣����sql2��sql3֮��ģ�
				 * �ع��������ڵ�һ��ִ��֮��
				 * 
				 */
				conn.rollback(sp);
				conn.commit();
				System.out.println("�����ѻع�");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			JdbcUtil.release(conn, st, rs);
		}
	}
	
	public static void main(String args[]){
		//TransactionDemo.payAToB();
//		TransactionDemo.rollBack();
		TransactionDemo.rollBackPoint();
	}
}
