package mysql.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * JDBC������
 * @author tangj
 *
 */
public class JdbcUtil {

	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;

	static {
		try {
			// ��ȡproperties�е����ݿ�������Ϣ
			InputStream is =new BufferedInputStream(new FileInputStream("./src/main/test/db.properties"));
			Properties prop = new Properties();
			prop.load(is);

			// ���ݿ�����
			driver = prop.getProperty("driver");
			// ���ݿ�url
			url = prop.getProperty("url");
			// ���ݿ������û���
			username = prop.getProperty("username");
			// ���ݿ���������
			password = prop.getProperty("password");

			// �������ݿ�
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * ��ȡ���ݿ����Ӷ���
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
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
