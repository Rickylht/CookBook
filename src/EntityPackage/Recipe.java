package EntityPackage;

import java.util.*;

public class Recipe {
	
	private String RecipeID;
	private String Name;
	private String Description;
	private boolean Privacy;
	private int ServeNumber;
	private double PrepareTime;
	private double CookTime;
	private String Category;	
	private Ingredients ingredients ;
	private LinkedList<String> PreparationSteps;
	
	//构造方法
	
	public Recipe(){
		
	}
	
	public Recipe(String name , String category, String recipeid ){
		this.RecipeID= recipeid ;
		this.Name = name ; 
		this.Category = category; 
		this.PreparationSteps = new LinkedList<String>();		
	}	

	public Recipe(String recipeID, String title, String description, boolean privacy, int serveNumber,
			double prepareTime, double cookTime, String category) {
		this.RecipeID = recipeID;
		this.Name = title;
		this.Description = description;
		this.Privacy = privacy;
		this.ServeNumber = serveNumber;
		this.PrepareTime = prepareTime;
		this.CookTime = cookTime;
		this.Category = category;
	}

	//getter and setter
	
	public String getRecipeID() {
		return RecipeID;
	}

	public void setRecipeID(String recipeid) {
		RecipeID = recipeid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isPrivacy() {
		return Privacy;
	}

	public void setPrivacy(boolean privacy) {
		Privacy = privacy;
	}

	public int getServeNumber() {
		return ServeNumber;
	}

	public void setServeNumber(int serveNumber) {
		ServeNumber = serveNumber;
	}

	public double getPrepareTime() {
		return PrepareTime;
	}

	public void setPrepareTime(double prepareTime) {
		PrepareTime = prepareTime;
	}

	public double getCookTime() {
		return CookTime;
	}

	public void setCookTime(double cookTime) {
		CookTime = cookTime;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}
	
	//function
	public void addIngredient(Ingredients ingredients){
		this.ingredients = ingredients ;
	}
	
	public void addPreparationStep (String step ) {
		steps.add(step);	
	}

	@Override
	public String toString() {
		return ("ID:" + this.RecipeID + "name:" + this.Name + "Description"+this.Description+"privicy"+this.Privacy+"serveNumber"+this.ServeNumber+"PrepareTime"+this.PrepareTime+"CookTime"+this.CookTime);
	}
}
