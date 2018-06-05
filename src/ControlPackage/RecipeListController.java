package ControlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import EntityPackage.Recipe;

public class RecipeListController {
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
	public LinkedList<Recipe> showDetail() {
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		RecipeShowController c = new RecipeShowController();// 创建本类对象
		con = c.getConnection();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select * from recipe where name is like");// sql对大小写不敏感
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getDouble("PrepareTime"));
				recipe.setCookTime(res.getDouble("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

}
