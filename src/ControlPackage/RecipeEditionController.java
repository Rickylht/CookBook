package ControlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeEditionController {
	static Connection con;
	static Statement sql;
	static ResultSet res;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// ʹ��forName��������jdbc��������
			System.out.println("���ݿ��������سɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// ʹ��Drivemanager��getConnection�ķ����õ����ݿ����ӣ�������������ָ��·�����û���������
			con = DriverManager.getConnection("jdbc:mysql:" + "//127.0.0.1:3306/", "root", "1989");
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
