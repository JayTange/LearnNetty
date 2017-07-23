package mysql.pool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JdbcPool implements DataSource {

	private static LinkedList<Connection> listConnections = new LinkedList<Connection>();

	static {
		// �ھ�̬������м������ݿ������ļ�
		try {
			// ��ȡproperties�е����ݿ�������Ϣ
			InputStream is = new BufferedInputStream(new FileInputStream("./src/main/test/db.properties"));
			Properties prop = new Properties();
			prop.load(is);

			// ���ݿ�����
			String driver = prop.getProperty("driver");
			// ���ݿ�url
			String url = prop.getProperty("url");
			// ���ݿ������û���
			String username = prop.getProperty("username");
			// ���ݿ���������
			String password = prop.getProperty("password");
			// ���ݿ����ӳصĳ�ʼ����������С
			int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
			// �������ݿ�
			Class.forName(driver);
			for (int i = 0; i < jdbcPoolInitSize; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("��ȡ��������" + conn);
				// ����ȡ�������ݿ����Ӽ��뵽listConnections�����У�listConnections���ϴ�ʱ����һ����������ݿ����ӵ����ӳ�
				listConnections.add(conn);
			}
		} catch (Exception e) {
			 throw new ExceptionInInitializerError(e);
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// ������ݿ����ӳ������Ӷ����������0��
		if (listConnections.size() > 0) {
			// �Ӽ����л�ȡһ�����ݿ�����
			final Connection conn = listConnections.removeFirst();
			System.out.println("���ݿ����ӳش�С��: " + listConnections.size());
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (!method.getName().equals("close")) {
								return method.invoke(conn, args);
							} else {
								// ������õ���Connection�����close�������Ͱ�conn�������ݿ����ӳ�
								listConnections.add(conn);
								System.out.println(conn + "�����������ӳ�");
								System.out.println("�������ӳصĴ�СΪ�� " + listConnections.size());
								return null;
							}

						}
					});

		}else {
			throw new RuntimeException("�Բ������ݿ�æ");
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return null;
	}

}
