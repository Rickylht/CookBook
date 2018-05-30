package EntityPackage;

public class Ingredients {
	private double IngredientsID;
	private String Name;
	private double Amount;
	private String Unit;
	
	public Ingredients(){
		
	}
	
	public Ingredients(double ingredientsID, String name, double amount, String unit) {		
		this.IngredientsID = ingredientsID;
		this.Name = name;
		this.Amount = amount;
		this.Unit = unit;
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
	
	

}
