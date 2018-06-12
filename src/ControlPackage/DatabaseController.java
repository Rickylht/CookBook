package ControlPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import EntityPackage.Ingredients;
import EntityPackage.PreparationSteps;
import EntityPackage.Recipe;
import ViewPackage.LoginView;

public class DatabaseController {
	
	static Connection con;
	static Statement sql;
	static ResultSet res,res2;
	
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
	
	
	
	//login方法
	public int login(String username,String userpassword) throws SQLException{
		int i = 1;
		con = this.getConnection();
		String s = "select * from cookbook.user where UserName = '"+username+"' and UserPassword = "+userpassword;
		sql = con.createStatement();
		res= sql.executeQuery(s);
		if(res.first()){
			i = res.getInt("userid");
		}else{
			i = 0;
		}
		return i; 
	}
	
	
	
	
	//INSERT INTO `cookbook`.`user` (`UserName`, `UserPassword`) VALUES ('Xiyuan', '12345'); 傻逼符号操你妈
	//create account 方法,已成功添加
	public boolean create(String username,String userpassword1,String userpassword2) throws SQLException{
		con = this.getConnection();		
		String s = "insert into `cookbook`.`user` (`UserName`,`UserPassword`) values('"+username+"',"+userpassword1+")";
		sql = con.createStatement();
		int res1 = sql.executeUpdate(s);
		System.out.println(res1);
		boolean i;
		if(res1 == 1 ){
			i = true;
		}else{
			i = false;
		}
		return i;		
	}
	
	
	
	
	//show recipe detail 方法
	public Recipe showrecipedetail(int userid,String name){				
		con = this.getConnection();
		Recipe recipe = new Recipe();				
		LinkedList<Ingredients> ls1 = new LinkedList<Ingredients>();
		LinkedList<PreparationSteps> ls2 = new LinkedList<PreparationSteps>();
		try {
			sql = con.createStatement();
			
			//提取recipe一般信息部分
			String ss1 = "select * from cookbook.recipe where name = '"+name+"'";
			res = sql.executeQuery(ss1);			
			if (res.next()){			
				recipe.setRecipeID(res.getInt("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
			}
			
			
			//提取对应ingredients部分
			String recipeid = res.getString("ID");
			String ss2 = "select * from cookbook.ingredients where RecipeID = "+recipeid;
			res = sql.executeQuery(ss2);		
			while(res.next()){
				Ingredients ingredients = new Ingredients();
				ingredients.setIngredientsID(res.getDouble("ID"));
				ingredients.setName(res.getString("Name"));
				ingredients.setAmount(res.getDouble("Amount"));
				ingredients.setUnit(res.getString("Unit"));
				ls1.add(ingredients);				 
			}			
			recipe.setIngredientlist(ls1);
			
			
			//提取对应preparationsteps部分
			String ss3 = "select * from cookbook.preparationsteps where RecipeID = "+recipeid;
			res = sql.executeQuery(ss3);
			while(res.next()){
				PreparationSteps preparationsteps = new PreparationSteps();
				preparationsteps.setStepsID(res.getDouble("ID"));
				preparationsteps.setDescription(res.getString("Description"));
				preparationsteps.setOrder(res.getDouble("preparationstepsorder"));
				ls2.add(preparationsteps);
			}
			recipe.setPreparationSteps(ls2);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe;
	}
	
	
	
	
	//show all recipe list 方法	
	public LinkedList<Recipe> showallrecipelist(int userid,String name) {				
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {
			
			//显示出所有公共部分recipe
			sql = con.createStatement();
			res = sql.executeQuery("select * from cookbook.recipe where `privacy` = 0");
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getInt("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);							
			}
			
			//显示出私有部分recipe
			String ss = "select * from `cookbook`.`user-recipe` where userid = "+userid;
			res = sql.executeQuery(ss);
			String s1;
			while(res.next()){
				s1 ="select * from `cookbook`.`recipe` where recipeid = "+Integer.toString(res.getInt("recipeid"));
				res2 = sql.executeQuery(s1);
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res2.getInt("ID"));
				recipe.setName(res2.getString("Name"));
				recipe.setServeNumber(res2.getInt("serveNumber"));
				recipe.setPrepareTime(res2.getInt("PrepareTime"));
				recipe.setCookTime(res2.getInt("cookTime"));
				recipe.setCategory(res2.getString("Category"));
				recipe.setDescription(res2.getString("Description"));
				ls.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	
	
	
	//show searching recipe list 方法.我并没有实现A用户输入的菜谱名和B用户自己create的菜谱同名这个功能
	public LinkedList<Recipe> showsearchingrecipelist(int userid,String s) {
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();		
		try {
			sql = con.createStatement();
			String ss = "select * from cookbook.recipe where name is like '%"+s+"%'";
			res = sql.executeQuery(ss);
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getInt("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	
	
	
	//create recipe 方法
	public boolean createrecipe(int userid,Recipe recipe){
		con = this.getConnection();
		int i = 0;
		int recipeid;	
		try {
			String ss1 = "INSERT INTO `cookbook`.`recipe` (`Name`, `ServeNumber`, `Privacy`, `PrepareTime`, `Category`, `Description`, `CookTime`) values('"+
					recipe.getName()+"',"+recipe.getServeNumber()+","+recipe.getPrivacy()+","+recipe.getPrepareTime()+",'"+recipe.getCategory()+"','"+recipe.getDescription()+"',"+recipe.getCookTime();
			int res1 = sql.executeUpdate(ss1);
			if(res1 == 1){
				i=i++;
			}
			
			String ss = "select * from `cookbook`.`recipe` where Name = '"+recipe.getName()+"'";
			res = sql.executeQuery(ss);
			recipeid = res.getInt("ID");
			LinkedList<Ingredients> ls1 = recipe.getIngredientlist();
			Iterator iter1 = ls1.iterator();
			while(iter1.hasNext()){
				String ss2 = "INSERT INTO `cookbook`.`ingredients` (`Name`, `RecipeID`, `Amount`, `Unit`, `Description`) values('"+
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return true;
	}
	
	
	
	
	//删除recipe方法，返回true为成功，返回false为失败
	public boolean deleterecipe(Recipe recipe){
		con = this.getConnection();
		boolean i = false;
		int recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`recipe` where id = '"+recipeid+"' and privacy = 1";
		try {
			sql = con.createStatement();
			int res1 = sql.executeUpdate(ss);			
			if(res1 == 1 ){
				i = true;
			}else{
				i = false;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;		
	}
	
	
	
	
	//UPDATE `cookbook`.`recipe` SET `Name` = 'qiezi', `ServeNumber` = '2', `Category` = 'meat' WHERE (`ID` = '4');
	//修改recipe方法，recipe1为想要修改的recipe，recipe2为修改之后的recipe
	public boolean editrecipe(int userid,Recipe recipe1,Recipe recipe2){
		con = this.getConnection();
		boolean i = false;
		int recipe1id = recipe1.getRecipeID();
		int recipe2id = recipe2.getRecipeID();		
		try {
			String ss1 = "update `cookbook`.`recipe` set `name` = '"+recipe2.getName()+"', `servenumber` = '"+recipe2.getServeNumber()
			+"', `preparetime` = '"+recipe2.getPrepareTime()+"', `category` = '"+recipe2.getCategory()+"', `description` = '"+recipe2.getDescription()
				+"', `cooktime` = '"+recipe2.getCookTime()+"' where (`id` = "+recipe1id+")";
			res = sql.executeQuery(ss1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	//设为favourite recipe方法,true为成功
	public boolean favouriterecipe(int userid,Recipe recipe){
		
	}
	
	
	
	
	//rate功能
	public int rate(int userid,Recipe recipe){
		
	}
	
	
	
	
	//comments功能
	public boolean comments(int userid,Recipe recipe){
		
	}

}
