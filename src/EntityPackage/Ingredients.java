package EntityPackage;

public class Ingredients {
	private double IngredientsID;
	private String Name;
	private double Amount;
	private String Unit;
	private String RecipeID;
	private String Method;
	

	public Ingredients(){
		
	}
	
	public Ingredients(String name, double amount, String unit){
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;		
	}
	
	public Ingredients(String name, double amount, String unit, String method){
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
		this.Method = method;
	}
	
	public Ingredients(double ingredientsID, String name, double amount, String unit, String recipeID) {		
		this.IngredientsID = ingredientsID;
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
		this.RecipeID = recipeID;
	}
	
	public double getIngredientsID() {
		return IngredientsID;
	}
	
	public void setIngredientsID(double ingredientsID) {
		IngredientsID = ingredientsID;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public double getAmount() {
		return Amount;
	}
	
	public void setAmount(double amount) {
		Amount = amount;
	}
	
	public String getUnit() {
		return Unit;
	}
	
	public void setUnit(String unit) {
		Unit = unit;
	}
	
	public String getRecipeID() {
		return RecipeID;
	}
	
	public void setRecipeID(String recipeID) {
		RecipeID = recipeID;
	}
	
	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}
		
}
