package ControlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ViewPackage.LoginView;

public class LogController {
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
	public boolean log(String s1,String s2) throws SQLException{
		con = this.getConnection();
		String s = "select * from cookbook.user where UserName = '"+s1+"' and UserPassword = "+s2;
		sql = con.createStatement();
		res = sql.executeQuery(s);// SQL���Դ�Сд������
		return res.first(); 
	}
	public boolean create(String s1,String s2) throws SQLException{
		con = this.getConnection();
		String s = "insert into cookbook.user values(5, '"+s1+"',"+s2+")";
		sql = con.createStatement();
		res = sql.executeQuery(s);
		return true;		
	}

}
