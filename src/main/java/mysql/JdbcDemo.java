package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {

	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		//���ݿ��ַ
		String url = "jdbc:mysql://localhost:3306/jdbc";
		//�û���
		String usename = "root";
		//����
		String password = "123456";
		
		//1.��������
		Class.forName("com.mysql.jdbc.Driver");
		//2.��ȡ�����ݿ������
		Connection connection = DriverManager.getConnection(url, usename, password);
		//3.��ȡ�������ݿ��statement
		Statement statement = connection.createStatement();
		String sql = "select * from user";
		//4.�����ݿⷢsql,��ȡ����������resultset
		ResultSet resultSet = statement.executeQuery(sql);
		//5.�ӽ������ȡ������
		while(resultSet.next()){
			System.out.println("id = "+resultSet.getObject("ID"));
			System.out.println("name = "+resultSet.getObject("name"));
			System.out.println("pwd= "+resultSet.getObject("pwd"));
		}
		//6.�ر������ͷ���Դ
		resultSet.close();
		statement.close();
		connection.close();
	}
}
