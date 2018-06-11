package EntityPackage;

public class Ingredients {
	private double IngredientsID;
	private String Name;
	private double Amount;
	private String Unit;
	private String Description;
	
	public Ingredients(){
		
	}
	
	public Ingredients(String name, double amount, String unit, String description ){
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
		this.Description = description;
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
	
	public String toString() {
		String Tostring  =  new String();
		Tostring = this.Name +" "+ String.valueOf(this.Amount)+" "+ this.Unit +" ("+this.Description+") \n";		
		return Tostring;
	}		
}
