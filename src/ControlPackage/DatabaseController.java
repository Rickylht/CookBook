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
	
	
	
	//login����
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
	
	
	
	
	//INSERT INTO `cookbook`.`user` (`UserName`, `UserPassword`) VALUES ('Xiyuan', '12345'); ɵ�Ʒ��Ų�����
	//create account ����,�ѳɹ����
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
	
	
	
	
	//show recipe detail ����
	public Recipe showrecipedetail(int userid,String name){				
		con = this.getConnection();
		Recipe recipe = new Recipe();				
		LinkedList<Ingredients> ls1 = new LinkedList<Ingredients>();
		LinkedList<PreparationSteps> ls2 = new LinkedList<PreparationSteps>();
		try {
			sql = con.createStatement();
			
			//��ȡrecipeһ����Ϣ����
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
			
			
			//��ȡ��Ӧingredients����
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
			
			
			//��ȡ��Ӧpreparationsteps����
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
	
	
	
	
	//show all recipe list ����	
	public LinkedList<Recipe> showallrecipelist(int userid,String name) {				
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {
			
			//��ʾ�����й�������recipe
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
			
			//��ʾ��˽�в���recipe
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
	
	
	
	
	//show searching recipe list ����.�Ҳ�û��ʵ��A�û�����Ĳ�������B�û��Լ�create�Ĳ���ͬ���������
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
	
	
	
	
	//create recipe ����
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
	
	
	
	
	//ɾ��recipe����������trueΪ�ɹ�������falseΪʧ��
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
	//�޸�recipe������recipe1Ϊ��Ҫ�޸ĵ�recipe��recipe2Ϊ�޸�֮���recipe
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
	
	
	
	
	//��Ϊfavourite recipe����,trueΪ�ɹ�
	public boolean favouriterecipe(int userid,Recipe recipe){
		
	}
	
	
	
	
	//rate����
	public int rate(int userid,Recipe recipe){
		
	}
	
	
	
	
	//comments����
	public boolean comments(int userid,Recipe recipe){
		
	}

}
