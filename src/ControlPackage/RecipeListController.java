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
	public LinkedList<Recipe> showDetail() {
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		RecipeShowController c = new RecipeShowController();// �����������
		con = c.getConnection();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select * from recipe where name is like");// sql�Դ�Сд������
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
