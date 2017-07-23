package mysql.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mysql.util.JdbcUtil;

public class JdbcBatch {

	Connection conn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	
	public void batchStatement(){
		try {
			conn = JdbcUtil.getConnection();
			String sql1 = "insert into batch(id,name) values(1,'a')";
			String sql2 = "insert into batch(id,name) values(2,'b')";
			String sql3 = "insert into batch(id,name) values(3,'c')";
			String sql4 = "insert into batch(id,name) values(4,'d')";
			String sql5 = "insert into batch(id,name) values(5,'e')";
			String sql6 = "insert into batch(id,name) values(6,'f')";
			String sql7 = "delete from batch where id=6";
			st = conn.createStatement();
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);
			st.addBatch(sql4);
			st.addBatch(sql5);
			st.addBatch(sql6);
			st.addBatch(sql7);
			st.executeBatch();
			st.clearBatch();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			System.out.println("�����ɹ�");
			JdbcUtil.release(conn, st, rs);
		}
	}
	
	public void batchPrepareMent(){
		long starttime = System.currentTimeMillis();
		try {
			conn = JdbcUtil.getConnection();
			String sql = "insert into batch(id,name) values(?,?)";
			ps = conn.prepareStatement(sql);
			for(int i=1;i<10000;i++){
				ps.setInt(1, i);
				ps.setString(2, "c"+i);
				ps.addBatch();
				if(i%1000==0){
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			ps.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("preparementִ�����");
			JdbcUtil.release(conn, st, rs);
		}
		long endtime = System.currentTimeMillis();
		System.out.println("���򻨷�ʱ��Ϊ�� "+(endtime-starttime)/1000+"��");
	}
	
	
	public static void main(String args[]){
		JdbcBatch batch = new JdbcBatch();
		batch.batchPrepareMent();
	}
}
