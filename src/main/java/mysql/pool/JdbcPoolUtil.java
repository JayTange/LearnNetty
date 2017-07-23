package mysql.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcPoolUtil {

	private static JdbcPool pool = new JdbcPool();
	
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	
	public static void release(Connection connection, Statement state, ResultSet resultSet) {
		// �رմ洢��ѯ�����ResultSet����
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// �رո���ִ��SQL�����Statement����
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// �ر�Connection���ݿ����Ӷ���
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
