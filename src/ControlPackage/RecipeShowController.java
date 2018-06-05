package ControlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import EntityPackage.Recipe;

@SuppressWarnings("restriction")

public class RecipeShowController {
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
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cookbook", "root", "1989");
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public Recipe showrecipe() throws SQLException{				
		con = this.getConnection();
		sql = con.createStatement();
		res = sql.executeQuery("select * from cookbook.recipe where ID = 1");// sql�Դ�Сд������
		Recipe recipe = new Recipe();
		if (res.first()) {			
			recipe.setRecipeID(res.getString("ID"));
			recipe.setName(res.getString("Name"));
			recipe.setServeNumber(res.getInt("serveNumber"));
			recipe.setPrepareTime(res.getDouble("PrepareTime"));
			recipe.setCookTime(res.getDouble("cookTime"));
			recipe.setCategory(res.getString("Category"));
			recipe.setDescription(res.getString("Description"));
		}else{
			
		}
		return recipe;
	}

	

		
}
