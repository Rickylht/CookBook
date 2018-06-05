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
			Class.forName("com.mysql.jdbc.Driver");// 使用forName方法加载jdbc驱动程序
			System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 使用Drivemanager中getConnection的方法得到数据库连接，三个参数依次指定路径，用户名和密码
			con = DriverManager.getConnection("jdbc:mysql:" + "//127.0.0.1:3306/", "root", "1989");
			System.out.println("数据库连接成功"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public boolean log(String s1,String s2) throws SQLException{
		con = this.getConnection();
		String s = "select * from cookbook.user where UserName = '"+s1+"' and UserPassword = "+s2;
		sql = con.createStatement();
		res = sql.executeQuery(s);// SQL语句对大小写不敏感
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
